package com.sfl.coolmonkey.emailtemplate.service.emailtemplate.impl;

import com.sfl.coolmonkey.emailtemplate.persistence.repositories.emailtemplate.EmailTemplateRepository;
import com.sfl.coolmonkey.emailtemplate.service.common.exception.ServicesRuntimeException;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.EmailTemplateService;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.component.EmailTemplateServiceValidationComponent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.dto.EmailTemplateDto;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.event.EmailTemplateModifiedEvent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplateType;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.SystemEmailTemplateType;
import org.apache.commons.lang3.SerializationUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/22/15
 * Time: 7:36 PM
 */
@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateServiceImpl.class);

    //region Dependencies
    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Autowired
    private EmailTemplateServiceValidationComponent emailTemplateServiceValidationComponent;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    //endregion

    //region Constructors
    public EmailTemplateServiceImpl() {
        LOGGER.debug("Initializing");
    }
    //endregion

    //region Public methods
    @Nonnull
    @Override
    public EmailTemplate getById(@Nonnull final ObjectId id) {
        Assert.notNull(id);
        final EmailTemplate emailTemplate = emailTemplateRepository.findOne(id);
        assertEmailTemplateNotNullForId(id, emailTemplate);
        return emailTemplate;
    }

    @Nonnull
    @Override
    public EmailTemplate getByUuid(@Nonnull final String uuid) {
        Assert.notNull(uuid);
        final EmailTemplate emailTemplate = emailTemplateRepository.findByUuid(uuid);
        assertEmailTemplateNotNullForUuid(uuid, emailTemplate);
        return emailTemplate;
    }

    @Nonnull
    @Override
    public EmailTemplate create(@Nonnull final String companyUuid, @Nonnull final EmailTemplateType type, @Nonnull final EmailTemplateDto dto) {
        Assert.notNull(companyUuid);
        assertEmailTemplateTypeNotNull(type);
        emailTemplateServiceValidationComponent.performEmailTemplateDtoValidations(dto);
        EmailTemplate emailTemplate = new EmailTemplate();
        dto.updateDomainEntityProperties(emailTemplate);
        emailTemplate.setCompanyUuid(companyUuid);
        emailTemplate.setType(type);
        emailTemplate = emailTemplateRepository.save(emailTemplate);
        return emailTemplate;
    }

    @Nonnull
    @Override
    public EmailTemplate update(@Nonnull final String uuid, @Nonnull final EmailTemplateDto dto) {
        Assert.notNull(uuid);
        emailTemplateServiceValidationComponent.performEmailTemplateDtoValidations(dto);
        EmailTemplate emailTemplate = getByUuid(uuid);
        emailTemplate.setUpdated(new Date());
        dto.updateDomainEntityProperties(emailTemplate);
        return emailTemplateRepository.save(emailTemplate);
    }

    @Nonnull
    @Override
    public List<EmailTemplate> getAllByCompanyUuid(@Nonnull final String companyUuid) {
        Assert.notNull(companyUuid);
        return emailTemplateRepository.findByCompanyUuidAndRemovedIsNull(companyUuid);
    }

    @Nonnull
    @Override
    public List<EmailTemplate> getAllActiveByCompanyUuid(@Nonnull final String companyUuid) {
        Assert.notNull(companyUuid);
        return emailTemplateRepository.findByCompanyUuidAndRemovedIsNullAndActiveTrue(companyUuid);
    }


    @Nonnull
    @Override
    public List<EmailTemplate> getAllByCompanyUuidAndType(@Nonnull final String companyUuid, @Nonnull final EmailTemplateType type) {
        Assert.notNull(companyUuid);
        assertEmailTemplateTypeNotNull(type);
        return emailTemplateRepository.findByCompanyUuidAndTypeAndRemovedIsNull(companyUuid, type);
    }

    @Nonnull
    @Override
    public EmailTemplate activate(@Nonnull final String uuid) {
        Assert.notNull(uuid);
        final EmailTemplate emailTemplate = getByUuid(uuid);
        emailTemplate.setActive(true);
        return emailTemplateRepository.save(emailTemplate);

    }

    @Nonnull
    @Override
    public EmailTemplate deactivate(@Nonnull final String uuid) {
        Assert.notNull(uuid);
        final EmailTemplate emailTemplate = getByUuid(uuid);
        emailTemplate.setActive(false);
        return emailTemplateRepository.save(emailTemplate);
    }

    @Nonnull
    @Override
    public EmailTemplate remove(@Nonnull final String uuid) {
        Assert.notNull(uuid);
        final EmailTemplate emailTemplate = getByUuid(uuid);
        emailTemplate.setRemoved(new Date());
        return emailTemplateRepository.save(emailTemplate);
    }

    @Override
    public boolean checkIfEmailTemplateExistForNameAndCompany(@Nonnull final String emailTemplateName, @Nonnull final String companyUuid) {
        Assert.notNull(emailTemplateName);
        Assert.notNull(companyUuid);
        final EmailTemplate emailTemplate = emailTemplateRepository.findByNameIgnoreCaseAndCompanyUuidAndRemovedIsNull(emailTemplateName, companyUuid);
        return emailTemplate != null;
    }

    @Nonnull
    @Override
    public EmailTemplate getBySystemEmailTemplateType(@Nonnull final SystemEmailTemplateType systemEmailTemplateType) {
        Assert.notNull(systemEmailTemplateType);
        final EmailTemplate emailTemplate =
                emailTemplateRepository.findBySystemEmailTemplateTypeAndRemovedIsNull(systemEmailTemplateType);
        if (emailTemplate == null) {
            LOGGER.error("Email template not found for system type - {}", systemEmailTemplateType);
            throw new ServicesRuntimeException("Email template not found for system type - " + systemEmailTemplateType);
        }
        return emailTemplate;
    }

    @Nonnull
    @Override
    public List<EmailTemplate> getForIndexation(final int page, final int size) {
        Assert.isTrue(page >= 0);
        Assert.isTrue(size > 0);
        final Page<EmailTemplate> ordersPage = emailTemplateRepository.findByRemovedIsNull(new PageRequest(page, size, Sort.Direction.ASC, "created"));
        return ordersPage.getContent();
    }

    @Nonnull
    @Override
    public Map<String, String> copy(@Nonnull final String fromCompanyUuid, @Nonnull final String toCompanyUuid) {
        Assert.notNull(fromCompanyUuid);
        Assert.notNull(toCompanyUuid);
        final List<EmailTemplate> originalEmailTemplates = emailTemplateRepository.findByCompanyUuidAndRemovedIsNull(fromCompanyUuid);
        List<EmailTemplate> newEmailTemplates = new ArrayList<>();
        final Map<String, String> originalToNewUuidsMap = new HashMap<>();
        for (final EmailTemplate originalEmailTemplate : originalEmailTemplates) {
            final EmailTemplate newEmailTemplate = SerializationUtils.clone(originalEmailTemplate);
            newEmailTemplate.setId(null);
            newEmailTemplate.setCreated(new Date());
            newEmailTemplate.setUpdated(new Date());
            newEmailTemplate.setUuid(UUID.randomUUID().toString());
            newEmailTemplate.setCompanyUuid(toCompanyUuid);
            newEmailTemplates.add(newEmailTemplate);
            originalToNewUuidsMap.put(originalEmailTemplate.getUuid(), newEmailTemplate.getUuid());
        }
        newEmailTemplates = emailTemplateRepository.save(newEmailTemplates);
        for (final EmailTemplate newEmailTemplate : newEmailTemplates) {
            applicationEventPublisher.publishEvent(new EmailTemplateModifiedEvent(this, newEmailTemplate.getUuid()));
        }
        return originalToNewUuidsMap;
    }

    @Nonnull
    @Override
    public List<EmailTemplate> getAllInactiveEmailTemplates() {
        return emailTemplateRepository.findByActiveFalse();
    }
    //endregion

    //region Utility methods
    private void assertEmailTemplateTypeNotNull(@Nonnull final EmailTemplateType type) {
        Assert.notNull(type, "Email template type should not be null");
    }

    private void assertEmailTemplateNotNullForId(final ObjectId id, final EmailTemplate emailTemplate) {
        if (emailTemplate == null) {
            LOGGER.error("Can not find email template with id - {}", id);
            throw new ServicesRuntimeException("Can not find email template");
        }
    }

    private void assertEmailTemplateNotNullForUuid(final String uuid, final EmailTemplate emailTemplate) {
        if (emailTemplate == null) {
            LOGGER.error("Can not find email template with uuid - {}", uuid);
            throw new ServicesRuntimeException("Can not find email template");
        }
    }
    //endregion
}

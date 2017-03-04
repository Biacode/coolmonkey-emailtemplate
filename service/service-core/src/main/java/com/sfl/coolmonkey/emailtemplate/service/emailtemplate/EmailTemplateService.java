package com.sfl.coolmonkey.emailtemplate.service.emailtemplate;

import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.dto.EmailTemplateDto;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplateType;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.SystemEmailTemplateType;
import org.bson.types.ObjectId;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/22/15
 * Time: 7:28 PM
 */
public interface EmailTemplateService {
    /**
     * Gets email template by provided id
     *
     * @param id the id
     * @return by id
     */
    @Nonnull
    EmailTemplate getById(@Nonnull final ObjectId id);

    /**
     * Gets email template by provided uuid
     *
     * @param uuid the uuid
     * @return by uuid
     */
    @Nonnull
    EmailTemplate getByUuid(@Nonnull final String uuid);

    /**
     * Creates email template
     *
     * @param companyUuid the company uuid
     * @param type        the type
     * @param dto         the dto
     * @return email template
     */
    @Nonnull
    EmailTemplate create(@Nonnull final String companyUuid, @Nonnull final EmailTemplateType type, @Nonnull final EmailTemplateDto dto);

    /**
     * Gets all not removed email templates by provided company uuid
     *
     * @param companyUuid the company uuid
     * @return all not removed by company uuid
     */
    @Nonnull
    List<EmailTemplate> getAllByCompanyUuid(@Nonnull final String companyUuid);

    /**
     * Gets all not removed and active email templates by provided company uuid
     *
     * @param companyUuid the company uuid
     * @return all not removed and active by company uuid
     */
    @Nonnull
    List<EmailTemplate> getAllActiveByCompanyUuid(@Nonnull final String companyUuid);

    /**
     * Updates email template for provided uuid, attachments and dto
     *
     * @param uuid the uuid
     * @param dto  the dto
     * @return email template
     */
    @Nonnull
    EmailTemplate update(@Nonnull final String uuid, @Nonnull final EmailTemplateDto dto);

    /**
     * Gets all email templates for provided company uuid and type
     *
     * @param companyUuid the company uuid
     * @param type        the type
     * @return all by company uuid and type
     */
    @Nonnull
    List<EmailTemplate> getAllByCompanyUuidAndType(@Nonnull final String companyUuid, @Nonnull final EmailTemplateType type);

    /**
     * Marks email template with given id as active
     *
     * @param uuid the uuid
     * @return emailTemplate email template
     */
    @Nonnull
    EmailTemplate activate(@Nonnull final String uuid);

    /**
     * Marks email template with given id as deactivated
     *
     * @param uuid the uuid
     * @return emailTemplate email template
     */
    @Nonnull
    EmailTemplate deactivate(@Nonnull final String uuid);

    /**
     * Marks the email template with given uuid as removed
     *
     * @param uuid the uuid
     * @return email template
     */
    @Nonnull
    EmailTemplate remove(@Nonnull final String uuid);

    /**
     * Checks whether email template exists
     *
     * @param emailTemplateName the email template name
     * @param companyUuid       the company uuid
     * @return boolean boolean
     */
    boolean checkIfEmailTemplateExistForNameAndCompany(@Nonnull final String emailTemplateName, @Nonnull final String companyUuid);

    /**
     * Gets the unique email template by system email template type
     *
     * @param systemEmailTemplateType the system email template type
     * @return by system email template type
     */
    @Nonnull
    EmailTemplate getBySystemEmailTemplateType(@Nonnull final SystemEmailTemplateType systemEmailTemplateType);

    /**
     * Gets the email templates for indexation
     *
     * @param page the page
     * @param size the size
     * @return for indexation
     */
    @Nonnull
    List<EmailTemplate> getForIndexation(final int page, final int size);

    /**
     * Copies email templates from given company to given company
     *
     * @param fromCompanyUuid
     * @param toCompanyUuid
     * @return map of old uuids to new uuids
     */
    @Nonnull
    Map<String, String> copy(@Nonnull final String fromCompanyUuid, @Nonnull final String toCompanyUuid);

    @Nonnull
    List<EmailTemplate> getAllInactiveEmailTemplates();
}

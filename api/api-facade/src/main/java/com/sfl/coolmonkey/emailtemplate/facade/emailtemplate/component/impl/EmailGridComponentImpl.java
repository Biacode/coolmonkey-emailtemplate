package com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.component.impl;

import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailGridModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateTypeModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request.GetEmailGridRequest;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.response.GetEmailGridResponse;
import com.sfl.coolmonkey.emailtemplate.facade.common.exception.FacadeRuntimeException;
import com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.component.EmailGridComponent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.EmailTemplateService;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 10/27/15
 * Time: 8:33 PM
 */
@Component
public class EmailGridComponentImpl implements EmailGridComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailGridComponentImpl.class);

    //region Dependencies
    @Autowired
    private EmailTemplateService emailTemplateService;
    //endregion

    //region Constructors
    public EmailGridComponentImpl() {
        LOGGER.debug("Initializing email grid component");
    }
    //endregion

    //region Public methods
    @Nonnull
    @Override
    public ResultResponseModel<GetEmailGridResponse> getEmailGrid(@Nonnull final GetEmailGridRequest request) {
        assertGetEmailGridRequest(request);
        final String companyUuid = request.getCompanyUuid();
        final List<EmailTemplate> emailTemplates = emailTemplateService.getAllByCompanyUuid(companyUuid);
        final List<EmailGridModel> emailGrids = new ArrayList<>();
        for (final EmailTemplate emailTemplate : emailTemplates) {
            final EmailGridModel emailGridModel = buildEmailGridModelFromEmailTemplate(emailTemplate);
            emailGridModel.setUsedOnDispositionCodes(0);
            emailGrids.add(emailGridModel);
        }
        return new ResultResponseModel<>(new GetEmailGridResponse(emailGrids));
    }
    //endregion

    //region Utility methods
    private EmailGridModel buildEmailGridModelFromEmailTemplate(final EmailTemplate emailTemplate) {
        final EmailGridModel emailGridModel = new EmailGridModel();
        emailGridModel.setUuid(emailTemplate.getUuid());
        emailGridModel.setSubject(emailTemplate.getSubject());
        emailGridModel.setName(emailTemplate.getName());
        emailGridModel.setActive(emailTemplate.isActive());
        emailGridModel.setSenderEmail(emailTemplate.getSenderEmail());
        emailGridModel.setSenderName(emailTemplate.getSenderName());
        emailGridModel.setType(buildEmailTemplateTypeModelFromEmailTemplateType(emailTemplate.getType()));
        if (emailTemplate.getAttachmentUuids() != null) {
            emailGridModel.setNumberOfAttachments(emailTemplate.getAttachmentUuids().size());
        } else {
            emailGridModel.setNumberOfAttachments(0);
        }
        return emailGridModel;
    }

    private void assertGetEmailGridRequest(final GetEmailGridRequest request) {
        Assert.notNull(request, "Get email grid request should not be null");
        Assert.hasText(request.getCompanyUuid(), "Get email grid request company uuid should not be blank");
    }

    private EmailTemplateTypeModel buildEmailTemplateTypeModelFromEmailTemplateType(final EmailTemplateType type) {
        if (type == null) {
            return null;
        } else {
            switch (type) {
                case APPOINTMENT_EMAIL_TO_ADDRESS:
                    return EmailTemplateTypeModel.APPOINTMENT_EMAIL_TO_ADDRESS;
                case INFO_TO_ADDRESS:
                    return EmailTemplateTypeModel.INFO_TO_ADDRESS;
                case NOTIFICATION_TO_SALES_USER:
                    return EmailTemplateTypeModel.NOTIFICATION_TO_SALES_USER;
                default:
                    throw new FacadeRuntimeException("Unknown email template type");
            }
        }
    }
    //endregion
}

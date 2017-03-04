package com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.impl;

import com.sfl.coolmonkey.commons.api.model.CommonErrorType;
import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateCreateUpdateModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateSearchModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.SystemEmailTemplateTypeModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request.*;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.response.*;
import com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.EmailTemplateFacade;
import com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.component.EmailGridComponent;
import com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.component.EmailTemplateFacadeValidationComponent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.EmailTemplateService;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.component.EmailTemplateTypeTranslationComponent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.dto.EmailTemplateDto;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.event.EmailTemplateModifiedEvent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.event.EmailTemplateRemovedEvent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplateType;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.SystemEmailTemplateType;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/23/15
 * Time: 11:04 AM
 */
@SuppressWarnings({
        "squid:CommentedOutCodeLine"
})
@Component
public class EmailTemplateFacadeImpl implements EmailTemplateFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateFacadeImpl.class);

    //region Dependencies
    @Autowired
    private EmailTemplateService emailTemplateService;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private EmailGridComponent emailGridComponent;

    @Autowired
    private EmailTemplateFacadeValidationComponent emailTemplateFacadeValidationComponent;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private EmailTemplateTypeTranslationComponent emailTemplateTypeTranslationComponent;
    //endregion

    //region Constructors
    public EmailTemplateFacadeImpl() {
        LOGGER.debug("Initializing email template service facade");
    }
    //endregion

    //region Public methods
    @Nonnull
    @Override
    public ResultResponseModel<CreateEmailTemplateResponse> create(@Nonnull final CreateEmailTemplateRequest request) {
        assertCreateEmailTemplateRequest(request);
        final EmailTemplateCreateUpdateModel model = request.getEmailTemplateModel();
        final Map<CommonErrorType, Object> errors = emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForCreate(model.getName(), request.getCompanyUuid());
        if (!errors.isEmpty()) {
            return new ResultResponseModel<>(errors);
        }
        final EmailTemplateDto dto = mapperFacade.map(model, EmailTemplateDto.class);
        final EmailTemplateType type = EmailTemplateType.valueOf(model.getType().toString());
        final EmailTemplate emailTemplate = emailTemplateService.create(request.getCompanyUuid(), type, dto);
        applicationEventPublisher.publishEvent(new EmailTemplateModifiedEvent(this, emailTemplate.getUuid()));
        return new ResultResponseModel<>(new CreateEmailTemplateResponse());
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetEmailGridResponse> getEmailGrid(@Nonnull final GetEmailGridRequest request) {
        Assert.notNull(request);
        return emailGridComponent.getEmailGrid(request);
    }

    @Nonnull
    @Override
    public ResultResponseModel<UpdateEmailTemplateResponse> update(@Nonnull final UpdateEmailTemplateRequest request) {
        assertUpdateEmailTemplateRequest(request);
        final EmailTemplateCreateUpdateModel model = request.getEmailTemplateModel();
        final Map<CommonErrorType, Object> errors = emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForUpdate(model.getName(), model.getUuid(), request.getCompanyUuid());
        if (!errors.isEmpty()) {
            return new ResultResponseModel<>(errors);
        }
        final EmailTemplateDto dto = mapperFacade.map(model, EmailTemplateDto.class);
        final String uuid = model.getUuid();
        emailTemplateService.update(uuid, dto);
        applicationEventPublisher.publishEvent(new EmailTemplateModifiedEvent(this, uuid));
        return new ResultResponseModel<>(new UpdateEmailTemplateResponse());
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetEmailTemplatesByTypeResponse> getByType(@Nonnull final GetEmailTemplatesByTypeRequest request) {
        assertGetByTypeRequest(request);
        final EmailTemplateType type = EmailTemplateType.valueOf(request.getType().toString());
        final List<EmailTemplate> emailTemplates = emailTemplateService.getAllByCompanyUuidAndType(request.getCompanyUuid(), type);
        return new ResultResponseModel<>(new GetEmailTemplatesByTypeResponse(mapperFacade.mapAsList(emailTemplates, EmailTemplateModel.class)));
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetEmailTemplateResponse> get(@Nonnull final GetEmailTemplateRequest request) {
        assertGetEmailTemplateRequest(request);
        final EmailTemplate emailTemplate = emailTemplateService.getByUuid(request.getUuid());
        //TODO: temporary commented to allow call center agents get admin's email template
        //assertEmailTemplateBelongsToUsersCompany(companyUuid, emailTemplate);
        EmailTemplateModel model =mapperFacade.map(emailTemplate, EmailTemplateModel.class);
        return new ResultResponseModel<>(new GetEmailTemplateResponse(model));
    }

    @Nonnull
    @Override
    public ResultResponseModel<ActivateEmailTemplateResponse> activate(@Nonnull final ActivateEmailTemplateRequest request) {
        Assert.notNull(request);
        Assert.hasText(request.getUuid());
        Assert.hasText(request.getCompanyUuid());
        final String uuid = request.getUuid();
        emailTemplateService.activate(uuid);
        applicationEventPublisher.publishEvent(new EmailTemplateModifiedEvent(this, uuid));
        return new ResultResponseModel<>(new ActivateEmailTemplateResponse());
    }

    @Nonnull
    @Override
    public ResultResponseModel<DeactivateEmailTemplateResponse> deactivate(@Nonnull final DeactivateEmailTemplateRequest request) {
        Assert.notNull(request);
        Assert.hasText(request.getUuid());
        Assert.hasText(request.getCompanyUuid());
        final String uuid = request.getUuid();
        emailTemplateService.deactivate(uuid);
        applicationEventPublisher.publishEvent(new EmailTemplateModifiedEvent(this, uuid));
        return new ResultResponseModel<>(new DeactivateEmailTemplateResponse());
    }

    @Nonnull
    @Override
    public ResultResponseModel<RemoveEmailTemplateResponse> remove(@Nonnull final RemoveEmailTemplateRequest request) {
        Assert.notNull(request);
        final String uuid = request.getUuid();
        Assert.hasText(uuid);
        Assert.hasText(request.getCompanyUuid());
        emailTemplateService.remove(uuid);
        applicationEventPublisher.publishEvent(new EmailTemplateRemovedEvent(this, uuid));
        return new ResultResponseModel<>(new RemoveEmailTemplateResponse());
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetActiveEmailTemplatesResponse> getAllActive(@Nonnull final GetActiveEmailTemplatesRequest request) {
        Assert.notNull(request);
        Assert.hasText(request.getCompanyUuid());
        final List<EmailTemplate> emailTemplates = emailTemplateService.getAllActiveByCompanyUuid(request.getCompanyUuid());
        final List<EmailTemplateModel> emailTemplateModels = mapperFacade.mapAsList(emailTemplates, EmailTemplateModel.class);
        return new ResultResponseModel<>(new GetActiveEmailTemplatesResponse(emailTemplateModels));
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetInactiveEmailTemplatesResponse> getAllInactive(@Nonnull final GetInactiveEmailTemplatesRequest request) {
        Assert.notNull(request);
        final List<EmailTemplate> emailTemplates = emailTemplateService.getAllInactiveEmailTemplates();
        final List<EmailTemplateModel> emailTemplatesModels = mapperFacade.mapAsList(emailTemplates, EmailTemplateModel.class);
        return  new ResultResponseModel<>(new GetInactiveEmailTemplatesResponse(emailTemplatesModels));

    }

    @Nonnull
    @Override
    public ResultResponseModel<GetEmailTemplateBySystemTypeResponse> getBySystemType(@Nonnull final GetEmailTemplateBySystemTypeRequest request) {
        assertGetEmailTemplateBySystemTypeRequest(request);
        final SystemEmailTemplateTypeModel systemTypeModel = request.getSystemType();
        final SystemEmailTemplateType systemType = mapperFacade.map(systemTypeModel, SystemEmailTemplateType.class);
        final EmailTemplate emailTemplate = emailTemplateService.getBySystemEmailTemplateType(systemType);
        final EmailTemplateModel emailTemplateModel = mapperFacade.map(emailTemplate, EmailTemplateModel.class);
        return new ResultResponseModel<>(new GetEmailTemplateBySystemTypeResponse(emailTemplateModel));
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetEmailTemplatesForIndexationResponse> getForIndexation(@Nonnull final GetEmailTemplatesForIndexationRequest request) {
        Assert.notNull(request);
        final List<EmailTemplate> emailTemplates = emailTemplateService.getForIndexation(request.getPage(), request.getSize());
        final List<EmailTemplateSearchModel> models = mapperFacade.mapAsList(emailTemplates, EmailTemplateSearchModel.class);
        final Map<EmailTemplateType, List<String>> translations = emailTemplateTypeTranslationComponent.getTranslationsForAllFieldTypes();
        models.forEach(model ->
            model.setTypeTexts(translations.get(mapperFacade.map(model.getType(), EmailTemplateType.class))));
        return new ResultResponseModel<>(new GetEmailTemplatesForIndexationResponse(models));
    }

    @Nonnull
    @Override
    public ResultResponseModel<CopyEmailTemplatesResponse> copy(@Nonnull final CopyEmailTemplatesRequest request) {
        assertCopyEmailTemplatesRequest(request);
        final Map<String, String> oldToNewUuidsMap = emailTemplateService.copy(request.getFromCompanyUuid(), request.getToCompanyUuid());
        return new ResultResponseModel<>(new CopyEmailTemplatesResponse(oldToNewUuidsMap));
    }

    @Nonnull
    @Override
    public ResultResponseModel<CreateCompanyDefaultsEmailTemplateResponse> createCompanyDefaults(@Nonnull final CreateCompanyDefaultsEmailTemplateRequest request) {
        assertCreateCompanyDefaultsEmailTemplateRequest(request);
        final String companyUuid = request.getCompanyUuid();
        final EmailTemplateDto dto = new EmailTemplateDto();
        dto.setName("Reminder for appointment!");
        dto.setSubject("Herinnering: {{LastDispositionCode.Name}} {{CPGender}} {{CPFirstname}} {{CPMiddleName}} {{CPLastname}} {{TimeFollowupStart}}\"");
        dto.setSenderName("Callmonkey Support");
        dto.setSenderEmail("support@callmonkey.com");
        dto.setHtmlContent("Wanneer: {{DateFollowupStart}} om {{TimeFollowupStart}} uur\n" +
                "Wie: {{Agent.UserName}}\n" +
                "Bedrijf:  {{OROrganisation}}\n" +
                "Contactpersoon: {{CPGender}} {{CPFirstname}} {{CPMiddleName}} {{CPLastname}} \n" +
                "Telefoonnummer: {{ORTelephone}} {{CPTelephone1}} {{CPMobile}} \n" +
                "Laatste notitie:\n" +
                "{{DCNotes}}");
        final EmailTemplate emailTemplate = emailTemplateService.create(companyUuid, EmailTemplateType.REMINDER, dto);
        applicationEventPublisher.publishEvent(new EmailTemplateModifiedEvent(this, emailTemplate.getUuid()));
        return new ResultResponseModel<>(new CreateCompanyDefaultsEmailTemplateResponse());
    }
    //endregion

    //region Utility methods
    private void assertCreateCompanyDefaultsEmailTemplateRequest(final CreateCompanyDefaultsEmailTemplateRequest request) {
        Assert.notNull(request);
        Assert.notNull(request.getCompanyUuid());
    }

    private void assertCopyEmailTemplatesRequest(final CopyEmailTemplatesRequest request) {
        Assert.notNull(request);
        Assert.notNull(request.getFromCompanyUuid());
        Assert.notNull(request.getToCompanyUuid());
    }

    private void assertGetEmailTemplateBySystemTypeRequest(final GetEmailTemplateBySystemTypeRequest request) {
        Assert.notNull(request);
        Assert.notNull(request.getSystemType());
    }

    private void assertGetByTypeRequest(final GetEmailTemplatesByTypeRequest request) {
        Assert.notNull(request);
        Assert.hasText(request.getCompanyUuid());
        Assert.notNull(request.getType());
    }

    private void assertGetEmailTemplateRequest(final GetEmailTemplateRequest request) {
        Assert.notNull(request);
        Assert.hasText(request.getCompanyUuid(), "The company uuid should not be blank");
        Assert.hasText(request.getUuid(), "The uuid should not be blank");
    }

    private void assertCreateEmailTemplateRequest(final CreateEmailTemplateRequest request) {
        Assert.notNull(request);
        Assert.hasText(request.getCompanyUuid(), "The company uuid should not be blank");
        assertEmailTemplateModelNotNull(request.getEmailTemplateModel());
        Assert.notNull(request.getEmailTemplateModel().getType(), "The email template type should not be null");
    }

    private void assertUpdateEmailTemplateRequest(final UpdateEmailTemplateRequest request) {
        Assert.notNull(request);
        assertEmailTemplateModelNotNull(request.getEmailTemplateModel());
        Assert.notNull(request.getCompanyUuid());
    }

    private void assertEmailTemplateModelNotNull(final EmailTemplateCreateUpdateModel model) {
        Assert.notNull(model, "Email template model should not be null");
    }
    //endregion
}

package com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.impl;

import com.sfl.coolmonkey.commons.api.model.CommonErrorType;
import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.*;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request.*;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.response.*;
import com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.EmailTemplateFacade;
import com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.component.EmailGridComponent;
import com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.component.EmailTemplateFacadeValidationComponent;
import com.sfl.coolmonkey.emailtemplate.facade.test.AbstractFacadeImplTest;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.EmailTemplateService;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.component.EmailTemplateTypeTranslationComponent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.dto.EmailTemplateDto;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.event.EmailTemplateModifiedEvent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.event.EmailTemplateRemovedEvent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplateType;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.SystemEmailTemplateType;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.SerializationUtils;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.util.*;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.junit.Assert.*;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/23/15
 * Time: 11:06 AM
 */
public class EmailTemplateFacadeImplTest extends AbstractFacadeImplTest {

    //region Test subject and mocks
    @TestSubject
    private EmailTemplateFacade emailTemplateFacade = new EmailTemplateFacadeImpl();

    @Mock
    private EmailTemplateService emailTemplateService;

    @Mock
    private MapperFacade mapperFacade;

    @Mock
    private EmailGridComponent emailGridComponent;

    @Mock
    private EmailTemplateFacadeValidationComponent emailTemplateFacadeValidationComponent;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Mock
    private EmailTemplateTypeTranslationComponent emailTemplateTypeTranslationComponent;
    //endregion

    //region Constructors
    public EmailTemplateFacadeImplTest() {
    }
    //endregion

    //region Test methods

    //region create
    @Test
    public void testCreateWithInvalidArguments() {
        // Test data
        final CreateEmailTemplateRequest validRequest = new CreateEmailTemplateRequest(
                UUID.randomUUID().toString(),
                getHelper().createEmailTemplateCreateUpdateModel()
        );
        CreateEmailTemplateRequest invalidRequest;
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // the request should not be null
        invalidRequest = null;
        try {
            emailTemplateFacade.create(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the request company uuid should not be null
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setCompanyUuid(null);
        try {
            emailTemplateFacade.create(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the request model should not be null
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setEmailTemplateModel(null);
        try {
            emailTemplateFacade.create(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the request model type should not be null
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.getEmailTemplateModel().setType(null);
        try {
            emailTemplateFacade.create(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testCreate() {
        // Test data
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        final String companyUuid = emailTemplate.getCompanyUuid();
        final EmailTemplateCreateUpdateModel model = getHelper().createEmailTemplateCreateUpdateModel();
        final CreateEmailTemplateRequest request = new CreateEmailTemplateRequest(companyUuid, model);
        final EmailTemplateDto dto = getHelper().createEmailTemplateDto();
        final EmailTemplateType type = EmailTemplateType.valueOf(model.getType().toString());
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForCreate(model.getName(), request.getCompanyUuid()))
                .andReturn(new HashMap<>());
        expect(mapperFacade.map(model, EmailTemplateDto.class)).andReturn(dto);
        expect(emailTemplateService.create(companyUuid, type, dto)).andReturn(emailTemplate);
        applicationEventPublisher.publishEvent(isA(EmailTemplateModifiedEvent.class));
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<CreateEmailTemplateResponse> result = emailTemplateFacade.create(request);
        assertNotNull(result);
        assertFalse(result.hasErrors());
        // Verify
        verifyAll();
    }

    @Test
    public void testCreateWithExistingName() {
        // Test data
        final EmailTemplateCreateUpdateModel model = getHelper().createEmailTemplateCreateUpdateModel();
        final String name = model.getName();
        final String companyUuid = UUID.randomUUID().toString();
        final CreateEmailTemplateRequest request = new CreateEmailTemplateRequest(companyUuid, model);
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForCreate(name, companyUuid))
                .andReturn(Collections.singletonMap(CommonErrorType.EMAIL_TEMPLATE_EXISTING_NAME, "Test error message"));
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<CreateEmailTemplateResponse> response = emailTemplateFacade.create(request);
        assertNotNull(response);
        assertTrue(response.hasErrors());
        assertNotNull(response.getErrors().get(CommonErrorType.EMAIL_TEMPLATE_EXISTING_NAME));
        // Verify
        verifyAll();
    }
    //endregion

    //region getEmailGrid
    @Test
    public void testGetEmailGridWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // the request should not be null
        try {
            emailTemplateFacade.getEmailGrid(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetEmailGrid() {
        // Test data
        final String companyUuid = UUID.randomUUID().toString();
        final GetEmailGridRequest request = new GetEmailGridRequest(companyUuid);
        final EmailGridModel emailGridModel = new EmailGridModel();
        emailGridModel.setUuid(UUID.randomUUID().toString());
        final ResultResponseModel<GetEmailGridResponse> response = new ResultResponseModel<>(
                new GetEmailGridResponse(Collections.singletonList(emailGridModel))
        );
        // Reset
        resetAll();
        // Expectations
        expect(emailGridComponent.getEmailGrid(request)).andReturn(response);
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<GetEmailGridResponse> result = emailTemplateFacade.getEmailGrid(request);
        assertNotNull(result);
        assertFalse(result.hasErrors());
        assertNotNull(result.getResponse());
        assertEquals(response, result);
        // Verify
        verifyAll();
    }
    //endregion

    //region update

    @Test
    public void testUpdateWithInvalidArguments() {
        // Test data
        final UpdateEmailTemplateRequest validRequest = new UpdateEmailTemplateRequest(
                UUID.randomUUID().toString(),
                getHelper().createEmailTemplateCreateUpdateModel());
        UpdateEmailTemplateRequest invalidRequest;
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // the request should not be null
        invalidRequest = null;
        try {
            emailTemplateFacade.update(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the email template model should not be null
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setEmailTemplateModel(null);
        try {
            emailTemplateFacade.update(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the email template model company uuid should not be null
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setCompanyUuid(null);
        try {
            emailTemplateFacade.update(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testUpdate() {
        // Test data
        final EmailTemplateCreateUpdateModel model = getHelper().createEmailTemplateCreateUpdateModel();
        final EmailTemplateDto dto = getHelper().createEmailTemplateDto();
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        final String uuid = model.getUuid();
        final String name = model.getName();
        final String companyUuid = UUID.randomUUID().toString();
        final UpdateEmailTemplateRequest request = new UpdateEmailTemplateRequest(companyUuid, model);
        // Reset
        resetAll();
        // Expectations
        expect(mapperFacade.map(model, EmailTemplateDto.class)).andReturn(dto);
        expect(emailTemplateService.update(uuid, dto)).andReturn(emailTemplate);
        expect(emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForUpdate(name, uuid, companyUuid))
                .andReturn(Collections.emptyMap());
        applicationEventPublisher.publishEvent(isA(EmailTemplateModifiedEvent.class));
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<UpdateEmailTemplateResponse> response = emailTemplateFacade.update(request);
        assertNotNull(response);
        assertFalse(response.hasErrors());
        assertNotNull(response.getResponse());
        // Verify
        verifyAll();
    }

    @Test
    public void testUpdateWithExistingName() {
        // Test data
        final EmailTemplateCreateUpdateModel model = getHelper().createEmailTemplateCreateUpdateModel();
        final String uuid = model.getUuid();
        final String name = model.getName();
        final String companyUuid = UUID.randomUUID().toString();
        final UpdateEmailTemplateRequest request = new UpdateEmailTemplateRequest(companyUuid, model);
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForUpdate(name, uuid, companyUuid))
                .andReturn(Collections.singletonMap(CommonErrorType.EMAIL_TEMPLATE_EXISTING_NAME, "Test error message"));
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<UpdateEmailTemplateResponse> response = emailTemplateFacade.update(request);
        assertNotNull(response);
        assertTrue(response.hasErrors());
        assertNotNull(response.getErrors().get(CommonErrorType.EMAIL_TEMPLATE_EXISTING_NAME));
        // Verify
        verifyAll();
    }
    //endregion

    //region getByType
    @Test
    public void testGetByTypeWithInvalidArguments() {
        // Test data
        final GetEmailTemplatesByTypeRequest validReques = new GetEmailTemplatesByTypeRequest(
                UUID.randomUUID().toString(), EmailTemplateTypeModel.INFO_TO_ADDRESS);
        GetEmailTemplatesByTypeRequest invalidRequest;
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        //region the request should not be null
        invalidRequest = null;
        try {
            emailTemplateFacade.getByType(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the company uuid should not be null
        invalidRequest = SerializationUtils.clone(validReques);
        invalidRequest.setCompanyUuid(null);
        try {
            emailTemplateFacade.getByType(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the company uuid should not be empty
        invalidRequest = SerializationUtils.clone(validReques);
        invalidRequest.setCompanyUuid("");
        try {
            emailTemplateFacade.getByType(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the email template type should not be null
        invalidRequest = SerializationUtils.clone(validReques);
        invalidRequest.setType(null);
        try {
            emailTemplateFacade.getByType(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetByType() {
        // Test data
        final String companyUuid = UUID.randomUUID().toString();
        final EmailTemplateType type = EmailTemplateType.APPOINTMENT_EMAIL_TO_ADDRESS;
        final List<EmailTemplate> emailTemplates = Collections.singletonList(
                getHelper().createEmailTemplate());
        final GetEmailTemplatesByTypeRequest request = new GetEmailTemplatesByTypeRequest(
                companyUuid, EmailTemplateTypeModel.valueOf(type.toString()));
        final List<EmailTemplateModel> models = Collections.singletonList(
                getHelper().createEmailTemplateModel());
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.getAllByCompanyUuidAndType(companyUuid, type)).andReturn(emailTemplates);
        expect(mapperFacade.mapAsList(emailTemplates, EmailTemplateModel.class)).andReturn(models);
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<GetEmailTemplatesByTypeResponse> result = emailTemplateFacade.getByType(request);
        assertNotNull(result);
        assertFalse(result.hasErrors());
        assertNotNull(result.getResponse());
        assertEquals(result.getResponse().getModels(), models);
        // Verify
        verifyAll();
    }
    //endregion

    //region get
    @Test
    public void testGetWithInvalidArguments() {
        // Test data
        final GetEmailTemplateRequest validRequest = new GetEmailTemplateRequest(
                UUID.randomUUID().toString(), UUID.randomUUID().toString());
        GetEmailTemplateRequest invalidRequest;
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // the request should not be null
        invalidRequest = null;
        try {
            emailTemplateFacade.get(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the company uuid should not be null
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setCompanyUuid(null);
        try {
            emailTemplateFacade.get(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the company uuid should not be empty
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setCompanyUuid("");
        try {
            emailTemplateFacade.get(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the uuid should not be null
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setUuid(null);
        try {
            emailTemplateFacade.get(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the uuid should not be empty
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setUuid("");
        try {
            emailTemplateFacade.get(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    //TODO: temporary commented to allow call center agents get admin's email template
    /*@Test
    public void testGetWhenEmailTemplateDoesNotBelongsToAppropriateCompany() {
        // Test data
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        emailTemplate.setCompanyUuid("differs_from_request");
        final GetEmailTemplateRequest request = new GetEmailTemplateRequest(
                UUID.randomUUID().toString(), UUID.randomUUID().toString());
        final String uuid = request.getUuid();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.getByUuid(uuid)).andReturn(emailTemplate);
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailTemplateFacade.get(request);
            fail("Exception should be thrown");
        } catch (final FacadeRuntimeException ignore) {
        }
        // Verify
        verifyAll();
    }*/

    @Test
    public void testGet() {
        // Test data
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        final String uuid = emailTemplate.getUuid();
        final String companyUuid = emailTemplate.getCompanyUuid();
        final GetEmailTemplateRequest request = new GetEmailTemplateRequest(uuid, companyUuid);
        final EmailTemplateModel model = getHelper().createEmailTemplateModel();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.getByUuid(uuid)).andReturn(emailTemplate);
        expect(mapperFacade.map(emailTemplate, EmailTemplateModel.class)).andReturn(model);
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<GetEmailTemplateResponse> result = emailTemplateFacade.get(request);
        assertNotNull(result);
        assertEquals(model, result.getResponse().getModel());
        // Verify
        verifyAll();
    }

    @Test
    public void testGetWhenAttachmentUuidsIsNull() {
        // Test data
        final List<AttachmentModel> attachmentModels = Arrays.asList(
                getHelper().createAttachmentModel(),
                getHelper().createAttachmentModel()
        );
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        emailTemplate.setAttachmentUuids(null);
        final GetEmailTemplateRequest request = new GetEmailTemplateRequest(
                UUID.randomUUID().toString(),
                emailTemplate.getCompanyUuid());
        final String uuid = request.getUuid();
        final EmailTemplateModel model = getHelper().createEmailTemplateModel();
        model.setAttachments(attachmentModels);
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.getByUuid(uuid)).andReturn(emailTemplate);
        expect(mapperFacade.map(emailTemplate, EmailTemplateModel.class)).andReturn(model);
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<GetEmailTemplateResponse> response = emailTemplateFacade.get(request);
        assertNotNull(response);
        assertFalse(response.hasErrors());
        assertNotNull(response.getResponse());
        assertEquals(model, response.getResponse().getModel());
        // Verify
        verifyAll();
    }
    //endregion

    //region activate
    @Test
    public void testActivateWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // request should not be null
        try {
            emailTemplateFacade.activate(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testActivate() {
        // Test data
        final String uuid = UUID.randomUUID().toString();
        final String companyUuid = UUID.randomUUID().toString();
        final ActivateEmailTemplateRequest request = new ActivateEmailTemplateRequest(companyUuid, uuid);
        // the emailTemplate which is belongs to user company
        final EmailTemplate before = getHelper().createEmailTemplate();
        before.setUuid(uuid);
        before.setCompanyUuid(companyUuid);
        final EmailTemplate after = getHelper().createEmailTemplate();
        after.setUuid(uuid);
        after.setCompanyUuid(companyUuid);
        after.setActive(true);
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.activate(uuid)).andReturn(after);
        applicationEventPublisher.publishEvent(isA(EmailTemplateModifiedEvent.class));
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<ActivateEmailTemplateResponse> response = emailTemplateFacade.activate(request);
        assertNotNull(response);
        assertNotNull(response.getResponse());
        assertFalse(response.hasErrors());
        // Verify
        verifyAll();
    }
    //endregion

    //region deactivate
    @Test
    public void testDeactivateWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // request should not be null
        try {
            emailTemplateFacade.deactivate(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testDeactivate() {
        // Test data
        final String uuid = UUID.randomUUID().toString();
        final String companyUuid = UUID.randomUUID().toString();
        final DeactivateEmailTemplateRequest request = new DeactivateEmailTemplateRequest(companyUuid, uuid);
        // the emailTemplate which is belongs to user company
        final EmailTemplate before = getHelper().createEmailTemplate();
        before.setUuid(uuid);
        before.setCompanyUuid(companyUuid);
        final EmailTemplate after = getHelper().createEmailTemplate();
        after.setUuid(uuid);
        after.setCompanyUuid(companyUuid);
        after.setActive(false);
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.deactivate(uuid)).andReturn(after);
        applicationEventPublisher.publishEvent(isA(EmailTemplateModifiedEvent.class));
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<DeactivateEmailTemplateResponse> response = emailTemplateFacade.deactivate(request);
        assertNotNull(response);
        assertNotNull(response.getResponse());
        assertFalse(response.hasErrors());
        // Verify
        verifyAll();
    }
    //endregion

    //region remove
    @Test
    public void testRemoveWithInvalidArguments() {
        // Test data
        final String companyUuid = UUID.randomUUID().toString();
        final String uuid = UUID.randomUUID().toString();
        final RemoveEmailTemplateRequest validRequest = new RemoveEmailTemplateRequest(companyUuid, uuid);
        RemoveEmailTemplateRequest invalidRequest;
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // request should not be null
        invalidRequest = null;
        try {
            emailTemplateFacade.remove(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // request uuid should not be null
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setUuid(null);
        try {
            emailTemplateFacade.remove(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // request company uuid should not be null
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setCompanyUuid(null);
        try {
            emailTemplateFacade.remove(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testRemove() {
        // Test data
        final String companyUuid = UUID.randomUUID().toString();
        final String uuid = UUID.randomUUID().toString();
        final RemoveEmailTemplateRequest request = new RemoveEmailTemplateRequest(companyUuid, uuid);
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.remove(uuid)).andReturn(emailTemplate);
        applicationEventPublisher.publishEvent(isA(EmailTemplateRemovedEvent.class));
        // Replay
        replayAll();
        // Run test scenario
        // request should not be null
        final ResultResponseModel<RemoveEmailTemplateResponse> response = emailTemplateFacade.remove(request);
        assertNotNull(response);
        assertFalse(response.hasErrors());
        assertNotNull(response.getResponse());
        // Verify
        verifyAll();
    }
    //endregion

    //region getAllActive
    @Test
    public void testGetAllActiveWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // the request should not be null
        try {
            emailTemplateFacade.getAllActive(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetAllActive() {
        // Test data
        final String companyUuid = UUID.randomUUID().toString();
        final GetActiveEmailTemplatesRequest request = new GetActiveEmailTemplatesRequest(companyUuid);
        final List<EmailTemplate> emailTemplates = Collections.singletonList(
                getHelper().createEmailTemplate()
        );
        final List<EmailTemplateModel> emailTemplateModels = Collections.singletonList(
                getHelper().createEmailTemplateModel()
        );
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.getAllActiveByCompanyUuid(companyUuid)).andReturn(emailTemplates);
        expect(mapperFacade.mapAsList(emailTemplates, EmailTemplateModel.class)).andReturn(emailTemplateModels);
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<GetActiveEmailTemplatesResponse> result = emailTemplateFacade.getAllActive(request);
        assertNotNull(result);
        assertFalse(result.hasErrors());
        assertNotNull(result.getResponse());
        assertEquals(emailTemplateModels, result.getResponse().getGrid());
        // Verify
        verifyAll();
    }
    //endregion

    //region getBySystemType
    @Test
    public void testGetBySystemTypeWithInvalidArguments() {
        // Test data
        final GetEmailTemplateBySystemTypeRequest validRequest = new GetEmailTemplateBySystemTypeRequest(
                SystemEmailTemplateTypeModel.ACCEPT_PERMISSION_REQUEST
        );
        GetEmailTemplateBySystemTypeRequest invalidRequest;
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailTemplateFacade.getBySystemType(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setSystemType(null);
        try {
            emailTemplateFacade.getBySystemType(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetBySystemType() {
        // Test data
        final String companyUuid = UUID.randomUUID().toString();
        final SystemEmailTemplateTypeModel systemTypeModel = SystemEmailTemplateTypeModel.ACCEPT_PERMISSION_REQUEST;
        final SystemEmailTemplateType systemType = SystemEmailTemplateType.ACCEPT_PERMISSION_REQUEST;
        final GetEmailTemplateBySystemTypeRequest request = new GetEmailTemplateBySystemTypeRequest(
                systemTypeModel
        );
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        final EmailTemplateModel emailTemplateModel = getHelper().createEmailTemplateModel();
        // Reset
        resetAll();
        // Expectations
        expect(mapperFacade.map(systemTypeModel, SystemEmailTemplateType.class)).andReturn(systemType);
        expect(emailTemplateService.getBySystemEmailTemplateType(systemType)).andReturn(emailTemplate);
        expect(mapperFacade.map(emailTemplate, EmailTemplateModel.class)).andReturn(emailTemplateModel);
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<GetEmailTemplateBySystemTypeResponse> response = emailTemplateFacade.getBySystemType(request);
        // Verify
        verifyAll();
        assertNotNull(response);
        assertFalse(response.hasErrors());
        assertNotNull(response.getResponse());
        assertEquals(emailTemplateModel, response.getResponse().getEmailTemplate());
    }
    //endregion

    //region getForIndexation
    @Test
    public void testGetForIndexationWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailTemplateFacade.getForIndexation(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetForIndexation() {
        // Test data
        final int page = 0;
        final int size = 500;
        final GetEmailTemplatesForIndexationRequest request = new GetEmailTemplatesForIndexationRequest(page, size);
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        final List<EmailTemplate> emailTemplates = Collections.singletonList(emailTemplate);
        final List<EmailTemplateSearchModel> models = Collections.singletonList(getHelper().createEmailTemplateSearchModel());
        Map<EmailTemplateType, List<String>> translations = new HashMap<>();
        translations.put(emailTemplate.getType(), Collections.singletonList(UUID.randomUUID().toString()));
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.getForIndexation(page, size)).andReturn(emailTemplates);
        expect(mapperFacade.mapAsList(emailTemplates, EmailTemplateSearchModel.class)).andReturn(models);
        expect(emailTemplateTypeTranslationComponent.getTranslationsForAllFieldTypes()).andReturn(translations);
        expect(mapperFacade.map(models.get(0).getType(), EmailTemplateType.class)).andReturn(emailTemplate.getType());
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<GetEmailTemplatesForIndexationResponse> result = emailTemplateFacade.getForIndexation(request);
        assertNotNull(result);
        assertFalse(result.hasErrors());
        assertEquals(models, result.getResponse().getGrid());
        // Verify
        verifyAll();
    }
    //endregion

    //region copy
    @Test
    public void testCopyWithInvalidArguments() {
        // Test data
        final CopyEmailTemplatesRequest validRequest = new CopyEmailTemplatesRequest(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
        CopyEmailTemplatesRequest invalidRequest;
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailTemplateFacade.copy(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setFromCompanyUuid(null);
        try {
            emailTemplateFacade.copy(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setToCompanyUuid(null);
        try {
            emailTemplateFacade.copy(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testCopy() {
        // Test data
        final String fromCompanyUuid = UUID.randomUUID().toString();
        final String toCompanyUuid = UUID.randomUUID().toString();
        final CopyEmailTemplatesRequest request = new CopyEmailTemplatesRequest(
                fromCompanyUuid,
                toCompanyUuid
        );
        final Map<String, String> map = new HashMap<>();
        map.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.copy(fromCompanyUuid, toCompanyUuid)).andReturn(map);
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<CopyEmailTemplatesResponse> result = emailTemplateFacade.copy(request);
        // Verify
        verifyAll();
        assertNotNull(result);
        assertFalse(result.hasErrors());
        assertNotNull(result.getResponse());
        assertEquals(map, result.getResponse().getOriginalToNewUuidsMap());
    }
    //endregion

    //region createCompanyDefaults
    @Test
    public void testCreateCompanyDefaultsWithInvalidArguments() {
        // Test data
        final CreateCompanyDefaultsEmailTemplateRequest validRequest = new CreateCompanyDefaultsEmailTemplateRequest(
                UUID.randomUUID().toString()
        );
        CreateCompanyDefaultsEmailTemplateRequest invalidRequest;
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailTemplateFacade.createCompanyDefaults(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setCompanyUuid(null);
        try {
            emailTemplateFacade.createCompanyDefaults(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testCreateCompanyDefaults() {
        // Test data
        final String companyUuid = UUID.randomUUID().toString();
        final EmailTemplateDto expectedDto = new EmailTemplateDto();
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        expectedDto.setAttachmentUuids(null);
        expectedDto.setBccEmails(null);
        expectedDto.setCcEmails(null);
        expectedDto.setHtmlContent("Wanneer: {{DateFollowupStart}} om {{TimeFollowupStart}} uur\n" +
                "Wie: {{Agent.UserName}}\n" +
                "Bedrijf:  {{OROrganisation}}\n" +
                "Contactpersoon: {{CPGender}} {{CPFirstname}} {{CPMiddleName}} {{CPLastname}} \n" +
                "Telefoonnummer: {{ORTelephone}} {{CPTelephone1}} {{CPMobile}} \n" +
                "Laatste notitie:\n" +
                "{{DCNotes}}");
        expectedDto.setName("Reminder for appointment!");
        expectedDto.setReplyEmail(null);
        expectedDto.setSenderEmail("support@callmonkey.com");
        expectedDto.setSenderName("Callmonkey Support");
        expectedDto.setSubject("Herinnering: {{LastDispositionCode.Name}} {{CPGender}} {{CPFirstname}} {{CPMiddleName}} {{CPLastname}} {{TimeFollowupStart}}\"");
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.create(companyUuid, EmailTemplateType.REMINDER, expectedDto)).andReturn(emailTemplate);
        applicationEventPublisher.publishEvent(isA(EmailTemplateModifiedEvent.class));
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<CreateCompanyDefaultsEmailTemplateResponse> result = emailTemplateFacade.createCompanyDefaults(new CreateCompanyDefaultsEmailTemplateRequest(companyUuid));
        // Verify
        verifyAll();
        assertNotNull(result);
        assertFalse(result.hasErrors());
        assertNotNull(result.getResponse());
    }
    //endregion

    //endregion
}
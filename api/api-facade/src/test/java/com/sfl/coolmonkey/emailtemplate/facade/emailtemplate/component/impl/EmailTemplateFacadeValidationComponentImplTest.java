package com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.component.impl;

import com.sfl.coolmonkey.commons.api.model.CommonErrorType;
import com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.component.EmailTemplateFacadeValidationComponent;
import com.sfl.coolmonkey.emailtemplate.facade.test.AbstractFacadeImplTest;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.EmailTemplateService;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;

import java.util.Map;
import java.util.UUID;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;

/**
 * User: Ruben Vardanyan
 * Company: SFL LLC
 * Date: 1/8/2016
 * Time: 2:35 PM
 */
public class EmailTemplateFacadeValidationComponentImplTest extends AbstractFacadeImplTest {

    //region Test subject and mocks
    @TestSubject
    private EmailTemplateFacadeValidationComponent emailTemplateFacadeValidationComponent = new EmailTemplateFacadeValidationComponentImpl();

    @Mock
    private EmailTemplateService emailTemplateService;
    //endregion

    //region Constructors
    public EmailTemplateFacadeValidationComponentImplTest() {
    }
    //endregion

    //region Test methods

    //region assertEmailTemplateNameIsAvailableForCreate
    @Test
    public void testAssertEmailTemplateNameIsAvailableForCreateWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForCreate(null, UUID.randomUUID().toString());
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForCreate("name", null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testAssertEmailTemplateNameIsAvailableForCreateWhenEmailTemplateNameAlreadyExists() {
        // Test data
        final String companyUuid = UUID.randomUUID().toString();
        final String name = "Test";
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.checkIfEmailTemplateExistForNameAndCompany(name, companyUuid))
                .andReturn(true).once();
        // Replay
        replayAll();
        // Run test scenario
        Map<CommonErrorType, Object> errors = emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForCreate(name, companyUuid);
        assertNotNull(errors);
        assertFalse(errors.isEmpty());
        assertTrue(errors.containsKey(CommonErrorType.EMAIL_TEMPLATE_EXISTING_NAME));
        // Verify
        verifyAll();
    }

    @Test
    public void testAssertEmailTemplateNameIsAvailableForCreate() {
        // Test data
        final String companyUuid = UUID.randomUUID().toString();
        final String name = "Test";
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.checkIfEmailTemplateExistForNameAndCompany(name, companyUuid))
                .andReturn(false);
        // Replay
        replayAll();
        // Run test scenario
        Map<CommonErrorType, Object> errors = emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForCreate(name, companyUuid);
        assertNotNull(errors);
        assertTrue(errors.isEmpty());
        // Verify
        verifyAll();
    }
    //endregion

    //region assertEmailTemplateNameIsAvailableForUpdate
    @Test
    public void testAssertEmailTemplateNameIsAvailableForUpdateWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForUpdate(null, UUID.randomUUID().toString(), UUID.randomUUID().toString());
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForUpdate("name", null, UUID.randomUUID().toString());
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForUpdate("name", UUID.randomUUID().toString(), null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testAssertEmailTemplateNameIsAvailableForUpdateWhenEmailTemplateNameAlreadyExists() {
        // Test data
        final String name = "Test";
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        emailTemplate.setName("Other name");
        final String uuid = emailTemplate.getUuid();
        final String companyUuid = emailTemplate.getCompanyUuid();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.getByUuid(uuid)).andReturn(emailTemplate);
        expect(emailTemplateService.checkIfEmailTemplateExistForNameAndCompany(name, companyUuid)).andReturn(true);
        // Replay
        replayAll();
        // Run test scenario
        Map<CommonErrorType, Object> errors = emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForUpdate(name, uuid, companyUuid);
        assertNotNull(errors);
        assertFalse(errors.isEmpty());
        assertTrue(errors.containsKey(CommonErrorType.EMAIL_TEMPLATE_EXISTING_NAME));
        // Verify
        verifyAll();
    }

    @Test
    public void testAssertEmailTemplateNameIsAvailableForUpdateWhenNameNotUpdated() {
        // Test data
        final String name = "Test";
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        emailTemplate.setName(name);
        final String uuid = emailTemplate.getUuid();
        final String companyUuid = emailTemplate.getCompanyUuid();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.getByUuid(uuid)).andReturn(emailTemplate);
        // Replay
        replayAll();
        // Run test scenario
        final Map<CommonErrorType, Object> errors = emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForUpdate(name, uuid, companyUuid);
        assertNotNull(errors);
        assertTrue(errors.isEmpty());
        // Verify
        verifyAll();
    }

    @Test
    public void testAssertEmailTemplateNameIsAvailableForUpdateWhenNameUpdated() {
        // Test data
        final String name = "Test";
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        emailTemplate.setName("Updated name");
        final String uuid = emailTemplate.getUuid();
        final String companyUuid = emailTemplate.getCompanyUuid();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.getByUuid(uuid)).andReturn(emailTemplate);
        expect(emailTemplateService.checkIfEmailTemplateExistForNameAndCompany(name, companyUuid)).andReturn(false);
        // Replay
        replayAll();
        // Run test scenario
        final Map<CommonErrorType, Object> errors = emailTemplateFacadeValidationComponent.validateEmailTemplateNameIsUniqueForUpdate(name, uuid, companyUuid);
        assertNotNull(errors);
        assertTrue(errors.isEmpty());
        // Verify
        verifyAll();
    }
    //endregion

    //endregion
}
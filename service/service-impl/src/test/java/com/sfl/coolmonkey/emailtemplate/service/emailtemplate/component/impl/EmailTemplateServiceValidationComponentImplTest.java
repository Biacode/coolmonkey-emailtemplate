package com.sfl.coolmonkey.emailtemplate.service.emailtemplate.component.impl;

import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.component.EmailTemplateServiceValidationComponent;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.dto.EmailTemplateDto;
import com.sfl.coolmonkey.emailtemplate.service.helper.CommonTestHelper;
import com.sfl.coolmonkey.emailtemplate.service.test.AbstractServiceImplTest;
import org.apache.commons.lang3.SerializationUtils;
import org.easymock.TestSubject;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.fail;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/28/15
 * Time: 3:05 PM
 */
public class EmailTemplateServiceValidationComponentImplTest extends AbstractServiceImplTest {

    //region Test subject and mocks
    @TestSubject
    private EmailTemplateServiceValidationComponent emailTemplateServiceValidationComponent =
            new EmailTemplateServiceValidationComponentImpl();
    //endregion

    //region Constructors
    public EmailTemplateServiceValidationComponentImplTest() {
    }
    //endregion

    //region Test methods

    //region performEmailTemplateDtoValidations
    @Test
    public void testPerformEmailTemplateDtoValidationsWithInvalidArguments() {
        // Test data
        final CommonTestHelper commonTestHelper = new CommonTestHelper();
        final EmailTemplateDto validDto = commonTestHelper.createEmailTemplateDto();
        EmailTemplateDto invalidDto;
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // the email template dto should not be null
        invalidDto = null;
        try {
            emailTemplateServiceValidationComponent.performEmailTemplateDtoValidations(invalidDto);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the sender name in email template dto should not be null
        invalidDto = SerializationUtils.clone(validDto);
        invalidDto.setSenderName(null);
        try {
            emailTemplateServiceValidationComponent.performEmailTemplateDtoValidations(invalidDto);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the sender email in email template dto should not be null
        invalidDto = SerializationUtils.clone(validDto);
        invalidDto.setSenderEmail(null);
        try {
            emailTemplateServiceValidationComponent.performEmailTemplateDtoValidations(invalidDto);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the attachment uuids in dto should not contain null
        invalidDto = SerializationUtils.clone(validDto);
        invalidDto.setAttachmentUuids(Collections.singletonList(null));
        try {
            emailTemplateServiceValidationComponent.performEmailTemplateDtoValidations(invalidDto);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testPerformEmailTemplateDtoValidationsWhenAttachmentUuidsIsNotNull() {
        // Test data
        final CommonTestHelper commonTestHelper = new CommonTestHelper();
        final EmailTemplateDto dto = commonTestHelper.createEmailTemplateDto();
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        emailTemplateServiceValidationComponent.performEmailTemplateDtoValidations(dto);
        // Verify
        verifyAll();
    }

    @Test
    public void testPerformEmailTemplateDtoValidationsWhenAttachmentUuidsIsNull() {
        // Test data
        final CommonTestHelper commonTestHelper = new CommonTestHelper();
        final EmailTemplateDto dto = commonTestHelper.createEmailTemplateDto();
        dto.setAttachmentUuids(null);
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        emailTemplateServiceValidationComponent.performEmailTemplateDtoValidations(dto);
        // Verify
        verifyAll();
    }
    //endregion

    //endregion
}
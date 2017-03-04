package com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.component.impl;

import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request.GetEmailGridRequest;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.response.GetEmailGridResponse;
import com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.component.EmailGridComponent;
import com.sfl.coolmonkey.emailtemplate.facade.test.AbstractFacadeImplTest;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.EmailTemplateService;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import org.apache.commons.lang3.SerializationUtils;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 10/27/15
 * Time: 8:34 PM
 */
public class EmailGridComponentImplTest extends AbstractFacadeImplTest {

    //region Test subject and mocks
    @TestSubject
    private EmailGridComponent emailGridComponent = new EmailGridComponentImpl();

    @Mock
    private EmailTemplateService emailTemplateService;
    //endregion

    //region Constructors
    public EmailGridComponentImplTest() {
    }
    //endregion

    //region Test methods
    @Test
    public void testGetEmailGridWithInvalidArguments() {
        // Test data
        final GetEmailGridRequest validRequest = new GetEmailGridRequest(UUID.randomUUID().toString());
        GetEmailGridRequest invalidRequest;
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // Get email grid request should not be null
        invalidRequest = null;
        try {
            emailGridComponent.getEmailGrid(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Get email grid request company uuid should not be null
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setCompanyUuid(null);
        try {
            emailGridComponent.getEmailGrid(invalidRequest);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Get email grid request company uuid should not be empty
        invalidRequest = SerializationUtils.clone(validRequest);
        invalidRequest.setCompanyUuid("  ");
        try {
            emailGridComponent.getEmailGrid(invalidRequest);
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
        final List<EmailTemplate> emailTemplates = Arrays.asList(
                getHelper().createEmailTemplate(),
                getHelper().createEmailTemplate()
        );
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateService.getAllByCompanyUuid(companyUuid)).andReturn(emailTemplates);
        // Replay
        replayAll();
        // Run test scenario
        final ResultResponseModel<GetEmailGridResponse> response = emailGridComponent.getEmailGrid(request);
        assertNotNull(response);
        assertFalse(response.hasErrors());
        assertNotNull(response.getResponse());
        assertNotNull(response.getResponse().getGrid());
        assertEquals(emailTemplates.size(), response.getResponse().getGrid().size());
        for (int i = 0; i != emailTemplates.size(); i++) {
            assertEquals(emailTemplates.get(i).getUuid(), response.getResponse().getGrid().get(i).getUuid());
            assertEquals(emailTemplates.get(i).getSubject(), response.getResponse().getGrid().get(i).getSubject());
            assertEquals(emailTemplates.get(i).getType().toString(), response.getResponse().getGrid().get(i).getType().toString());
            assertEquals(emailTemplates.get(i).getName(), response.getResponse().getGrid().get(i).getName());
            assertEquals(emailTemplates.get(i).getAttachmentUuids().size(), response.getResponse().getGrid().get(i).getNumberOfAttachments());
            assertEquals(emailTemplates.get(i).getSenderEmail(), response.getResponse().getGrid().get(i).getSenderEmail());
            assertEquals(emailTemplates.get(i).getSenderName(), response.getResponse().getGrid().get(i).getSenderName());
            assertEquals(emailTemplates.get(i).isActive(), response.getResponse().getGrid().get(i).isActive());
        }
        assertEquals(0, response.getResponse().getGrid().get(0).getUsedOnDispositionCodes());
        assertEquals(0, response.getResponse().getGrid().get(1).getUsedOnDispositionCodes());
        // Verify
        verifyAll();
    }
    //endregion

    //region Utility methods
    //endregion
}
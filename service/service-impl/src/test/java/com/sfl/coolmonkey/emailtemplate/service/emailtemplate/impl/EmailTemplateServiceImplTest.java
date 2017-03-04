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
import com.sfl.coolmonkey.emailtemplate.service.test.AbstractServiceImplTest;
import org.bson.types.ObjectId;
import org.easymock.Capture;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/22/15
 * Time: 8:02 PM
 */
public class EmailTemplateServiceImplTest extends AbstractServiceImplTest {

    //region Test subject and mocks
    @TestSubject
    private EmailTemplateService emailTemplateService = new EmailTemplateServiceImpl();

    @Mock
    private EmailTemplateRepository emailTemplateRepository;

    @Mock
    private EmailTemplateServiceValidationComponent emailTemplateServiceValidationComponent;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    //endregion

    //region Constructors
    public EmailTemplateServiceImplTest() {
    }
    //endregion

    //region Test methods

    //region getById
    @Test
    public void testGetByIdWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailTemplateService.getById(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetByIdWhenEmailTemplateDoesNotExists() {
        // Test data
        final ObjectId id = new ObjectId();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findOne(eq(id))).andReturn(null);
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailTemplateService.getById(id);
            fail("Exception should be thrown");
        } catch (final ServicesRuntimeException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetById() {
        // Test data
        final ObjectId id = new ObjectId();
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findOne(eq(id))).andReturn(emailTemplate);
        // Replay
        replayAll();
        // Run test scenario
        final EmailTemplate result = emailTemplateService.getById(id);
        assertNotNull(result);
        assertEquals(emailTemplate, result);
        // Verify
        verifyAll();
    }
    //endregion

    //region getByUuid
    @Test
    public void testGetByUuidWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // the uuid should not be null
        try {
            emailTemplateService.getByUuid(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetByUuidWhenEmailTemplateDoesNotExists() {
        // Test data
        final String uuid = UUID.randomUUID().toString();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findByUuid(eq(uuid))).andReturn(null);
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailTemplateService.getByUuid(uuid);
            fail("Exception should be thrown");
        } catch (final ServicesRuntimeException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetByUuid() {
        // Test data
        final String uuid = UUID.randomUUID().toString();
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findByUuid(eq(uuid))).andReturn(emailTemplate);
        // Replay
        replayAll();
        // Run test scenario
        final EmailTemplate result = emailTemplateService.getByUuid(uuid);
        assertNotNull(result);
        assertEquals(emailTemplate, result);
        // Verify
        verifyAll();
    }
    //endregion

    //region create
    @Test
    public void testCreateWithInvalidArguments() {
        // Test data
        final String validCompanyUuid = UUID.randomUUID().toString();
        final EmailTemplateType validType = EmailTemplateType.APPOINTMENT_EMAIL_TO_ADDRESS;
        final EmailTemplateDto validDto = getHelper().createEmailTemplateDto();
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // the company uuid should not be null
        try {
            emailTemplateService.create(null, validType, validDto);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the company uuid should not be empty
        try {
            emailTemplateService.create(null, validType, validDto);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the email template type should not be null
        try {
            emailTemplateService.create(validCompanyUuid, null, validDto);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testCreate() {
        // Test data
        final String companyUuid = UUID.randomUUID().toString();
        final EmailTemplateType type = EmailTemplateType.APPOINTMENT_EMAIL_TO_ADDRESS;
        final EmailTemplateDto dto = getHelper().createEmailTemplateDto();
        // Reset
        resetAll();
        // Expectations
        emailTemplateServiceValidationComponent.performEmailTemplateDtoValidations(dto);
        expect(emailTemplateRepository.save(isA(EmailTemplate.class))).andAnswer(() -> (EmailTemplate) getCurrentArguments()[0]);
        // Replay
        replayAll();
        // Run test scenario
        final EmailTemplate emailTemplate = emailTemplateService.create(companyUuid, type, dto);
        assertEmailTemplateDto(dto, emailTemplate);
        // Verify
        verifyAll();
    }
    //endregion

    //region getAllByCompany
    @Test
    public void testGetAllNotRemovedByCompanyUuidWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // company uuid should not be null
        try {
            emailTemplateService.getAllByCompanyUuid(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetAllNotRemovedByCompanyUuid() {
        // Test data
        final String companyUuid = UUID.randomUUID().toString();
        final List<EmailTemplate> emailTemplates = Collections.singletonList(getHelper().createEmailTemplate());
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findByCompanyUuidAndRemovedIsNull(companyUuid)).andReturn(emailTemplates);
        // Replay
        replayAll();
        // Run test scenario
        final List<EmailTemplate> results = emailTemplateService.getAllByCompanyUuid(companyUuid);
        assertNotNull(results);
        assertEquals(emailTemplates, results);
        // Verify
        verifyAll();
    }
    //endregion

    //region getAllActiveByCompanyUuid
    @Test
    public void testGetAllNotRemovedAndActiveByCompanyUuidWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // company uuid should not be null
        try {
            emailTemplateService.getAllActiveByCompanyUuid(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetAllNotRemovedAndActiveByCompanyUuid() {
        // Test data
        final String companyUuid = UUID.randomUUID().toString();
        final List<EmailTemplate> emailTemplates = Collections.singletonList(getHelper().createEmailTemplate());
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findByCompanyUuidAndRemovedIsNullAndActiveTrue(companyUuid)).andReturn(emailTemplates);
        // Replay
        replayAll();
        // Run test scenario
        final List<EmailTemplate> results = emailTemplateService.getAllActiveByCompanyUuid(companyUuid);
        assertNotNull(results);
        assertEquals(emailTemplates, results);
        // Verify
        verifyAll();
    }
    //endregion

    //region update
    @Test
    public void testUpdateWithInvalidArguments() {
        // Test data
        final EmailTemplateDto dto = getHelper().createEmailTemplateDto();
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // the uuid should not be null
        try {
            emailTemplateService.update(null, dto);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testUpdate() {
        // Test data
        final EmailTemplateDto dto = getHelper().createEmailTemplateDto();
        final String uuid = UUID.randomUUID().toString();
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        dto.updateDomainEntityProperties(emailTemplate);
        // Reset
        resetAll();
        // Expectations
        emailTemplateServiceValidationComponent.performEmailTemplateDtoValidations(dto);
        expect(emailTemplateRepository.findByUuid(uuid)).andReturn(emailTemplate);
        expect(emailTemplateRepository.save(isA(EmailTemplate.class))).andAnswer(() -> (EmailTemplate) getCurrentArguments()[0]);
        // Replay
        replayAll();
        // Run test scenario
        final EmailTemplate result = emailTemplateService.update(uuid, dto);
        assertTrue(result.getUpdated() != null);
        assertEmailTemplateDto(dto, result);
        // Verify
        verifyAll();
    }
    //endregion

    //region getAllByCompanyUuidAndType
    @Test
    public void testGetAllByCompanyUuidAndTypeWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // the company uuid should not be null
        try {
            emailTemplateService.getAllByCompanyUuidAndType(null, EmailTemplateType.INFO_TO_ADDRESS);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // the type should not be null
        try {
            emailTemplateService.getAllByCompanyUuidAndType(UUID.randomUUID().toString(), null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testNameGetAllByCompanyUuidAndType() {
        // Test data
        final List<EmailTemplate> emailTemplates = Collections.singletonList(getHelper().createEmailTemplate());
        final String companyUuid = emailTemplates.get(0).getCompanyUuid();
        final EmailTemplateType type = emailTemplates.get(0).getType();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findByCompanyUuidAndTypeAndRemovedIsNull(companyUuid, type)).andReturn(emailTemplates);
        // Replay
        replayAll();
        // Run test scenario
        final List<EmailTemplate> results = emailTemplateService.getAllByCompanyUuidAndType(companyUuid, type);
        assertNotNull(results);
        assertEquals(emailTemplates, results);
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
        // emailTemplate uuid should not be null
        try {
            emailTemplateService.activate(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testActivate() {
        // Test data
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        final String uuid = emailTemplate.getUuid();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findByUuid(uuid)).andReturn(emailTemplate).once();
        expect(emailTemplateRepository.save(isA(EmailTemplate.class))).andAnswer(() -> (EmailTemplate) getCurrentArguments()[0]).once();
        // Replay
        replayAll();
        // Run test scenario
        final EmailTemplate result = emailTemplateService.activate(uuid);
        assertNotNull(result);
        assertTrue(result.isActive());
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
        // emailTemplate uuid should not be null
        try {
            emailTemplateService.deactivate(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testDeactivate() {
        // Test data
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        final String uuid = emailTemplate.getUuid();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findByUuid(uuid)).andReturn(emailTemplate).once();
        expect(emailTemplateRepository.save(isA(EmailTemplate.class))).andAnswer(() -> (EmailTemplate) getCurrentArguments()[0]).once();
        // Replay
        replayAll();
        // Run test scenario
        final EmailTemplate result = emailTemplateService.deactivate(uuid);
        assertNotNull(result);
        assertFalse(result.isActive());
        // Verify
        verifyAll();
    }
    //endregion

    //region remove
    @Test
    public void testRemoveWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        // emailTemplate uuid should not be null
        try {
            emailTemplateService.remove(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testRemove() {
        // Test data
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        final String uuid = emailTemplate.getUuid();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findByUuid(uuid)).andReturn(emailTemplate).once();
        expect(emailTemplateRepository.save(isA(EmailTemplate.class))).andAnswer(() -> (EmailTemplate) getCurrentArguments()[0]).once();
        // Replay
        replayAll();
        // Run test scenario
        final EmailTemplate result = emailTemplateService.remove(uuid);
        assertNotNull(result);
        assertNotNull(result.getRemoved());
        // Verify
        verifyAll();
    }
    //endregion

    //region checkIfEmailTemplateExistForNameAndCompany

    @Test
    public void testDoesEmailTemplateExistForNameAndCompanyWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailTemplateService.checkIfEmailTemplateExistForNameAndCompany(null, UUID.randomUUID().toString());
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            emailTemplateService.checkIfEmailTemplateExistForNameAndCompany("name", null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testDoesEmailTemplateExistForNameAndCompanyWhenNotExists() {
        // Test data
        final String name = "name";
        final String companyUuid = UUID.randomUUID().toString();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findByNameIgnoreCaseAndCompanyUuidAndRemovedIsNull(name, companyUuid)).andReturn(null);
        // Replay
        replayAll();
        // Run test scenario
        final boolean exists = emailTemplateService.checkIfEmailTemplateExistForNameAndCompany(name, companyUuid);
        assertFalse(exists);
        // Verify
        verifyAll();
    }

    @Test
    public void testDoesEmailTemplateExistForNameAndCompanyWhenExists() {
        // Test data
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        final String name = emailTemplate.getName();
        final String companyUuid = emailTemplate.getCompanyUuid();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findByNameIgnoreCaseAndCompanyUuidAndRemovedIsNull(name, companyUuid)).andReturn(emailTemplate);
        // Replay
        replayAll();
        // Run test scenario
        final boolean result = emailTemplateService.checkIfEmailTemplateExistForNameAndCompany(emailTemplate.getName(), emailTemplate.getCompanyUuid());
        assertTrue(result);
        // Verify
        verifyAll();
    }

    //endregion

    //region getBySystemEmailTemplateType
    @Test
    public void testGetBySystemEmailTemplateTypeWithInvalidArguments() {
        // Test data
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailTemplateService.getBySystemEmailTemplateType(null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetBySystemEmailTemplateTypeWhenNotFound() {
        // Test data
        final SystemEmailTemplateType systemEmailTemplateType = SystemEmailTemplateType.ADDRESS_IMPORT_FAILED;
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findBySystemEmailTemplateTypeAndRemovedIsNull(systemEmailTemplateType))
                .andReturn(null);
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailTemplateService.getBySystemEmailTemplateType(systemEmailTemplateType);
            fail("Exception should be thrown");
        } catch (final ServicesRuntimeException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetBySystemEmailTemplateType() {
        // Test data
        final SystemEmailTemplateType systemEmailTemplateType = SystemEmailTemplateType.ADDRESS_IMPORT_FAILED;
        final EmailTemplate emailTemplate = getHelper().createEmailTemplate();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findBySystemEmailTemplateTypeAndRemovedIsNull(systemEmailTemplateType))
                .andReturn(emailTemplate);
        // Replay
        replayAll();
        // Run test scenario
        final EmailTemplate result = emailTemplateService.getBySystemEmailTemplateType(systemEmailTemplateType);
        // Verify
        verifyAll();
        assertEquals(emailTemplate, result);
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
            emailTemplateService.getForIndexation(-1, 1);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            emailTemplateService.getForIndexation(1, -1);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testGetForIndexation() {
        // Test data
        final int page = 2;
        final int size = 3;
        final Pageable expectedPageable = new PageRequest(page, size, Sort.Direction.ASC, "created");
        final List<EmailTemplate> orders = Collections.singletonList(getHelper().createEmailTemplate());
        final Page<EmailTemplate> ordersPage = new PageImpl<>(orders);
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findByRemovedIsNull(expectedPageable)).andReturn(ordersPage);
        // Replay
        replayAll();
        // Run test scenario
        final List<EmailTemplate> response = emailTemplateService.getForIndexation(page, size);
        // Verify
        verifyAll();
        assertEquals(orders, response);
    }
    //endregion

    //region copy
    @Test
    public void testCopyWithInvalidArguments() {
        // Test data
        final String validUuid = UUID.randomUUID().toString();
        // Reset
        resetAll();
        // Expectations
        // Replay
        replayAll();
        // Run test scenario
        try {
            emailTemplateService.copy(null, validUuid);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            emailTemplateService.copy(validUuid, null);
            fail("Exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
        }
        // Verify
        verifyAll();
    }

    @Test
    public void testCopy() {
        // Test data
        // Company uuids
        final String fromCompanyUuid = UUID.randomUUID().toString();
        final String toCompanyUuid = UUID.randomUUID().toString();
        // Objects
        final EmailTemplate originalEmailTemplate = getHelper().createEmailTemplate();
        originalEmailTemplate.setId(new ObjectId());
        final List<EmailTemplate> originalEmailTemplates = Collections.singletonList(originalEmailTemplate);
        final Capture<List<EmailTemplate>> newEmailTemplatesCapture = new Capture<>();
        final Capture<EmailTemplateModifiedEvent> eventCapture = new Capture<>();
        // Reset
        resetAll();
        // Expectations
        expect(emailTemplateRepository.findByCompanyUuidAndRemovedIsNull(fromCompanyUuid)).andReturn(originalEmailTemplates);
        expect(emailTemplateRepository.save(capture(newEmailTemplatesCapture))).andAnswer(() -> (List) getCurrentArguments()[0]);
        applicationEventPublisher.publishEvent(capture(eventCapture));
        // Replay
        replayAll();
        // Run test scenario
        final Map<String, String> result = emailTemplateService.copy(fromCompanyUuid, toCompanyUuid);
        // Verify
        verifyAll();
        final List<EmailTemplate> newEmailTemplates = newEmailTemplatesCapture.getValue();
        final EmailTemplate newEmailTemplate = newEmailTemplates.get(0);
        assertNull(newEmailTemplate.getId());
        assertNotEquals(originalEmailTemplates.get(0).getCreated(), newEmailTemplate.getCreated());
        assertNotEquals(originalEmailTemplates.get(0).getUpdated(), newEmailTemplate.getUpdated());
        assertNotEquals(originalEmailTemplates.get(0).getUuid(), newEmailTemplate.getUuid());
        assertEquals(toCompanyUuid, newEmailTemplate.getCompanyUuid());
        final EmailTemplateModifiedEvent event = eventCapture.getValue();
        assertEquals(emailTemplateService, event.getSource());
        assertEquals(newEmailTemplate.getUuid(), event.getUuid());
        assertEquals(1, result.size());
        assertEquals(newEmailTemplate.getUuid(), result.get(originalEmailTemplate.getUuid()));
    }
    //endregion

    //endregion

    //region Utility methods
    private void assertEmailTemplateDto(final EmailTemplateDto dto, final EmailTemplate emailTemplate) {
        assertNotNull(dto);
        assertNotNull(emailTemplate);
        assertEquals(dto.getName(), emailTemplate.getName());
        assertEquals(dto.getSenderName(), emailTemplate.getSenderName());
        assertEquals(dto.getSenderEmail(), emailTemplate.getSenderEmail());
        assertEquals(dto.getReplyEmail(), emailTemplate.getReplyEmail());
        assertEquals(dto.getCcEmails(), emailTemplate.getCcEmails());
        assertEquals(dto.getBccEmails(), emailTemplate.getBccEmails());
    }
    //endregion
}
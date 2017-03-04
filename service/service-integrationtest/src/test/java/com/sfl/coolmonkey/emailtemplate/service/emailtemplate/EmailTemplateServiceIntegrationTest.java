package com.sfl.coolmonkey.emailtemplate.service.emailtemplate;

import com.sfl.coolmonkey.emailtemplate.persistence.repositories.emailtemplate.EmailTemplateRepository;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.dto.EmailTemplateDto;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplateType;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.SystemEmailTemplateType;
import com.sfl.coolmonkey.emailtemplate.service.test.AbstractServiceIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.Assert.*;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/22/15
 * Time: 8:27 PM
 */
public class EmailTemplateServiceIntegrationTest extends AbstractServiceIntegrationTest {

    //region Dependencies
    @Autowired
    private EmailTemplateService emailTemplateService;

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;
    //endregion

    //region Constructors
    public EmailTemplateServiceIntegrationTest() {
    }
    //endregion

    //region Test methods
    @Test
    public void testCreate() {
        // Prepare data
        final String companyUuid = UUID.randomUUID().toString();
        final EmailTemplateType type = EmailTemplateType.APPOINTMENT_EMAIL_TO_ADDRESS;
        final EmailTemplateDto dto = getHelper().createEmailTemplateDto();
        // Run test scenario
        final EmailTemplate emailTemplate = emailTemplateService.create(companyUuid, type, dto);
        assertEmailTemplateDto(dto, emailTemplate);
        assertEquals(companyUuid, emailTemplate.getCompanyUuid());
        assertEquals(type, emailTemplate.getType());
    }

    @Test
    public void testGetById() {
        // Prepare data
        final EmailTemplate emailTemplate = getHelper().createAndPersistEmailTemplate();
        // Run test scenario
        final EmailTemplate result = emailTemplateService.getById(emailTemplate.getId());
        assertNotNull(result);
        assertEquals(emailTemplate, result);
    }

    @Test
    public void testGetByUuid() {
        // Prepare data
        final EmailTemplate emailTemplate = getHelper().createAndPersistEmailTemplate();
        // Run test scenario
        final EmailTemplate result = emailTemplateService.getByUuid(emailTemplate.getUuid());
        assertNotNull(result);
        assertEquals(emailTemplate, result);
    }

    @Test
    public void findByCompanyUuidAndRemovedIsNull() {
        // Prepare data
        final String companyUuid = UUID.randomUUID().toString();
        // Normal email template, should be retrieved
        final EmailTemplate emailTemplate = getHelper().createAndPersistEmailTemplate(companyUuid);
        // Deleted email template, should NOT be retrieved
        final EmailTemplate removedEmailTemplate = getHelper().createAndPersistEmailTemplate(companyUuid);
        removedEmailTemplate.setRemoved(new Date());
        emailTemplateRepository.save(removedEmailTemplate);
        // Other company's email template, should NOT be retrieved
        getHelper().createAndPersistEmailTemplate(UUID.randomUUID().toString());
        // Run test scenario
        final List<EmailTemplate> result = emailTemplateService.getAllByCompanyUuid(companyUuid);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(emailTemplate.getUuid(), result.get(0).getUuid());
    }

    @Test
    public void findByCompanyUuidAndActiveAndRemovedIsNull() {
        // Prepare data
        final String companyUuid = UUID.randomUUID().toString();
        // Normal email template, should be retrieved
        final EmailTemplate emailTemplate = getHelper().createAndPersistEmailTemplate(companyUuid);
        // Deleted email template, should NOT be retrieved
        final EmailTemplate removedEmailTemplate = getHelper().createAndPersistEmailTemplate(companyUuid);
        removedEmailTemplate.setRemoved(new Date());
        emailTemplateRepository.save(removedEmailTemplate);
        // Deactivated email template, should NOT be retrieved
        final EmailTemplate deactivatedEmailTemplate = getHelper().createAndPersistEmailTemplate(companyUuid);
        deactivatedEmailTemplate.setActive(false);
        emailTemplateRepository.save(deactivatedEmailTemplate);
        // Other company's email template, should NOT be retrieved
        getHelper().createAndPersistEmailTemplate(UUID.randomUUID().toString());
        // Run test scenario
        final List<EmailTemplate> result = emailTemplateService.getAllActiveByCompanyUuid(companyUuid);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(emailTemplate.getUuid(), result.get(0).getUuid());
    }

    @Test
    public void testUpdate() {
        // Prepare data
        final EmailTemplate emailTemplate = getHelper().createAndPersistEmailTemplate();
        final EmailTemplateDto dto = getHelper().createEmailTemplateDto();
        dto.setSubject("New subject");
        dto.setHtmlContent("New Html Content");
        // Run test scenario
        final EmailTemplate result = emailTemplateService.update(emailTemplate.getUuid(), dto);
        assertTrue(result.getUpdated() != null);
        assertEmailTemplateDto(dto, result);
    }

    @Test
    public void testGetAllByCompanyUuidAndType() {
        // Prepare data
        final String companyUuid = UUID.randomUUID().toString();
        final List<EmailTemplate> emailTemplates = new ArrayList<>();
        emailTemplates.add(getHelper().createAndPersistEmailTemplate(companyUuid));
        emailTemplates.add(getHelper().createAndPersistEmailTemplate(companyUuid));
        emailTemplates.add(getHelper().createAndPersistEmailTemplate(companyUuid));
        final EmailTemplateType type = emailTemplates.get(0).getType();
        // Run test scenario
        final List<EmailTemplate> results = emailTemplateService.getAllByCompanyUuidAndType(companyUuid, type);
        assertTrue(!results.isEmpty());
        assertEmailTemplates(emailTemplates, results);
    }

    @Test
    public void testActivateAndDeactivate() {
        // Prepare data
        final EmailTemplate emailTemplate = getHelper().createAndPersistEmailTemplate();
        final String uuid = emailTemplate.getUuid();
        EmailTemplate result;
        // Run test scenario
        // Deactivate
        emailTemplateService.deactivate(uuid);
        result = emailTemplateService.getByUuid(uuid);
        assertNotNull(result);
        assertFalse(result.isActive());
        // Activate
        emailTemplateService.activate(uuid);
        result = emailTemplateService.getByUuid(uuid);
        assertNotNull(result);
        assertTrue(result.isActive());
    }

    @Test
    public void testRemove() {
        // Prepare data
        final EmailTemplate emailTemplate = getHelper().createAndPersistEmailTemplate();
        final String uuid = emailTemplate.getUuid();
        // Run test scenario
        emailTemplateService.remove(uuid);
        final EmailTemplate result = emailTemplateService.getByUuid(uuid);
        assertNotNull(result);
        assertNotNull(result.getRemoved());
    }

    @Test
    public void testDoesEmailTemplateExistForNameAndCompanyWhenExists() {
        // Prepare data
        final EmailTemplate emailTemplate = getHelper().createAndPersistEmailTemplate();
        // Run test scenario
        final boolean exists = emailTemplateService.checkIfEmailTemplateExistForNameAndCompany(emailTemplate.getName().toUpperCase(Locale.ROOT), emailTemplate.getCompanyUuid());
        assertTrue(exists);
    }

    @Test
    public void testDoesEmailTemplateExistForNameAndCompanyWhenNotExists() {
        // Prepare data
        final EmailTemplate emailTemplate = getHelper().createAndPersistEmailTemplate();
        // Run test scenario
        final boolean exists = emailTemplateService.checkIfEmailTemplateExistForNameAndCompany("name which does not exists", emailTemplate.getCompanyUuid());
        assertFalse(exists);
    }

    @Test
    public void testDoesEmailTemplateExistForNameAndCompanyWhenExistsAndDeleted() {
        // Prepare data
        final EmailTemplate emailTemplate = getHelper().createAndPersistEmailTemplate();
        emailTemplateService.remove(emailTemplate.getUuid());
        // Run test scenario
        final boolean exists = emailTemplateService.checkIfEmailTemplateExistForNameAndCompany(emailTemplate.getName(), emailTemplate.getCompanyUuid());
        assertFalse(exists);
    }

    @Test
    public void testGetBySystemEmailTemplateType() {
        // Prepare data
        emailTemplateRepository.deleteAll();
        final SystemEmailTemplateType systemEmailTemplateType = SystemEmailTemplateType.ADDRESS_IMPORT_FAILED;
        EmailTemplate emailTemplate = getHelper().createAndPersistEmailTemplate();
        emailTemplate.setSystemEmailTemplateType(systemEmailTemplateType);
        emailTemplate = emailTemplateRepository.save(emailTemplate);
        // Run test scenario
        final EmailTemplate result = emailTemplateService.getBySystemEmailTemplateType(systemEmailTemplateType);
        assertEquals(emailTemplate, result);
    }

    @Test
    public void testGetForIndexation() {
        emailTemplateRepository.deleteAll();
        // prepare test data
        final EmailTemplate emailTemplate1 = getHelper().createAndPersistEmailTemplate();
        final EmailTemplate emailTemplate2 = getHelper().createAndPersistEmailTemplate();
        final EmailTemplate emailTemplate3 = getHelper().createAndPersistEmailTemplate();
        final EmailTemplate emailTemplate4 = getHelper().createAndPersistEmailTemplate();
        final EmailTemplate emailTemplate5 = getHelper().createAndPersistEmailTemplate();
        final EmailTemplate emailTemplate6 = getHelper().createAndPersistEmailTemplate();
        emailTemplate6.setRemoved(new Date());
        emailTemplateRepository.save(emailTemplate6);
        // run test scenario
        List<EmailTemplate> result;
        result = emailTemplateService.getForIndexation(0, 2);
        assertEquals(Arrays.asList(emailTemplate1, emailTemplate2), result);
        result = emailTemplateService.getForIndexation(1, 2);
        assertEquals(Arrays.asList(emailTemplate3, emailTemplate4), result);
        result = emailTemplateService.getForIndexation(2, 2);
        assertEquals(Collections.singletonList(emailTemplate5), result);
        result = emailTemplateService.getForIndexation(0, 6);
        assertFalse(result.contains(emailTemplate6));
    }

    @Test
    public void testCopy() {
        // Prepare data
        final String toCompanyUuid = UUID.randomUUID().toString();
        final String fromCompanyUuid = UUID.randomUUID().toString();
        final EmailTemplate originalEmailTemplate = getHelper().createAndPersistEmailTemplate(fromCompanyUuid);
        // Run test scenario
        final Map<String, String> result = emailTemplateService.copy(fromCompanyUuid, toCompanyUuid);
        assertEquals(Collections.singletonList(originalEmailTemplate), emailTemplateService.getAllByCompanyUuid(fromCompanyUuid));
        assertEquals(result.get(originalEmailTemplate.getUuid()), emailTemplateService.getAllByCompanyUuid(toCompanyUuid).get(0).getUuid());
    }
    //endregion

    //region Utility methods
    private void assertEmailTemplates(final List<EmailTemplate> expected, final List<EmailTemplate> actual) {
        expected.forEach(actual::contains);
    }

    private void assertEmailTemplateDto(final EmailTemplateDto dto, final EmailTemplate emailTemplate) {
        assertNotNull(dto);
        assertNotNull(emailTemplate);
        assertEquals(dto.getName(), emailTemplate.getName());
        assertEquals(dto.getSenderName(), emailTemplate.getSenderName());
        assertEquals(dto.getSenderEmail(), emailTemplate.getSenderEmail());
        assertEquals(dto.getReplyEmail(), emailTemplate.getReplyEmail());
        assertEquals(dto.getCcEmails(), emailTemplate.getCcEmails());
        assertEquals(dto.getBccEmails(), emailTemplate.getBccEmails());
        assertEquals(dto.getSubject(), emailTemplate.getSubject());
        assertEquals(dto.getHtmlContent(), emailTemplate.getHtmlContent());
    }
    //endregion
}

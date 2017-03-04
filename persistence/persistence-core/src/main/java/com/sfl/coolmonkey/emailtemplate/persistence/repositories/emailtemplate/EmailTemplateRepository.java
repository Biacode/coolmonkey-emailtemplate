package com.sfl.coolmonkey.emailtemplate.persistence.repositories.emailtemplate;

import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplateType;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.SystemEmailTemplateType;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/22/15
 * Time: 7:37 PM
 */
@Repository
public interface EmailTemplateRepository extends MongoRepository<EmailTemplate, ObjectId> {
    EmailTemplate findByUuid(@Nonnull final String uuid);

    List<EmailTemplate> findByCompanyUuidAndRemovedIsNull(@Nonnull final String companyUuid);

    List<EmailTemplate> findByCompanyUuidAndRemovedIsNullAndActiveTrue(@Nonnull final String companyUuid);

    List<EmailTemplate> findByCompanyUuidAndTypeAndRemovedIsNull(@Nonnull final String companyUuid, @Nonnull final EmailTemplateType type);

    EmailTemplate findByNameIgnoreCaseAndCompanyUuidAndRemovedIsNull(@Nonnull final String name, @Nonnull final String companyUuid);

    EmailTemplate findBySystemEmailTemplateTypeAndRemovedIsNull(@Nonnull final SystemEmailTemplateType systemEmailTemplateType);

    Page<EmailTemplate> findByRemovedIsNull(@Nonnull final Pageable pageable);

    List<EmailTemplate> findByActiveFalse();
}

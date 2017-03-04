package com.sfl.coolmonkey.emailtemplate.facade.helper.conversion;

import com.sfl.coolmonkey.coolfs.api.model.storage.StoredFileInfoModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.AttachmentModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateCreateUpdateModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateTypeModel;
import com.sfl.coolmonkey.emailtemplate.facade.helper.conversion.emailtemplate.EmailTemplateToEmailTemplateModelConverter;
import com.sfl.coolmonkey.emailtemplate.facade.helper.conversion.emailtemplate.EmailTemplateToEmailTemplateSearchModelConverter;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.dto.EmailTemplateDto;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplateType;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 10/1/15
 * Time: 6:48 PM
 */
@Primary
@Component
@SuppressWarnings({
        "pmd:NcssMethodCount",
        "squid:S138",
})
public class MapperFacadeFactory extends AbstractFactoryBean<MapperFacade> {

    //region Dependencies
    @Autowired
    private EmailTemplateToEmailTemplateSearchModelConverter emailTemplateToEmailTemplateSearchModelConverter;

    @Autowired
    private EmailTemplateToEmailTemplateModelConverter emailTemplateToEmailTemplateModelConverter;
    //endregion

    //region Constructors
    public MapperFacadeFactory() {
    }
    //endregion

    //region Public methods
    @Override
    public Class<?> getObjectType() {
        return MapperFacade.class;
    }

    @Override
    public MapperFacade createInstance() {
        final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        //region Email template
        mapperFactory.classMap(EmailTemplateModel.class, EmailTemplate.class).byDefault().register();
        mapperFactory.classMap(StoredFileInfoModel.class, AttachmentModel.class).byDefault().register();
        mapperFactory.classMap(EmailTemplateCreateUpdateModel.class, EmailTemplateDto.class)
                .field("attachments{}", "attachmentUuids{}")
                .byDefault().register();
        mapperFactory.classMap(EmailTemplateTypeModel.class, EmailTemplateType.class).byDefault().register();
        mapperFactory.getConverterFactory().registerConverter(emailTemplateToEmailTemplateSearchModelConverter);
        mapperFactory.getConverterFactory().registerConverter(emailTemplateToEmailTemplateModelConverter);
        //endregion

        return mapperFactory.getMapperFacade();
    }
    //endregion
}

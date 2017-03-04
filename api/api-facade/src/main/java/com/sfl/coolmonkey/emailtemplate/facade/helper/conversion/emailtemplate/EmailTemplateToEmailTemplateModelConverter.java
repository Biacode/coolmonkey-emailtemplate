package com.sfl.coolmonkey.emailtemplate.facade.helper.conversion.emailtemplate;

import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.StoredFileInfoModel;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.GetFileInfoByUuidListRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.GetFileInfoByUuidListResponse;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.AttachmentModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailTemplateTypeModel;
import com.sfl.coolmonkey.emailtemplate.externalclients.coolfs.communicator.CoolFsServiceCommunicator;
import com.sfl.coolmonkey.emailtemplate.service.emailtemplate.model.EmailTemplate;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 01/04/16
 * Time: 14:25
 */
@Component
public class EmailTemplateToEmailTemplateModelConverter extends CustomConverter<EmailTemplate, EmailTemplateModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateToEmailTemplateModelConverter.class);

    //region Dependencies
    @Autowired
    private CoolFsServiceCommunicator coolFsServiceCommunicator;
    //endregion

    //region Constructors
    public EmailTemplateToEmailTemplateModelConverter() {
        LOGGER.debug("Initializing");
    }
    //endregion

    //region Public methods
    @Override
    public EmailTemplateModel convert(final EmailTemplate emailTemplate, final Type<? extends EmailTemplateModel> type) {
        final EmailTemplateModel model =
                new EmailTemplateModel(
                        emailTemplate.getUuid(),
                        mapperFacade.map(emailTemplate.getType(), EmailTemplateTypeModel.class),
                        emailTemplate.getName(),
                        emailTemplate.getSenderName(),
                        emailTemplate.getSenderEmail(),
                        emailTemplate.getReplyEmail(),
                        emailTemplate.getCcEmails(),
                        emailTemplate.getBccEmails(),
                        emailTemplate.getSubject(),
                        emailTemplate.getHtmlContent(),
                        null,
                        emailTemplate.isActive()

                );
        if(emailTemplate.getAttachmentUuids()!=null) {
            final ResultResponseModel<GetFileInfoByUuidListResponse> coolFsResponse =
                    coolFsServiceCommunicator.getFileInfoByUuids(new GetFileInfoByUuidListRequest(emailTemplate.getAttachmentUuids()));
            if (!coolFsResponse.hasErrors()) {
                final List<StoredFileInfoModel> filesInfo = coolFsResponse.getResponse().getFilesInfo();
                final List<AttachmentModel> attachmentModels = mapperFacade.mapAsList(filesInfo, AttachmentModel.class);
                model.setAttachments(attachmentModels);
            }
        }
        return model;
    }
    //endregion
}

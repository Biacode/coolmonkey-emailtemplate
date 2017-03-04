package com.sfl.coolmonkey.emailtemplate.externalclients.coolfs.communicator.impl;

import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.emailtemplate.externalclients.coolfs.communicator.CoolFsServiceCommunicator;
import com.sfl.coolmonkey.coolfs.api.client.rest.storage.StorageResourceClient;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.GetFileInfoByUuidListRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.GetFileInfoByUuidRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.LoadFileByUuidRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.request.UploadFileRequest;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.GetFileInfoByUuidListResponse;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.GetFileInfoByUuidResponse;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.LoadFileByUuidResponse;
import com.sfl.coolmonkey.coolfs.api.model.storage.response.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 2/19/16
 * Time: 11:34 AM
 */
@Component
public class CoolFsServiceCommunicatorImpl implements CoolFsServiceCommunicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoolFsServiceCommunicatorImpl.class);

    //region Dependencies
    @Autowired
    private StorageResourceClient storageResourceClient;
    //endregion

    //region Constructors
    public CoolFsServiceCommunicatorImpl() {
        LOGGER.debug("Initializing coolfs service communicator");
    }
    //endregion

    //region Public methods
    @Nonnull
    @Override
    public ResultResponseModel<UploadFileResponse> uploadFile(@Nonnull UploadFileRequest request) {
        return storageResourceClient.upload(request);
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetFileInfoByUuidResponse> getFileInfoByUuid(@Nonnull final GetFileInfoByUuidRequest request) {
        return storageResourceClient.getFileInfoByUuid(request);
    }

    @Nonnull
    @Override
    public ResultResponseModel<GetFileInfoByUuidListResponse> getFileInfoByUuids(@Nonnull final GetFileInfoByUuidListRequest request) {
        return storageResourceClient.getFileInfoByUuids(request);
    }

    @Nonnull
    @Override
    public ResultResponseModel<LoadFileByUuidResponse> downloadFileByUuid(@Nonnull final LoadFileByUuidRequest request) {
        return storageResourceClient.downloadFileByUuid(request);
    }
    //endregion
}

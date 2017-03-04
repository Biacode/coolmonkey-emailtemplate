package com.sfl.coolmonkey.emailtemplate.facade.emailtemplate.component;

import com.sfl.coolmonkey.commons.api.model.response.ResultResponseModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.request.GetEmailGridRequest;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.response.GetEmailGridResponse;

import javax.annotation.Nonnull;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 10/27/15
 * Time: 8:32 PM
 */
public interface EmailGridComponent {
    @Nonnull
    ResultResponseModel<GetEmailGridResponse> getEmailGrid(@Nonnull final GetEmailGridRequest request);
}

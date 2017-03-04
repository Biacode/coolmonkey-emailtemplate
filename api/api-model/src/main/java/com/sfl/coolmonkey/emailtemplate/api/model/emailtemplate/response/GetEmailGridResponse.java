package com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.response;

import com.sfl.coolmonkey.commons.api.model.response.AbstractGridAwareResponseModel;
import com.sfl.coolmonkey.emailtemplate.api.model.emailtemplate.EmailGridModel;

import java.util.List;

/**
 * User: Babken Vardanyan
 * Company: SFL LLC
 * Date: 10/27/15
 * Time: 8:25 PM
 */
public class GetEmailGridResponse extends AbstractGridAwareResponseModel<EmailGridModel> {
    private static final long serialVersionUID = 8240188092367972236L;

    //region Properties
    //endregion

    //region Constructors
    public GetEmailGridResponse() {
    }

    public GetEmailGridResponse(final List<EmailGridModel> grid) {
        super(grid);
    }
    //endregion

    //region Equals, HashCode and ToString
    //endregion

    //region Properties getters and setters
    //endregion
}

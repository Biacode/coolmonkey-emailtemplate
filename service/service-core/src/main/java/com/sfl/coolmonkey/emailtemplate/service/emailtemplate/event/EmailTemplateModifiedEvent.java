package com.sfl.coolmonkey.emailtemplate.service.emailtemplate.event;

import com.sfl.coolmonkey.emailtemplate.service.event.model.AbstractEntityActionEvent;

/**
 * User: Valentina Sargsyan
 * Company: SFL LLC
 * Date: 17-3-2016
 * Time: 11:35
 */
public class EmailTemplateModifiedEvent extends AbstractEntityActionEvent {
    private static final long serialVersionUID = 2813290946823399341L;

    //region Constructors
    public EmailTemplateModifiedEvent(final Object source, final String uuid) {
        super(source, uuid);
    }
    //endregion
}

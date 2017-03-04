package com.sfl.coolmonkey.emailtemplate.service.common.exception;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 7/20/15
 * Time: 3:31 PM
 */
public class ServicesRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -6910128089191695262L;

    //region Constructors
    public ServicesRuntimeException(final Throwable cause) {
        super(cause);
    }

    public ServicesRuntimeException(final String message) {
        super(message);
    }

    public ServicesRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }
    //endregion
}
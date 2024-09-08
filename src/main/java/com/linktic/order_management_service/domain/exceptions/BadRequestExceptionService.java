package com.linktic.order_management_service.domain.exceptions;

public class BadRequestExceptionService extends RuntimeException {

    public BadRequestExceptionService(String message) {
        super(message);
    }

    public BadRequestExceptionService(ExceptionDetail exceptionDetail) {
        super(exceptionDetail.getJson());
    }
}

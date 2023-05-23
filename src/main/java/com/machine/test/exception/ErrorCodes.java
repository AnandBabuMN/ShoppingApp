package com.machine.test.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCodes {
    CUSTOMER_NOT_FOUND ("1", "USER NOT FOUND!"),
    EXISTS_PRODUCT("2", "PRODUCT ALREADY IN INVENTORY"),
    PRODUCT_NOT_FOUND ("3","PRODUCT NOT FOUND!");

    private final String errorCode;
    private final String errorDesc;
}

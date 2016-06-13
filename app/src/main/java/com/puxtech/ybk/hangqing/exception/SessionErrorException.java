package com.puxtech.ybk.hangqing.exception;

/**
 * 行情session错误
 */
public class SessionErrorException extends Exception {

    public SessionErrorException(String detailMessage) {
        super(detailMessage);
    }
}

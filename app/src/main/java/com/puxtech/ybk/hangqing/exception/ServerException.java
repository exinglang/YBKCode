package com.puxtech.ybk.hangqing.exception;

/**
 * 行情服务器异常
 */
public class ServerException extends Exception {

    public ServerException(String detailMessage) {
        super(detailMessage);
    }
}

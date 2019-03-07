package com.myth.yRPC.core;

import java.io.Serializable;

/**
 * @description:
 * @author: yuang gang
 * @create: 2019-03-07 14:55
 **/
public class RpcResponse extends RpcBaseModel {
    private String requestId;
    private Exception exception;
    private Object result;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}

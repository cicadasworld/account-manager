package com.jin.bean.response;

import java.io.Serializable;

/**
 * 服务器返回给前端的统一数据封装
 * @author hujin
 * @version 2021/12/26
 */
public class ResponseBean<T> implements Serializable {

    /**
     * 服务器是否异常标识
     */
    private Boolean flag;

    /**
     * 服务器响应的数据
     */
    private T data;

    /**
     * 服务器响应的错误消息
     */
    private String errorMessage;

    public ResponseBean() {
    }

    public ResponseBean(Boolean flag) {
        this.flag = flag;
    }

    public ResponseBean(T data) {
        this.data = data;
    }

    public ResponseBean(Boolean flag, T data, String errorMessage) {
        this.flag = flag;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "flag=" + flag +
                ", data=" + data +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}

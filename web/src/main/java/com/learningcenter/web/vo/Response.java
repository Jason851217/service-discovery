package com.learningcenter.web.vo;

import java.io.Serializable;

/**
 * 描述:
 *
 * @author Jason
 * @email 285290078@qq.com
 * @create 2018-06-13 19:36
 **/
public class Response<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private Response(){

    }

    public static <T> Response success(T data){
        Response response = new Response();
        response.status=200;
        response.data=data;
        return response;
    }

    public static Response error(int status,String msg){
        Response response = new Response();
        response.status=status;
        response.msg=msg;
        return response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

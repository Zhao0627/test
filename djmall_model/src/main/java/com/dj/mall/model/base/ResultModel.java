package com.dj.mall.model.base;

import lombok.Data;

import java.io.Serializable;

/**
 * JSON 统一返回类
 * @param <T>
 */
@Data
public class ResultModel<T> implements Serializable {

	public ResultModel(){}
	
    private Integer code;
    private String msg;
    private T data;

    public ResultModel success(T data) {
        this.code = 200;
        this.msg = "success";
        this.data = data;
        return this;
    }

    public ResultModel success(String msg) {
        this.code = 200;
        this.msg = msg;
        return this;
    }

    public ResultModel error(String msg) {
        this.code = -1;
        this.msg = msg;
        return this;
    }

    public ResultModel error(T data) {
        this.code = -1;
        this.msg = "error";
        this.data = data;
        return this;
    }

    public ResultModel error(Integer code, T data) {
        this.code = code;
        this.msg = "error";
        this.data = data;
        return this;
    }

    public ResultModel error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ResultModel error(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        return this;
    }
}

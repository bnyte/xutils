package com.bnyte.xuni.http.reactive.web;

import java.io.Serializable;
import java.util.*;

/**
 * web统一响应结果集合
 * @auther bnyte
 * @date 2021-12-09 18:37
 * @email bnytezz@gmail.com
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = 94264211209183700L;

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应返回信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 请求成功
     *  code: 0
     *  message: succeeded
     * @return 链式返回当前响应成功对象
     */
    public static <T> R<T> ok() {
        R<T> r = new R<>();
        r.codeAndMessage(Status.ok);
        return r;
    }

    /**
     * 请求成功
     *  code: 0
     *  message: succeeded
     * @return 链式返回当前响应成功对象
     */
    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.codeAndMessage(Status.ok);
        r.data = data;
        return r;
    }

    /**
     * 请求失败
     *  code: -1
     *  message: failed
     * @return 链式返回当前响应失败对象
     */
    public static <T> R<T> error() {
        R<T> r = new R<>();
        r.codeAndMessage(Status.error);
        return r;
    }

    /**
     * 设置当前链式对象响应结果数据集，使用这种方式会直接覆盖掉已经存在的data数据
     * @return 链式返回当前设置好响应结果集的响应对象
     */
    public R<T> data(T data) {
        if (this.data == null) {
            this.data = data;
        } else {
            List<T> list = new ArrayList<>();
            if (this.data instanceof Collection) {
                list.addAll((Collection<? extends T>) this.data);
                list.add(data);
            }
            else {
                list.add(this.data);
                list.add(data);
            }
            this.setData((T) list);
        }
        return this;
    }

    public R<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public R<T> message(String message) {
        this.message = message;
        return this;
    }

    /**
     * 设置状态码以及响应信息
     * @param desc 响应描述对象
     * @return 返回当前链式对象
     */
    public R<T> codeAndMessage(Status desc) {
        this.setCode(desc.getCode());
        this.setMessage(desc.getMessage());
        return this;
    }

    private R() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

package com.huangyichun.core.bo;

import com.huangyichun.core.ienum.ResponseStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应体
 *
 * @author xuyf
 * @date 2020/7/16
 */
@Data
public class ResponseVO<T> implements Serializable {
    private static final long serialVersionUID = -6901279092442651873L;
    private String msg;
    private String code;

    private T data;

    /**
     * 默认返回100状态码
     *
     * @author xuyf
     * @date 2020/7/16
     */
    public ResponseVO() {
        this.code = ResponseStatus.FAIL.getCode();
        this.msg = ResponseStatus.FAIL.getText();
        this.data = null;
    }

    public ResponseVO(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public ResponseVO success() {
        return new ResponseVO(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getText(), null);
    }

    public ResponseVO success(T data) {
        return new ResponseVO();
    }
}

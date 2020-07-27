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
    /**
     * 摘要信息
     */
    private String msg;
    /**
     * 状态码
     */
    private String code;
    /**
     * 数据
     */
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

    /**
     * 成功返回200
     *
     * @return RequestVO
     * @author: xuyf
     * @Date: 2020/7/16
     */
    public ResponseVO success() {
        return new ResponseVO(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getText(), null);
    }

    /**
     * 成功默认返回200，带数据
     *
     * @param data
     * @return
     * @author: xuyf
     * @Date: 2020/7/16
     */
    public ResponseVO success(T data) {
        return new ResponseVO(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getText(), data);
    }

    /**
     * 失败默认返回100
     *
     * @return ResponseVO
     * @author: xuyf
     * @Date: 2020/7/16
     */
    public ResponseVO fail() {
        return new ResponseVO(ResponseStatus.FAIL.getCode(), ResponseStatus.FAIL.getText(), data);
    }
}

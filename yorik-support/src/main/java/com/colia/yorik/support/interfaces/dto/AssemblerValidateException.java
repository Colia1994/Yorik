
package com.colia.yorik.support.interfaces.dto;


import com.colia.yorik.support.infrastructure.exception.ValidateException;

/**
 * 数据传输对象装配器验证异常
 *
 * @Author colia
 * @Date 2020/07/15 下午3:45
 **/
public class AssemblerValidateException extends ValidateException {

    public AssemblerValidateException(String message) {
        super(message);
    }

    public AssemblerValidateException(String message, Throwable cause) {
        super(message, cause);
    }
}
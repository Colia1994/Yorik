package com.colia.yorik.support.domain.vo;

/**
 * vo
 *
 * @param <T>
 */
public interface ValueObject<T> {

    boolean sameValueAs(T other);

    /**
     * 获取值
     *
     * @return
     */
    default String getValueObject() {
        return "";
    }
}
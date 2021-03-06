package com.colia.yorik.support.aop;

import com.colia.yorik.support.utils.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author konglingyao
 * @Date 2020/7/22
 */
@Slf4j
@Data
public class ServiceProxyFactoryBean<T> implements InvocationHandler, FactoryBean<T> {

    private Class<?> interfaces;

    private Object obj;

    public String queue;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("invoke doing before:" + JSONUtil.transferToString(args));
        Object object = method.invoke(obj, args);
        log.info("invoke doing after:" + JSONUtil.transferToString(object));

        return object;
    }

    @Override
    public T getObject() throws Exception {
        log.info("queue:{},interfaces:{},{},{}", getQueue(), interfaces.getCanonicalName(), interfaces.getName(), interfaces.getSimpleName());
        System.out.println("queue:" + getQueue());
        log.info("准备构建代理类");
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class<?>[]{interfaces}, this);
    }

    @Override
    public Class<?> getObjectType() {
        return interfaces;
    }
}

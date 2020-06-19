package com.laven.v2;

import com.laven.v2.RemoteInvocationHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
@Component
public class ReferenceInvokeProxy implements BeanPostProcessor {
    @Autowired
    RemoteInvocationHandler invocationHandler;

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Field[] fields = bean.getClass().getDeclaredFields();

        for(Field field : fields) {
            if (field.isAnnotationPresent(Reference.class)) {
                field.setAccessible(true);
                // 针对添加了Reference注解的字段添加代理值
                Object proxy = Proxy.newProxyInstance(field.getType().getClassLoader(), new Class<?>[]{field.getType()}, invocationHandler);
                try {
                    field.set(bean, proxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return bean;
    }
}

package com.laven.v2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
@Component
public class InitialMediator implements BeanPostProcessor {
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RemoteService.class)) {
            // 添加了发布服务注解的bean进行远程发布
            Method[] methods = bean.getClass().getDeclaredMethods();
            for(Method method : methods) {
                String key = bean.getClass().getInterfaces()[0].getName() + "." + method.getName();
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(method);
                Mediator.map.put(key, beanMethod);
            }
        }
        return bean;
    }
}

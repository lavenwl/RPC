package com.laven.v2;

import com.laven.v1.RpcRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Mediator {

    // 用来存储发布的服务的实例(服务调用的路由)

    public static Map<String, BeanMethod> map = new ConcurrentHashMap<String, BeanMethod>();

    private volatile  static Mediator instance;

    public Mediator() { }

    public static Mediator getInstance() {
        if (instance == null) {
            synchronized (Mediator.class) {
                if (instance == null) {
                    instance = new Mediator();
                }
            }
        }
        return instance;
    }

    public Object processor(RpcRequest rpcRequest) {
        String key = rpcRequest.getClassName() + "." + rpcRequest.getMethodName();
        BeanMethod beanMethod = map.get(key);
        if (beanMethod == null) {
            return null;
        }

        Object bean = beanMethod.getBean();
        Method method = beanMethod.getMethod();

        try {
            return method.invoke(bean, rpcRequest.getArgs());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }




}

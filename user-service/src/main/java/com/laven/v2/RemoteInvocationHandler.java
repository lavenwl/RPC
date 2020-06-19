package com.laven.v2;

import com.laven.v1.RpcNetTransport;
import com.laven.v1.RpcRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
@Component
public class RemoteInvocationHandler implements InvocationHandler {
    @Value("${gp.host}")
    private String host;
    @Value("${gp.port}")
    private int port;

    public RemoteInvocationHandler() {
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 创建远程连接
        RpcNetTransport transport = new RpcNetTransport(host, port);

        // 构建request对象
        RpcRequest request = new RpcRequest();
        request.setArgs(args);
        request.setClassName(method.getDeclaringClass().getName());
        request.setTypes(method.getParameterTypes());
        request.setMethodName(method.getName());
        return transport.send(request);
    }
}

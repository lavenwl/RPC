package com.laven.v1;

import java.lang.reflect.Proxy;

public class ProxyClient {
    public <T> T clientProxy(final Class<T> interfaceCls, final String host, final int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls}, new RemoteInvocationHandler(host, port));
    }
}

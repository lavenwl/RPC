package com.laven.v1;

public class Bootstrap {
    public static void main(String[] args) {
        IOrderService orderService = new OrderServiceImpl();
        ProxyServer proxyServer = new ProxyServer();
        proxyServer.pulisher(orderService, 8000);
    }
}

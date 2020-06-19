package com.laven.v1;

public class App {
    public static void main(String[] args) {
        ProxyClient proxyClient = new ProxyClient();
        IOrderService orderService = proxyClient.clientProxy(IOrderService.class, "localhost", 8000);

        System.out.println(orderService.orderById());
        System.out.println(orderService.queryOrderList());
    }
}

package com.laven.v2;

import com.laven.v1.IOrderService;
import com.laven.v1.OrderServiceImpl;
import com.laven.v1.ProxyServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.laven")
public class Bootstrap {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Bootstrap.class);
    }
}

package com.laven.v2;

import com.laven.v1.IOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class myController {

    @Reference
    private IOrderService orderService;

    @GetMapping("/order")
    public String order() {

        System.out.println("in orderList" +  orderService.toString());
        return orderService.queryOrderList();
    }
}

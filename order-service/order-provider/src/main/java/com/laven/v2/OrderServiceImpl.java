package com.laven.v2;

import com.laven.v1.IOrderService;

@RemoteService
public class OrderServiceImpl implements IOrderService{

    public String queryOrderList() {
        return "EXECUTE QUERYORDERLIST METHOD";
    }

    public String orderById() {
        return "EXECUTE ORDER_BY_ID METHOD";
    }
}

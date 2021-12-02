package com.sososhopping.merchant.model.order.service;

public interface OrderService {

    void requestOrderListOfDay();
    void requestOrderListDeliverToday();
    void requestOrderListPending();
    void requestOrderListDelivery();
    void requestOrderCancel();
    void requestOrderAccept();
    void requestOrderReady();
}

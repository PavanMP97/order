package com.intenship.order.services;

import com.intenship.order.entity.OrderDomain;

public interface UserServiceInterface {
    public Object placeOrder(Long userId, OrderDomain orderDomain);

    public Object debit(Long userId, OrderDomain orderDomain);

    public Object showOrderDetails(Long userId);

    public Object showOrderDetailsByProductId(Long productId);
}

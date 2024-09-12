package com.orderservice.services;

import com.orderservice.dto.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public String placeOrder(OrderRequest orderRequest);

}

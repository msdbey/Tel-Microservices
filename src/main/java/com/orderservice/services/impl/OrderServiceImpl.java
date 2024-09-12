package com.orderservice.services.impl;

import com.orderservice.dto.OrderItemDto;
import com.orderservice.dto.OrderRequest;
import com.orderservice.entities.Order;
import com.orderservice.entities.OrderItem;
import com.orderservice.repositories.OrderRepo;
import com.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private OrderRepo orderRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo){
        this.orderRepo=orderRepo;
    }
    @Override
    public String placeOrder(OrderRequest orderRequest) {

        Order order = new Order();
        order.setOrder_number(UUID.randomUUID().toString());

        List<OrderItem> orderItemList = orderRequest.getOrderItemDtoList().stream()
                .map(orderItemDto -> mapToDto(orderItemDto))
                .collect(Collectors.toList());
        order.setOrderItemList(orderItemList);

        orderRepo.save(order);

        return "Congratulations! Your Order Placed Successfully";
    }

    private OrderItem mapToDto(OrderItemDto orderItemDto) {
        OrderItem orderItem =new OrderItem();
        orderItem.setSkuCode(orderItemDto.getSkuCode());
        orderItem.setOrderPrice(orderItem.getOrderPrice());
        orderItem.setQty(orderItemDto.getQty());
        return  orderItem;
    }
}

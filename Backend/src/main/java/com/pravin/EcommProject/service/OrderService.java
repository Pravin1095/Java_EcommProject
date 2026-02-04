package com.pravin.EcommProject.service;

import com.pravin.EcommProject.model.Order;
import com.pravin.EcommProject.model.OrderItem;
import com.pravin.EcommProject.model.Product;
import com.pravin.EcommProject.model.dto.OrderItemRequest;
import com.pravin.EcommProject.model.dto.OrderItemResponse;
import com.pravin.EcommProject.model.dto.OrderRequest;
import com.pravin.EcommProject.model.dto.OrderResponse;
import com.pravin.EcommProject.repo.OrderRepo;
import com.pravin.EcommProject.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderRepo orderRepo;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        String orderId = "ORD" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(orderRequest.customerName()); //Unlike normal class record data are accessed with just their names instead of get
        order.setEmail(orderRequest.email());
        order.setStatus("PLACED");

        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemRequest itemReq : orderRequest.items()){
            Product product = productRepo.findById(itemReq.productId()).orElseThrow(()->new RuntimeException("Product not found"));
            product.setStockQuantity(product.getStockQuantity()-itemReq.quantity());
            productRepo.save(product);

            //builder helps to create a new object in clean way
            OrderItem orderItem = OrderItem.builder().product(product).
                    quantity(itemReq.quantity()).
                    totalPrice(product.getPrice().multiply(BigDecimal.valueOf(itemReq.quantity()))).
                    order(order).
                    build();

            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        Order savedOrder = (Order) orderRepo.save(order);

        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for(OrderItem item : order.getOrderItems()){
            OrderItemResponse orderItemResponse = new OrderItemResponse(
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getTotalPrice()
            );
            itemResponses.add(orderItemResponse);
        }

        OrderResponse orderResponse = new OrderResponse(savedOrder.getOrderId(), savedOrder.getCustomerName(), savedOrder.getEmail(),
                savedOrder.getStatus(),savedOrder.getOrderDate(),itemResponses);
        return orderResponse;
    }


    public List<OrderResponse> getAllOrderResponses() {
        List<Order> orders = orderRepo.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();
        for(Order order : orders) {

            List<OrderItemResponse> itemResponses = new ArrayList<>();
            for (OrderItem item : order.getOrderItems()) {
                OrderItemResponse orderItemResponse = new OrderItemResponse(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getTotalPrice()
                );
                itemResponses.add(orderItemResponse);
            }

            OrderResponse orderResponse = new OrderResponse( order.getOrderId(),
                    order.getCustomerName(),
                    order.getEmail(),
                    order.getStatus(),
                    order.getOrderDate(),
                    itemResponses);

            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }
}

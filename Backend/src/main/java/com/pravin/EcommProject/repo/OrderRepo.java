package com.pravin.EcommProject.repo;

import com.pravin.EcommProject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepo extends JpaRepository {

    Optional<Order> findByOrderId(String orderId);
}

package com.pravin.EcommProject.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderItem {
    private int id;

    @ManyToOne   //many Order Item can be associated to one Product
    private Product product;
    private int quantity;
    private BigDecimal totalPrice;


    //This FetchType lazy fetches the Order data only when user tries to access it directly.
    //Default type is Eager, this fetches data immediately when we try to get any OrderItem
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}

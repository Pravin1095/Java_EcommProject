package com.pravin.EcommProject.model.dto;

import java.math.BigDecimal;

public record OrderItemResponse(String productName, int qunatity, BigDecimal totalPrice) {
}

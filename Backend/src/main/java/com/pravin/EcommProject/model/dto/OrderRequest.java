package com.pravin.EcommProject.model.dto;

import java.util.List;

public record OrderRequest(String customerName, String email, List<OrderItemRequest> items) {
}

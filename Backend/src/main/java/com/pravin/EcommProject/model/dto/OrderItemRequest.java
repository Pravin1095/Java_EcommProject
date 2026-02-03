package com.pravin.EcommProject.model.dto;

//record makes its states implicitly private and final hence they are immutable.
//record decreases boilerplate code compared to a class
//A record automatically provides:
//Private final fields
//Constructor
//Getter methods (without get)
//equals()
//hashCode()
//toString()

public record OrderItemRequest(int productId, int quantity) {



}

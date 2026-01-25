package com.pravin.EcommProject.controller;


import com.pravin.EcommProject.model.Product;
import com.pravin.EcommProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productservice;

    @GetMapping("/products")
    public List<Product> getProducts(){
return productservice.getAllProducts();

    }
}

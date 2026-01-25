package com.pravin.EcommProject.controller;


import com.pravin.EcommProject.model.Product;
import com.pravin.EcommProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    //ResponseEntity is used to customize the http codes in our project. In below
    //example if we send HttpStatus.ACCEPTED, it will be 202 in response code
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
return new ResponseEntity<>(productservice.getAllProducts(), HttpStatus.OK) ;

    }
}

package com.pravin.EcommProject.controller;


import com.pravin.EcommProject.model.Product;
import com.pravin.EcommProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product  = productservice.getProductById(id);
        if(product.getId()>0){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

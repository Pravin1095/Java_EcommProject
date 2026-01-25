package com.pravin.EcommProject.service;


import com.pravin.EcommProject.model.Product;
import com.pravin.EcommProject.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productrepo;
    public List<Product> getAllProducts() {
        return productrepo.findAll();
    }
}

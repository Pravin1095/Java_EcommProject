package com.pravin.EcommProject.service;


import com.pravin.EcommProject.model.Product;
import com.pravin.EcommProject.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productrepo;
    public List<Product> getAllProducts() {
        return productrepo.findAll();
    }

    public Product getProductById(Integer id) {
        return productrepo.findById(id).orElse(new Product(-1));

    }

    public Product addorUpdateProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        return productrepo.save(product);

    }

    public void deleteProduct(int id) {
        productrepo.deleteById(id);
    }
}

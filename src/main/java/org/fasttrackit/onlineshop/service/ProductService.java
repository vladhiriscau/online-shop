package org.fasttrackit.onlineshop.service;

import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.persistance.ProductRepositary;
import org.fasttrackit.onlineshop.transfer.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private  static  final Logger LOGGER =
            LoggerFactory.getLogger(ProductService.class);

    // Inversion of Control (IoC)
    private final ProductRepositary productRepositary;

    // Dependency Injection (from IoC container)
    @Autowired
    public ProductService(ProductRepositary productRepositary) {
        this.productRepositary = productRepositary;
    }

    public Product createProduct(SaveProductRequest request) {
        LOGGER.info("Creating product {}", request);
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setImageUrl(request.getImageUrl());

        return productRepositary.save(product);
    }

}

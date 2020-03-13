package org.fasttrackit.onlineshop.service;

import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshop.persistance.ProductRepositary;
import org.fasttrackit.onlineshop.transfer.product.GetProductsRequest;
import org.fasttrackit.onlineshop.transfer.product.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Product getProduct(long id) {
        LOGGER.info("Retrieving product {}", id);

        // optional usage explained
        //Optional<Product> productOptional = productRepositary.findById(id);

        //if (productOptional.isPresent()) {
            //return productOptional.get();
        //} else  {
           //throw new ResourceNotFoundException("Product " + id + " not found. ");
        //}
        return productRepositary.findById(id)
                // lambda exception
                .orElseThrow(() -> new ResourceNotFoundException("Product " + id + " not found. "));
    }

    public Page<Product> getProducts(GetProductsRequest request, Pageable pageable) {
        LOGGER.info("Searching products: {}", request);
        if (request !=null) {
            if (request.getPatialName() != null && request.getMinQuantity() != null) {
                return productRepositary.findByNameContainingAndQuantityGreaterThan(
                        request.getPatialName(), request.getMinQuantity(), pageable);
            } else if (request.getPatialName() != null) {
                return  productRepositary.findByNameContaining(request.getPatialName(), pageable);
            }
        }
        return productRepositary.findAll(pageable);
    }

    public Product updateProduct(long id, SaveProductRequest request) {
        LOGGER.info("Updating product {}: {}", id, request);
        Product product = getProduct(id);

        BeanUtils.copyProperties(request, product);

        return productRepositary.save(product);
    }

    public void deleteProduct(long id) {
        LOGGER.info("Deleting product {}", id);
        productRepositary.deleteById(id);
    }
}

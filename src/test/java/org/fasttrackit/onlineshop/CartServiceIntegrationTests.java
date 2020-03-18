package org.fasttrackit.onlineshop;

import org.fasttrackit.onlineshop.service.CartService;
import org.fasttrackit.onlineshop.service.CustomerService;
import org.fasttrackit.onlineshop.steps.CustomerTestSteps;
import org.fasttrackit.onlineshop.transfer.cart.AddProductsToCartRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartServiceIntegrationTests {

    @Autowired
    private CartService cartService;



    @Autowired
    private CustomerTestSteps testSteps;
    @Test
    void  addProductToCart_whenNewCart_thenCartIsCreated()  {
        AddProductsToCartRequest cartRequest = new AddProductsToCartRequest();
        cartRequest.setCustomerId(customer.getId());

        cartService.addProductsToCart(cartRequest);

    }

}


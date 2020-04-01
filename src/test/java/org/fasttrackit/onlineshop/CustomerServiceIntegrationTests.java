package org.fasttrackit.onlineshop;

import org.fasttrackit.onlineshop.domain.Cart;
import org.fasttrackit.onlineshop.domain.Customer;
import org.fasttrackit.onlineshop.service.CustomerService;
import org.fasttrackit.onlineshop.transfer.customer.SaveCustomerRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.number.OrderingComparison.greaterThan;

@SpringBootTest
public class CustomerServiceIntegrationTests {

    @Autowired
    private CustomerService customerService;

    @Test
    void createCustomer() {
        SaveCustomerRequest request = new SaveCustomerRequest();
        request.setFirstName("FirstName");
        request.setFirstName("LastName");

        Customer customer = customerService.createCustomer(request);

        assertThat(customer, notNullValue());
        assertThat(customer.getId(), greaterThan(0L));
        assertThat(customer.getFirstName(), is(request.getFirstName()));
        assertThat(customer.getLasttName(), is(request.getLastName()));

        cartService.getCart(customer.getId());

        Cart cart = cartService.getCart(customer.getId());

        assertThat(cart, notNullValue());
        assertThat(cart.getId()), is(customer.getId());

        assertThat(cart.getProducts(), notNullValue());
        assertThat(cart.getProducts(), hasSize(1));

        assertThat(cart.getProducts().iterator());
    }

}

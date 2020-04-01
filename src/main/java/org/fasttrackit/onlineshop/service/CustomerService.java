package org.fasttrackit.onlineshop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.onlineshop.domain.Customer;
import org.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshop.persistance.CustomerRepository;
import org.fasttrackit.onlineshop.transfer.customer.SaveCustomerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }



    public Customer createCustomer(SaveCustomerRequest request) {
        LOGGER.info("Creating customer {}", request);

        Customer customer = objectMapper.convertValue(request, Customer.class);
        return customerRepository.save(customer);
    }

    public Customer getCustomer(long id) {
        LOGGER.info("Retrieving customer {}", id);

        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer " + id + " not found"));
    }

    public Customer updateCustomer(long id, SaveCustomerRequest request) {
        LOGGER.info("Updating customer {}", id);
        Customer customer = getCustomer(id);
        BeanUtils.copyProperties(request, customer);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(long id) {
        LOGGER.info("Deleting customer {}",id);
        customerRepository.deleteById(id);
    }
}
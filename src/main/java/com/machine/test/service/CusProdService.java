package com.machine.test.service;


import com.machine.test.dto.CustomerDto;
import com.machine.test.entity.Customer;
import com.machine.test.entity.Product;

import java.util.List;

public interface CusProdService {
    Customer findCustomerById(int id);

    Product findProductById(int id);

    List<Customer> findAllCustomers();

    CustomerDto saveCusProd(CustomerDto customerDto);

    CustomerDto updateCusProd(CustomerDto customerDto);

    String deleteCustomerById(int id);

    List<Product> findAllProducts();
}

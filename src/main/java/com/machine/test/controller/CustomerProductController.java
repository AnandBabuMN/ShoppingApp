package com.machine.test.controller;

import com.machine.test.dto.CustomerDto;
import com.machine.test.entity.Customer;
import com.machine.test.entity.Product;
import com.machine.test.service.CusProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class CustomerProductController {

    @Autowired
    CusProdService cusProdService;

    @GetMapping("/getAllProducts")
    public List<Product> findAllProduct() {
        return cusProdService.findAllProducts();
    }

    @GetMapping("/getAllCustomers")
    public List<Customer> findAllCustomers() {
        return cusProdService.findAllCustomers();
    }

    @GetMapping("/getProductById/{id}")
    public Product findProductById(@PathVariable int id) {
        return cusProdService.findProductById(id);
    }

    @GetMapping("/getCustomerById/{id}")
    public Customer findCustomerById(@PathVariable int id) {
        return cusProdService.findCustomerById(id);
    }

    @DeleteMapping("/deleteCustomerById/{id}")
    public String deleteCustomerById(@PathVariable int id) {
        return cusProdService.deleteCustomerById(id);
    }

    @PostMapping("/saveCustomerProduct")
    public CustomerDto saveCusProd(@RequestBody CustomerDto customerDto) {
        return cusProdService.saveCusProd(customerDto);
    }

    @PutMapping("/updateCustomerProduct")
    public CustomerDto updateCusProd(@RequestBody CustomerDto customerDto) {
        return cusProdService.updateCusProd(customerDto);
    }
}

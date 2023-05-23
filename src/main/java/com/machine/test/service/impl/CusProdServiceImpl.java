package com.machine.test.service.impl;

import com.machine.test.dto.CustomerDto;
import com.machine.test.entity.Customer;
import com.machine.test.entity.Product;
import com.machine.test.exception.ServiceException;
import com.machine.test.repository.CustomerRepository;
import com.machine.test.repository.ProductRepository;
import com.machine.test.service.CusProdService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.machine.test.exception.ErrorCodes.CUSTOMER_NOT_FOUND;
import static com.machine.test.exception.ErrorCodes.PRODUCT_NOT_FOUND;

@Service
public class CusProdServiceImpl implements CusProdService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public CusProdServiceImpl(CustomerRepository customerRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Customer findCustomerById(int id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new ServiceException(CUSTOMER_NOT_FOUND.getErrorCode(), CUSTOMER_NOT_FOUND.getErrorDesc()));
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ServiceException(PRODUCT_NOT_FOUND.getErrorCode(), PRODUCT_NOT_FOUND.getErrorDesc()));
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerDto saveCusProd(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        return modelMapper.map(customerRepository.save(customer), CustomerDto.class);
    }

    @Override
    public CustomerDto updateCusProd(CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getCustomerId()).orElseThrow(() ->
                new ServiceException(CUSTOMER_NOT_FOUND.getErrorCode(), CUSTOMER_NOT_FOUND.getErrorDesc()));
        List<Product> products = customerDto.getProducts().stream().map(product -> {
            Product savedProduct = productRepository.findById(product.getProductId()).orElseThrow(() -> new ServiceException(PRODUCT_NOT_FOUND.getErrorCode(), PRODUCT_NOT_FOUND.getErrorDesc()));
            savedProduct.setProductName(product.getProductName());
            savedProduct.setPrice(product.getPrice());
            savedProduct.setQuantity(product.getQuantity());
            return savedProduct;
        }).collect(Collectors.toList());
        customer.setProducts(products);
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setAddress(customerDto.getAddress());
        customer.setContactNo(customerDto.getContactNo());
        return modelMapper.map(customerRepository.save(customer), CustomerDto.class);
    }

    @Override
    public String deleteCustomerById(int id) {
        try {
            customerRepository.deleteById(id);
        } catch (ServiceException e) {
            throw new ServiceException(CUSTOMER_NOT_FOUND.getErrorCode(), CUSTOMER_NOT_FOUND.getErrorDesc());
        }
        return "Customer with given " + id + " has been Deleted";
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}

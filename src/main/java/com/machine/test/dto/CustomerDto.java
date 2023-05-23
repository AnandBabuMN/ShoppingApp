package com.machine.test.dto;

import com.machine.test.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDto {
    private int customerId;
    private String customerName;
    private long contactNo;
    private String address;
    private List<Product> products;
}

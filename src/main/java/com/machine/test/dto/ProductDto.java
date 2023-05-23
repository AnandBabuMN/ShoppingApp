package com.machine.test.dto;

import com.machine.test.entity.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String productName;
    private double price;
    private int quantity;
}

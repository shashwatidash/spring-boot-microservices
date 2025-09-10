package com.example.accounts.entity;

import com.example.accounts.dto.CustomerDto;

public class CustomerMapper {
    public static CustomerDto toDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerName(customer.getCustomerName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        return customerDto;
    }

    public static Customer toEntity(CustomerDto customerDto, Customer customer) {
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        return customer;
    }
}

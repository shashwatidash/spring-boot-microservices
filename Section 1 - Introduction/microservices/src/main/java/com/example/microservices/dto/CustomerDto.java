package com.example.microservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Customer name cannot be empty")
    @Size(min = 2, max = 20, message = "Customer name should have at least 2 characters and not exceed 20 characters")
    private String customerName;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "$|[0-9]{10}", message = "Phone number should be valid with 10 digits")
    private String phoneNumber;
    
    private AccountsDto accountsDto;
}

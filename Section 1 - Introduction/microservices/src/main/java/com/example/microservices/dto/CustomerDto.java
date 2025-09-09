package com.example.microservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer details with account information")
public class CustomerDto {

    @NotEmpty(message = "Customer name cannot be empty")
    @Size(min = 2, max = 20, message = "Customer name should have at least 2 characters and not exceed 20 characters")
    @Schema(description = "Name of the Customer", example = "John Doe")
    private String customerName;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Schema(description = "Email of the Customer", example = "john.doe@example.com")
    private String email;

    @NotEmpty(message = "Phone number cannot be empty")
    @Schema(description = "Phone number of the Customer", example = "1234567890")
    @Pattern(regexp = "$|[0-9]{10}", message = "Phone number should be valid with 10 digits")
    private String phoneNumber;

    @Schema(description = "Account details of the Customer")
    private AccountsDto accountsDto;
}

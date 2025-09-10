package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts")
public class AccountsDto {

    @NotEmpty(message = "Account number cannot be empty")
    @Pattern(regexp = "$|[0-9]{10}", message = "Account number should be valid with 10 digits")
    @Schema(description = "Account number of the Customer")
    private Long accountNumber;

    @NotEmpty(message = "Account type cannot be empty")
    @Schema(description = "Type of the Account", example = "Savings")
    private String accountType;

    @NotEmpty(message = "Branch address cannot be empty")
    @Schema(description = "Branch address of the Account", example = "123, Main Street, City")
    private String branchAddress;
}
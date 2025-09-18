package com.example.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Loans", description = "Schema to hold Loans information")
public class LoansDto {

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp="(^$|[0-9]{10})", message="Phone number must be 10 digits")
    @Schema(description = "Phone number of Customer")
    private String phoneNumber;

    @NotEmpty(message = "Loan number cannot be empty")
    @Pattern(regexp="(^$|[0-9]{12})", message="Loan number must be 12 digits")
    @Schema(description = "Loan number of Customer")
    private String loanNumber;

    @NotEmpty(message = "Loan Type cannot be empty")
    @Schema(description = "Type of Loan", example = "Education")
    private String loanType;

    @PositiveOrZero(message = "Total loan must be positive or zero")
    @Schema(description = "Amount of loan", example = "500000")
    private int totalLoan;

    @PositiveOrZero(message = "Amount paid must be positive or zero")
    @Schema(description = "Total loan amount")
    private int amountPaid;

    @PositiveOrZero(message = "Outstanding amount must be positive or zero")
    @Schema(description = "Total outstanding amount against a loan")
    private int outstandingAmount;
}

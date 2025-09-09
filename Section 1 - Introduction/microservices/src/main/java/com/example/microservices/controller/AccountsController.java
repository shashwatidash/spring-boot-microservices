package com.example.microservices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.constants.AccountConstants;
import com.example.microservices.dto.CustomerDto;
import com.example.microservices.dto.ResponseDto;
import com.example.microservices.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@Validated
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Tag(name = "Accounts APIs", description = "CRUD REST APIs for managing customer accounts")
public class AccountsController {

    private IAccountsService accountsService;

    @PostMapping("/account")
    @Operation(summary = "Create Account", description = "API to create a new Customer and Account")
    @ApiResponse(responseCode = "201", description = "HTTPS Status 201 Created")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/account")
    @Operation(summary = "Get Account Details", description = "API to fetch account details using customer phone number")
    @ApiResponse(responseCode = "200", description = "HTTPS Status 200 OK")
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam
        @Pattern(regexp = "$|[0-9]{10}", message = "Phone number must be 10 digits")
        String phoneNumber) {
        CustomerDto customerDto = accountsService.getAccountDetails(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping("/account")
    @Operation(summary = "Update Account Details", description = "API to update existing Customer and Account details using customer phone number")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "HTTPS Status 200 OK"),
        @ApiResponse(responseCode = "500", description = "HTTPS Status 500 INTERNAL SERVER ERROR")
    })
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccountDetails(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.MESSAGE_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/account")
    @Operation(summary = "Delete Account", description = "API to delete existing Customer and Account using customer phone number")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "HTTPS Status 200 OK"),
        @ApiResponse(responseCode = "500", description = "HTTPS Status 500 INTERNAL SERVER ERROR")
    })
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String phoneNumber) {
        boolean isDeleted = accountsService.deleteAccount(phoneNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }
}
package com.example.loans.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loans.service.ILoansService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.loans.dto.LoansDto;
import com.example.loans.dto.ResponseDto;
import com.example.loans.constants.LoanConstants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@Tag(name = "Loans APIs", description = "CRUD REST APIs for managing customer loans")
public class LoansController {
    private ILoansService loansService;

    @GetMapping("/loans")
    public ResponseEntity<ResponseDto> getMethodName(@RequestParam @Pattern(regexp = "^$|[0-9]{10}", message = "Phone number must be 10 digits") String phoneNumber) {
        loansService.createLoan(phoneNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }
    
    @PostMapping("/loans")
    public ResponseEntity<LoansDto> postMethodName(@RequestParam @Pattern(regexp = "^$|[0-9]{10}", message = "Phone number must be 10 digits") String phoneNumber) {
        LoansDto loansDto = loansService.getLoanDetails(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @PutMapping("/loans")
    public ResponseEntity<ResponseDto> putMethodName(@RequestBody LoansDto loansDto) {
        boolean isUpdated = loansService.updateLoanAmount(loansDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/loans")
    public ResponseEntity<ResponseDto> deleteLoans(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone Number must be 10 digits") String phoneNumber) {
        boolean isDeleted = loansService.deleteLoan(phoneNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));
        }
    }
}

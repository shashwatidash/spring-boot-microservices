package com.example.loans.service;

import com.example.loans.dto.LoansDto;

public interface ILoansService {
    void createLoan(String phoneNumber);

    LoansDto getLoanDetails(String phoneNumber);

    boolean updateLoanAmount(LoansDto loansDto);

    boolean deleteLoan(String phoneNumber);
}

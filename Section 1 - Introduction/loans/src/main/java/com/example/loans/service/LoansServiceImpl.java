package com.example.loans.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.loans.constants.LoanConstants;
import com.example.loans.dto.LoansDto;
import com.example.loans.entity.Loans;
import com.example.loans.exception.LoanAlreadyExists;
import com.example.loans.exception.ResourceNotFound;
import com.example.loans.mapper.LoansMapper;
import com.example.loans.repository.LoansRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    @Override
    public void createLoan(String phoneNumber) {
        Optional<Loans> optionalLoans= loansRepository.findByPhoneNumber(phoneNumber);
        if (optionalLoans.isPresent()) {
            throw new LoanAlreadyExists("Loan already registered with given mobileNumber " + phoneNumber);
        }
        loansRepository.save(createNewLoan(phoneNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setPhoneNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public boolean deleteLoan(String phoneNumber) {
        Loans loans = loansRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new ResourceNotFound("Loan", "phoneNumber", phoneNumber));
        loansRepository.deleteById(loans.getLoanId());
        return true;
    }

    @Override
    public LoansDto getLoanDetails(String phoneNumber) {
        Loans loans = loansRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new ResourceNotFound("Loans", "phoneNumber", phoneNumber));
        return LoansMapper.toDto(loans, new LoansDto());
    }

    @Override
    public boolean updateLoanAmount(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(() -> new ResourceNotFound("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoansMapper.toEntity(loansDto, loans);
        loansRepository.save(loans);
        return  true;
    }
}

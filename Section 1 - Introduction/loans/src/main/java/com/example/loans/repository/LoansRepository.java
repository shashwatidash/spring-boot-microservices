package com.example.loans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loans.entity.Loans;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {
    Optional<Loans> findByPhoneNumber(String phoneNumber);
    Optional<Loans> findByLoanNumber(String loanNumber);
}
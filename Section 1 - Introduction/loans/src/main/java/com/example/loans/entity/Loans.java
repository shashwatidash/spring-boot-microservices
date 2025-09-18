package com.example.loans.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Loans extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String loanNumber;
    
    private String phoneNumber;

    private String loanType;

    private int totalLoan;

    private int amountPaid;
    
    private int outstandingAmount;

    @Override
    public String toString() {
        return "Loans {" + 
                "loanNumber=" + loanNumber +
                ", phoneNumber=" + phoneNumber +
                ", loanType='" + loanType + '\'' +
                ", totalLoan='" + totalLoan + '\'' +
                ", amountPaid='" + amountPaid + '\'' +
                ", outstandingAmount='" + outstandingAmount + '\'' +
                "} " + super.toString(); 
    }

}

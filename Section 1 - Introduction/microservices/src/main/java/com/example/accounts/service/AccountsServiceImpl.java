package com.example.accounts.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.accounts.constants.AccountConstants;
import com.example.accounts.dto.AccountsDto;
import com.example.accounts.dto.CustomerDto;
import com.example.accounts.entity.Accounts;
import com.example.accounts.entity.AccountsMapper;
import com.example.accounts.entity.Customer;
import com.example.accounts.entity.CustomerMapper;
import com.example.accounts.exceptions.CustomerAlreadyExists;
import com.example.accounts.exceptions.ResourceNotFound;
import com.example.accounts.repository.AccountsRepository;
import com.example.accounts.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService{
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.toEntity(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByPhoneNumber(customer.getPhoneNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExists("Customer aready exists with phone number: " + customer.getPhoneNumber()); 
        }

        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());

        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto getAccountDetails(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber).orElseThrow(
            () -> new ResourceNotFound("Customer", "phoneNumber", phoneNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
            () -> new ResourceNotFound("Account", "customerId", String.valueOf(customer.getCustomerId()))
        );

        CustomerDto customerDto = CustomerMapper.toDto(customer);
        customerDto.setAccountsDto(AccountsMapper.toDto(accounts));

        return customerDto;
    }

    @Override
    public boolean updateAccountDetails(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                () -> new ResourceNotFound("Account", "Account Number", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.toEntity(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFound("Customer", "CustomerId", customerId.toString())
            );
            CustomerMapper.toEntity(customerDto, customer);
            customer = customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String phoneNumber) {
        boolean isDeleted = false;
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber).orElseThrow(
            () -> new ResourceNotFound("Customer", "phoneNumber", phoneNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
            () -> new ResourceNotFound("Account", "customerId", String.valueOf(customer.getCustomerId()))
        );

        accountsRepository.deleteById(accounts.getAccountNumber());
        customerRepository.deleteById(customer.getCustomerId());
        isDeleted = true;
        return isDeleted;
    }
}

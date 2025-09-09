package com.example.microservices.service;

import com.example.microservices.dto.CustomerDto;

public interface IAccountsService {
    /*
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /*
     * @param phoneNumber - Phone number of the customer
     * @return CustomerDto - CustomerDto Object
     */
    CustomerDto getAccountDetails(String phoneNumber);

    /*
     * @param customerDto - CustomerDto Object
     * @return boolean - true if account details are updated successfully, false otherwise
     */
    boolean updateAccountDetails(CustomerDto customerDto);

    /*
     * @param phoneNumber - Phone number of the customer
     * @return boolean - true if account is deleted successfully, false otherwise
     */
    boolean deleteAccount(String phoneNumber);
}

package com.rayanen.banking.service;

import com.rayanen.banking.dto.*;
import com.rayanen.banking.model.entity.LegalCustomer;
import com.rayanen.banking.model.entity.RealCustomer;
import com.rayanen.banking.model.entity.SavingAccount;


import java.util.List;


public interface BankingService {


     Object saveLegalCustomer(LegalCustomer legalCustomer) ;

     Object saveRealCustomer(RealCustomer realCustomer) ;

     Object searchLegal(String legalCode) ;

     Object searchReal(String nationalCode);

     Object advanceLegalSearch(String advanceSearchDto) ;

     Object advanceRealSearch(String advanceSearchDto) ;

     String updateLegal(LegalCustomerDto legalCustomerDto) ;

     String updateReal(RealCustomerDto realCustomerDto) ;

     Object savingAccountForReal(String legalCode) ;

     Object savingAccountForLegal(String nationalCode) ;

     Object searchByAccountNumber(Integer accountNumber);

     Object deposit(TransactionRequirementsDto transactionRequirementsDto) ;

     Object withdrawal(TransactionRequirementsDto transactionRequirementsDto) ;

     Object transferMoney(TransferMoneyDto transferMoneyDto) ;


}

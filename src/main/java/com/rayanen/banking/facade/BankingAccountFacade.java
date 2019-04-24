package com.rayanen.banking.facade;
import com.rayanen.banking.dto.*;


public interface BankingAccountFacade {

     Object saveNewLegalCustomer(LegalCustomerDto legalCustomerDto);

     Object saveNewRealCustomer(RealCustomerDto realCustomerDto);

     Object searchLegal(String legalCode);

     Object searchReal(String nationalCode);

     Object advanceLegalSearch(String name);

     Object advanceRealSearch(String name);

     String updateLegal(LegalCustomerDto legalCustomerDto);

     String updateReal(RealCustomerDto realCustomerDto);

     Object savingAccountForReal(SearchDto searchDto);

     Object savingAccountForLegal(SearchDto searchDto);

     Object searchByAccountNumber(SearchDto searchDto);

     Object withdrawal(TransactionRequirementsDto transactionRequirementsDto);

     Object deposit(TransactionRequirementsDto transactionRequirementsDto);

     Object transferMoney(TransferMoneyDto transferMoneyDto);

}

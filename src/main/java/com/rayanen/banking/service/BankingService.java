package com.rayanen.banking.service;

import com.rayanen.banking.dto.*;
import com.rayanen.banking.model.entity.LegalCustomer;
import com.rayanen.banking.model.entity.RealCustomer;
import com.rayanen.banking.model.entity.SavingAccount;


import java.util.List;


public interface BankingService {


     Object saveLegalCustomer(LegalCustomer legalCustomer) ;

     Object saveRealCustomer(RealCustomer realCustomer) ;

     Object searchLegal(SearchDto searchDto) ;

     Object searchReal(SearchDto searchDto);

     Object advanceLegalSearch(AdvanceSearchDto advanceSearchDto) ;

     Object advanceRealSearch(AdvanceSearchDto advanceSearchDto) ;

     String updateLegal(LegalCustomerDto legalCustomerDto) ;

     String updateReal(RealCustomerDto realCustomerDto) ;

     Object savingAccountForReal(SearchDto searchDto) ;

     Object savingAccountForLegal(SearchDto searchDto) ;

     Object searchByAccountNumber(SearchDto searchDto);

     Object deposit(TransactionRequirementsDto transactionRequirementsDto) ;

     Object withdrawal(TransactionRequirementsDto transactionRequirementsDto) ;

     Object transferMoney(TransferMoneyDto transferMoneyDto) ;


}

package com.rayanen.banking.service;

import com.rayanen.banking.dto.*;
import com.rayanen.banking.model.entity.LegalCustomer;
import com.rayanen.banking.model.entity.RealCustomer;
import com.rayanen.banking.model.entity.SavingAccount;


import java.util.List;


public interface BankingService {


     ResponseDto<String> saveLegalCustomer(LegalCustomer legalCustomer) ;

     ResponseDto<String> saveRealCustomer(RealCustomer realCustomer) ;

     ResponseDto<CustomerDto> searchLegal(SearchDto searchDto) ;

     ResponseDto<CustomerDto> searchReal(SearchDto searchDto);

     ResponseDto<List<LegalCustomer>> advanceLegalSearch(AdvanceSearchDto advanceSearchDto) ;

     ResponseDto<List<RealCustomer>> advanceRealSearch(AdvanceSearchDto advanceSearchDto) ;

     ResponseDto<RealCustomerDto> updateLegal(LegalCustomerDto legalCustomerDto) ;

     ResponseDto<RealCustomerDto> updateReal(RealCustomerDto realCustomerDto) ;

     ResponseDto<String> savingAccountForReal(SearchDto searchDto) ;

     ResponseDto<String> savingAccountForLegal(SearchDto searchDto) ;

     ResponseDto<RealCustomerDto> searchByAccountNumber(SearchDto searchDto);

     ResponseDto<RealCustomerDto> deposit(TransactionRequirementsDto transactionRequirementsDto) ;

     ResponseDto<RealCustomerDto> withdrawal(TransactionRequirementsDto transactionRequirementsDto) ;

     ResponseDto<RealCustomerDto> transferMoney(TransferMoneyDto transferMoneyDto) ;


}

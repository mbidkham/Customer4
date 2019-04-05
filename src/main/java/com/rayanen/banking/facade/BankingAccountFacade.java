package com.rayanen.banking.facade;
import com.rayanen.banking.dto.*;


public interface BankingAccountFacade {

     ResponseDto saveNewLegalCustomer(LegalCustomerDto legalCustomerDto);

     ResponseDto saveNewRealCustomer(RealCustomerDto realCustomerDto);

     ResponseDto searchLegal(SearchDto searchDto);

     ResponseDto searchReal(SearchDto searchDto);

     ResponseDto advanceLegalSearch(AdvanceSearchDto advanceSearchDto);

     ResponseDto advanceRealSearch(AdvanceSearchDto advanceSearchDto);

     ResponseDto updateLegal(LegalCustomerDto legalCustomerDto);

     ResponseDto updateReal(RealCustomerDto realCustomerDto);

     ResponseDto savingAccountForReal(SearchDto searchDto);

     ResponseDto savingAccountForLegal(SearchDto searchDto);

     ResponseDto searchByAccountNumber(SearchDto searchDto);

     ResponseDto withdrawal(TransactionRequirementsDto transactionRequirementsDto);

     ResponseDto deposit(TransactionRequirementsDto transactionRequirementsDto);

     ResponseDto transferMoney(TransferMoneyDto transferMoneyDto);

}

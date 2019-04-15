package com.rayanen.banking.facade;
import com.rayanen.banking.dto.*;


public interface BankingAccountFacade {

     Object saveNewLegalCustomer(LegalCustomerDto legalCustomerDto);

     Object saveNewRealCustomer(RealCustomerDto realCustomerDto);

     Object searchLegal(SearchDto searchDto);

     Object searchReal(SearchDto searchDto);

     Object advanceLegalSearch(AdvanceSearchDto advanceSearchDto);

     Object advanceRealSearch(AdvanceSearchDto advanceSearchDto);

     String updateLegal(LegalCustomerDto legalCustomerDto);

     String updateReal(RealCustomerDto realCustomerDto);

     Object savingAccountForReal(SearchDto searchDto);

     Object savingAccountForLegal(SearchDto searchDto);

     Object searchByAccountNumber(SearchDto searchDto);

     Object withdrawal(TransactionRequirementsDto transactionRequirementsDto);

     Object deposit(TransactionRequirementsDto transactionRequirementsDto);

     Object transferMoney(TransferMoneyDto transferMoneyDto);

}

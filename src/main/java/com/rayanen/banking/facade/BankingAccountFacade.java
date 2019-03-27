package com.rayanen.banking.facade;

import com.rayanen.banking.dto.*;
import com.rayanen.banking.model.entity.LegalCustomer;
import com.rayanen.banking.model.entity.RealCustomer;
import com.rayanen.banking.model.entity.SavingAccount;
import com.rayanen.banking.service.BankingService;
import com.rayanen.banking.service.MapperClass;

public class BankingAccountFacade {

    public ResponseDto<String> saveNewLegalCustomer(LegalCustomerDto legalCustomerDto){
        BankingService bankingService=new BankingService();
       return bankingService.saveLegalCustomer((LegalCustomer) MapperClass.mapper(new LegalCustomer(), legalCustomerDto)) ;


    }
    public void saveNewRealCustomer(RealCustomerDto realCustomerDto){
        BankingService bankingService=new BankingService();
        bankingService.saveRealCustomer((RealCustomer) MapperClass.mapper(new RealCustomer(), realCustomerDto)) ;

    }
    public void searchReal(SearchDto searchDto){
        BankingService bankingService=new BankingService();
        bankingService.searchReal(searchDto);

    }

    public void searchLegal(SearchDto searchDto){
        BankingService bankingService=new BankingService();
        bankingService.searchLegal(searchDto);

    }
    public void advanceLegalSearch(AdvanceSearchDto advanceSearchDto){

        BankingService bankingService=new BankingService();
        bankingService.advanceLegalSearch(advanceSearchDto);
    }
    public void advanceRealSearch(AdvanceSearchDto advanceSearchDto){

        BankingService bankingService=new BankingService();
        bankingService.advanceRealSearch(advanceSearchDto);
    }
    public void updateLegal(SearchDto searchDto){
        BankingService bankingService = new BankingService();
        bankingService.updateLegal(searchDto);

    }

    public void updateReal(SearchDto searchDto){
        BankingService bankingService = new BankingService();
        bankingService.updateReal(searchDto);

    }


    public void savingAccountForReal(RealCustomerDto realCustomerDto){
        BankingService bankingService = new BankingService();
        bankingService.savingAccountForReal((RealCustomer) MapperClass.mapper(new RealCustomer(), realCustomerDto)) ;


    }

    public void savingAccountForLegal(LegalCustomerDto legalCustomerDto){
        BankingService bankingService = new BankingService();
        bankingService.savingAccountForLegal((LegalCustomer) MapperClass.mapper(new LegalCustomer(), legalCustomerDto)) ;


    }




    public void searchByAccountNumber(SearchDto searchDto){
        BankingService bankingService = new BankingService();
        bankingService.searchByAccountNumber(searchDto);

    }
    public void withdrawal(SavingAccountDto savingAccountDto){

        BankingService bankingService = new BankingService();
        bankingService.withdrawal((SavingAccount) MapperClass.mapper(new SavingAccount() , savingAccountDto));
    }
    public void deposit(SavingAccountDto savingAccountDto){
         BankingService  bankingService  = new BankingService();

         bankingService.deposit((SavingAccount) MapperClass.mapper(new SavingAccount() , savingAccountDto));
    }

    public void transferMoney(TransferMoneyDto transferMoneyDto){
        BankingService bankingService = new BankingService();
        bankingService.transferMoney(transferMoneyDto);

    }


}

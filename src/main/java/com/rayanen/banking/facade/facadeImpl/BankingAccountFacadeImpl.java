package com.rayanen.banking.facade.facadeImpl;

import com.rayanen.banking.dto.*;
import com.rayanen.banking.facade.BankingAccountFacade;
import com.rayanen.banking.model.entity.LegalCustomer;
import com.rayanen.banking.model.entity.RealCustomer;
import com.rayanen.banking.model.entity.SavingAccount;
import com.rayanen.banking.service.BankingService;

import com.rayanen.banking.utility.MapperClass;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BankingAccountFacadeImpl implements BankingAccountFacade {

    private BankingService bankingService;

    public BankingAccountFacadeImpl(BankingService bankingService){
        this.bankingService =  bankingService ;
    }


    public Object saveNewLegalCustomer(LegalCustomerDto legalCustomerDto){

       return bankingService.saveLegalCustomer( MapperClass.mapper(new LegalCustomer(), legalCustomerDto)) ;


    }
    public Object saveNewRealCustomer(RealCustomerDto realCustomerDto){


        return bankingService.saveRealCustomer(   MapperClass.mapper(new RealCustomer(), realCustomerDto)) ;

    }
    public Object searchReal(String nationalCode){

       return bankingService.searchReal(nationalCode);

    }

    public Object searchLegal(String legalCode){

        return bankingService.searchLegal(legalCode);

    }
    public Object advanceLegalSearch(String name){

        return bankingService.advanceLegalSearch(name);
    }

    public Object advanceRealSearch(String name){

        return bankingService.advanceRealSearch(name);
    }

    public String updateLegal(LegalCustomerDto legalCustomerDto){

        return bankingService.updateLegal(legalCustomerDto);

    }

    public String updateReal(RealCustomerDto realCustomerDto){

        return bankingService.updateReal(realCustomerDto);

    }



    public Object savingAccountForReal(String nationalCode){

        return bankingService.savingAccountForReal(nationalCode) ;

    }

    public Object savingAccountForLegal(String legalCode){

        return bankingService.savingAccountForLegal(legalCode) ;

    }

    public Object searchByAccountNumber(Integer accountNumber){

        return bankingService.searchByAccountNumber(accountNumber);

    }
    public Object withdrawal(TransactionRequirementsDto transactionRequirementsDto){

        return bankingService.withdrawal(transactionRequirementsDto);
    }

    public Object deposit(TransactionRequirementsDto transactionRequirementsDto){

         return bankingService.deposit(transactionRequirementsDto);
    }

    public Object transferMoney(TransferMoneyDto transferMoneyDto){

        return bankingService.transferMoney(transferMoneyDto);

    }


}

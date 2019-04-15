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

        RealCustomer realCustomer =  MapperClass.mapper(new RealCustomer(), realCustomerDto);

        return bankingService.saveRealCustomer(realCustomer) ;

    }
    public Object searchReal(SearchDto searchDto){

       return bankingService.searchReal(searchDto);

    }

    public Object searchLegal(SearchDto searchDto){

        return bankingService.searchLegal(searchDto);

    }
    public Object advanceLegalSearch(AdvanceSearchDto advanceSearchDto){

        return bankingService.advanceLegalSearch(advanceSearchDto);
    }

    public Object advanceRealSearch(AdvanceSearchDto advanceSearchDto){

        return bankingService.advanceRealSearch(advanceSearchDto);
    }

    public String updateLegal(LegalCustomerDto legalCustomerDto){

        return bankingService.updateLegal(legalCustomerDto);

    }

    public String updateReal(RealCustomerDto realCustomerDto){

        return bankingService.updateReal(realCustomerDto);

    }



    public Object savingAccountForReal(SearchDto searchDto){

        return bankingService.savingAccountForReal(searchDto) ;

    }

    public Object savingAccountForLegal(SearchDto searchDto){

        return bankingService.savingAccountForLegal(searchDto) ;

    }

    public Object searchByAccountNumber(SearchDto searchDto){

        return bankingService.searchByAccountNumber(searchDto);

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

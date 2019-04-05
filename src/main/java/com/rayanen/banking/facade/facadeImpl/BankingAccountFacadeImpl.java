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


    public ResponseDto<String> saveNewLegalCustomer(LegalCustomerDto legalCustomerDto){

       return bankingService.saveLegalCustomer((LegalCustomer) MapperClass.dtoToEntityMapper(new LegalCustomer(), legalCustomerDto)) ;


    }
    public ResponseDto<String> saveNewRealCustomer(RealCustomerDto realCustomerDto){

        RealCustomer realCustomer = (RealCustomer) MapperClass.dtoToEntityMapper(new RealCustomer(), realCustomerDto);

        return bankingService.saveRealCustomer(realCustomer) ;

    }
    public ResponseDto searchReal(SearchDto searchDto){

       return bankingService.searchReal(searchDto);

    }

    public ResponseDto searchLegal(SearchDto searchDto){

        return bankingService.searchLegal(searchDto);

    }
    public ResponseDto advanceLegalSearch(AdvanceSearchDto advanceSearchDto){

        return bankingService.advanceLegalSearch(advanceSearchDto);
    }

    public ResponseDto advanceRealSearch(AdvanceSearchDto advanceSearchDto){

        return bankingService.advanceRealSearch(advanceSearchDto);
    }

    public ResponseDto updateLegal(LegalCustomerDto legalCustomerDto){

        return bankingService.updateLegal(legalCustomerDto);

    }

    public ResponseDto updateReal(RealCustomerDto realCustomerDto){

        return bankingService.updateReal(realCustomerDto);

    }



    public ResponseDto savingAccountForReal(SearchDto searchDto){

        return bankingService.savingAccountForReal(searchDto) ;

    }

    public ResponseDto savingAccountForLegal(SearchDto searchDto){

        return bankingService.savingAccountForLegal(searchDto) ;

    }

    public ResponseDto searchByAccountNumber(SearchDto searchDto){

        return bankingService.searchByAccountNumber(searchDto);

    }
    public ResponseDto withdrawal(TransactionRequirementsDto transactionRequirementsDto){

        return bankingService.withdrawal(transactionRequirementsDto);
    }

    public ResponseDto deposit(TransactionRequirementsDto transactionRequirementsDto){

         return bankingService.deposit(transactionRequirementsDto);
    }

    public ResponseDto transferMoney(TransferMoneyDto transferMoneyDto){

        return bankingService.transferMoney(transferMoneyDto);

    }


}

package com.rayanen.banking.service.impl;

import com.rayanen.banking.dto.*;
import com.rayanen.banking.model.dao.LegalCustomerDao;
import com.rayanen.banking.model.dao.RealCustomerDao;
import com.rayanen.banking.model.dao.SavingAccountDao;
import com.rayanen.banking.model.entity.LegalCustomer;
import com.rayanen.banking.model.entity.RealCustomer;
import com.rayanen.banking.model.entity.SavingAccount;
import com.rayanen.banking.model.entity.Transaction;
import com.rayanen.banking.service.BankingService;
import com.rayanen.banking.utility.MapperClass;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Service
public class BankingServiceImpl implements BankingService {

    private LegalCustomerDao legalCustomerDao;
    private RealCustomerDao realCustomerDao;
    private SavingAccountDao savingAccountDao;

    public BankingServiceImpl(LegalCustomerDao legalCustomerDao , RealCustomerDao realCustomerDao , SavingAccountDao savingAccountDao ){
        this.legalCustomerDao = legalCustomerDao;
        this.realCustomerDao = realCustomerDao;
       this. savingAccountDao = savingAccountDao;
    }



    public ResponseDto<String> saveLegalCustomer(LegalCustomer legalCustomer) {


        if (Objects.nonNull(legalCustomer) && Objects.nonNull(legalCustomer.getId()) && Objects.nonNull(realCustomerDao.findById(legalCustomer.getId()))) {
            legalCustomerDao.save(legalCustomer);

            return new ResponseDto<>(ResponseStatus.Ok, null, "ویرایش با موفقیت انجام  شد !", null);


        }
        if (Objects.nonNull(legalCustomer) && Objects.nonNull(legalCustomer.getLegalCode()) && Objects.nonNull(legalCustomer.getName())) {

            if (Objects.isNull(legalCustomerDao.findByLegalCode(legalCustomer.getLegalCode()))) {
                legalCustomerDao.save(legalCustomer);

                return new ResponseDto<>(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
            } else {

                return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("قبلا ثبت نام کرده اید"));

            }

        } else {
            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("کد ثبت شرکت/ارگان یا اسم را وارد نکرده اید "));


        }


    }


    public ResponseDto saveRealCustomer(RealCustomer realCustomer) {


        if (Objects.nonNull(realCustomer) && Objects.nonNull(realCustomer.getId()) && Objects.nonNull(realCustomerDao.findById(realCustomer.getId()))) {
            realCustomerDao.save(realCustomer);

            return new ResponseDto<>(ResponseStatus.Ok, null, "با موفقیت ویرایش شد !", null);


        }
        if (Objects.nonNull(realCustomer) && Objects.nonNull(realCustomer.getNationalCode()) && Objects.nonNull(realCustomer.getName())) {

           if (Objects.isNull(realCustomerDao.findByNationalCode(realCustomer.getNationalCode()))) {
             realCustomerDao.save(realCustomer);

               return new ResponseDto<>(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
           } else {

               return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("قبلا ثبت نام کرده اید"));

         }

        } else {
            return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("کد ملی یا اسم را وارد نکرده اید "));


        }


    }


    public ResponseDto<CustomerDto> searchLegal(SearchDto searchDto) {


        LegalCustomer byLegalCode = legalCustomerDao.findByLegalCode((String) searchDto.getCode());


        if (Objects.isNull(byLegalCode)) {

            return new ResponseDto(ResponseStatus.Error, null, "", new ResponseException("پیدا نشد!"));


        } else{
            LegalCustomerDto legalCustomerDto = (LegalCustomerDto) MapperClass.entityToDtoMapper(new LegalCustomerDto(), byLegalCode);

            return new ResponseDto(ResponseStatus.Ok, legalCustomerDto, null, null);
        }

    }


    public ResponseDto<CustomerDto> searchReal(SearchDto searchDto) {


        RealCustomer byNationalCode = realCustomerDao.findByNationalCode((String) searchDto.getCode());


        if (Objects.isNull(byNationalCode)) {

            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("پیدا نشد!"));

        } else{
            RealCustomerDto realCustomerDto = (RealCustomerDto) MapperClass.entityToDtoMapper(new RealCustomerDto(), byNationalCode);

            return new ResponseDto(ResponseStatus.Ok, realCustomerDto, null, null);
        }

    }


    public ResponseDto<List<LegalCustomer>> advanceLegalSearch(AdvanceSearchDto advanceSearchDto) {

        List<LegalCustomer> byName = legalCustomerDao.findByName(advanceSearchDto.getName().toUpperCase());

        if (byName.size() == 0)
            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("پیدا نشد!"));


        return new ResponseDto<>(ResponseStatus.Ok, byName, null, null);
    }


    public ResponseDto<List<RealCustomer>> advanceRealSearch(AdvanceSearchDto advanceSearchDto) {

        List<RealCustomer> byName = realCustomerDao.findByName(advanceSearchDto.getName().toUpperCase());

        if (byName.size() != 0)
            return new ResponseDto(ResponseStatus.Ok, byName, null, null);

        return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("پیدا نشد!"));
    }


    public ResponseDto<RealCustomerDto> updateLegal(LegalCustomerDto legalCustomerDto) {


        LegalCustomer byLegalCode = legalCustomerDao.findByLegalCode(legalCustomerDto.getLegalCode());

        legalCustomerDao.save((LegalCustomer) MapperClass.dtoToEntityMapper(legalCustomerDto , byLegalCode));

        return new ResponseDto(ResponseStatus.Ok, byLegalCode, "با موفقیت ویرایش شد !", null);


    }


    public ResponseDto<RealCustomerDto> updateReal(RealCustomerDto realCustomerDto) {

        RealCustomer byReal = realCustomerDao.findByNationalCode( realCustomerDto.getNationalCode());

        realCustomerDao.save((RealCustomer) MapperClass.dtoToEntityMapper(realCustomerDto , byReal));

        return new ResponseDto(ResponseStatus.Ok, byReal, "با موفقیت ویرایش شد !",null);

    }


    public ResponseDto<String> savingAccountForReal(SearchDto searchDto) {
        RealCustomer foundByNationalCode = realCustomerDao.findByNationalCode((String) searchDto.getCode());


        if (Objects.isNull(foundByNationalCode)) {

            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("پیدا نشد!"));

        } else {
            SavingAccount savingAccount = new SavingAccount();

            String currentTime=Long.toString(System.currentTimeMillis()) ;

            String accountNumber=currentTime.substring(currentTime.length()-3)+foundByNationalCode.getId();

            savingAccount.setAccountNumber(Integer.parseInt(accountNumber));

            foundByNationalCode.getSavingAccounts().add(savingAccount);

            savingAccountDao.save(savingAccount);

            realCustomerDao.save(foundByNationalCode);

            return new ResponseDto(ResponseStatus.Ok, null, " سپرده جدید با شماره حساب "+ savingAccount.getAccountNumber() +"  برای شما ایجاد شد " , null);

        }


    }


    public ResponseDto<String> savingAccountForLegal(SearchDto searchDto) {


        LegalCustomer foundByLegalCode = legalCustomerDao.findByLegalCode((String) searchDto.getCode());

        if (Objects.isNull(foundByLegalCode)) {

            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("پیدا نشد!"));


        } else {

            SavingAccount savingAccount = new SavingAccount();
            String currentTime=Long.toString(System.currentTimeMillis()) ;
            String accountNumber=currentTime.substring(currentTime.length()-3)+foundByLegalCode.getId();
            savingAccount.setAccountNumber(Integer.parseInt(accountNumber));
            foundByLegalCode.getSavingAccounts().add(savingAccount);
            savingAccountDao.save(savingAccount);
            legalCustomerDao.save(foundByLegalCode);

            return new ResponseDto(ResponseStatus.Ok, null, "سپرده جدید برای شما ایجاد شد :)", null);

        }
    }

    public ResponseDto<RealCustomerDto> searchByAccountNumber(SearchDto searchDto) {

        SavingAccount findByNum = savingAccountDao.findByAccountNumber((Integer) searchDto.getCode());

        if (Objects.nonNull(findByNum))

            return new ResponseDto(ResponseStatus.Ok, findByNum, null, null);

        return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("پیدا نشد"));

    }

    public ResponseDto<RealCustomerDto> deposit(TransactionRequirementsDto transactionRequirementsDto) {

        SavingAccount savingAccount  = savingAccountDao.findByAccountNumber(transactionRequirementsDto.getSavingAccountNumber());

        if(Objects.nonNull(savingAccount)){

            savingAccount.setBalance(savingAccount.getBalance().add(transactionRequirementsDto.getAmount()));
            Transaction depositTransaction = new Transaction();
            depositTransaction.setAmount(transactionRequirementsDto.getAmount());
            depositTransaction.setTransactionType(TransactionType.DEPOSIT);
            savingAccount.getTransactions().add(depositTransaction);
            return new ResponseDto(ResponseStatus.Ok, null, "واریز انجام شد ", null);
        }
        return new ResponseDto(ResponseStatus.Error, null, null,new ResponseException("پیدا نشد ! "));




    }


    public ResponseDto<RealCustomerDto> withdrawal(TransactionRequirementsDto transactionRequirementsDto) {

        SavingAccount savingAccount = savingAccountDao.findByAccountNumber(transactionRequirementsDto.getSavingAccountNumber());
        if (Objects.nonNull(savingAccount)) {
            if (savingAccount.getBalance().compareTo(transactionRequirementsDto.getAmount()) >= 0) {
                savingAccount.getBalance().subtract(transactionRequirementsDto.getAmount());
                Transaction depositTransaction = new Transaction();
                depositTransaction.setAmount(transactionRequirementsDto.getAmount());
                depositTransaction.setTransactionType(TransactionType.WITHDRAWAL);
                savingAccount.getTransactions().add(depositTransaction);
                if (savingAccount.getBalance().compareTo(savingAccount.getMinBalance()) < 0)
                    savingAccount.setMinBalance(savingAccount.getBalance());
                return new ResponseDto(ResponseStatus.Ok, null, "برداشت  وجه با موفقیت انجام شد ", null);
            } else
                return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("موجودی کافی نیست "));

        }
        return new ResponseDto(ResponseStatus.Error, null, null,new ResponseException("پیدا نشد ! "));
    }

    public ResponseDto<RealCustomerDto> transferMoney(TransferMoneyDto transferMoneyDto) {
        SavingAccount sourceAccount = savingAccountDao.findByAccountNumber(transferMoneyDto.getSourceAccount());
        SavingAccount targetAccount = savingAccountDao.findByAccountNumber(transferMoneyDto.getDestinationAccount());

        if (Objects.nonNull(sourceAccount) && Objects.nonNull(targetAccount)) {
            if (sourceAccount.getBalance().compareTo(transferMoneyDto.getAmount()) >= 0) {
                sourceAccount.getBalance().subtract(transferMoneyDto.getAmount());
                if (sourceAccount.getBalance().compareTo(sourceAccount.getMinBalance()) < 0)
                    sourceAccount.setMinBalance(sourceAccount.getBalance());
                targetAccount.setBalance(targetAccount.getBalance().add(transferMoneyDto.getAmount()));
                Transaction transferTransaction = new Transaction();
                transferTransaction.setAmount(transferMoneyDto.getAmount());
                transferTransaction.setTransactionType(TransactionType.TRANSFER);
                sourceAccount.getTransactions().add(transferTransaction);
                targetAccount.getTransactions().add(transferTransaction);


                return new ResponseDto(ResponseStatus.Ok, null, "انتقال وجه با موفقیت انجام شد ", null);
            }
            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("موجودی کافی نیست "));

        }


        return new ResponseDto(ResponseStatus.Ok, null, "کاربر پیدا نشد ", null);


    }
}

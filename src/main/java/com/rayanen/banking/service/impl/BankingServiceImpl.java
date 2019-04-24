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


import javax.transaction.Transactional;
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



    @Transactional(rollbackOn = Exception.class)
    public Object saveLegalCustomer(LegalCustomer legalCustomer) {


        if (Objects.nonNull(legalCustomer) && Objects.nonNull(legalCustomer.getLegalCode()) && Objects.nonNull(legalCustomer.getName())) {

            if (Objects.isNull(legalCustomerDao.findByLegalCode(legalCustomer.getLegalCode()))) {

                legalCustomerDao.save(legalCustomer);

               return "اطلاعات ذخیره شد.";

            } else {

                return new ResponseException("قبلا ثبت نام کرده اید");

            }

        } else {

            return new ResponseException("کد ثبت شرکت/ارگان یا اسم را وارد نکرده اید ");


        }


    }

    @Transactional(rollbackOn = Exception.class)
    public Object saveRealCustomer(RealCustomer realCustomer)  {



        if (Objects.nonNull(realCustomer) && Objects.nonNull(realCustomer.getNationalCode()) && Objects.nonNull(realCustomer.getName())) {

           if (Objects.isNull(realCustomerDao.findByNationalCode(realCustomer.getNationalCode()))) {
             realCustomerDao.save(realCustomer);

               return "اطلاعات ذخیره شد.";

           } else {


               return new ResponseException("قبلا ثبت نام کرده اید");

         }

        } else {

              return new ResponseException("کد ملی یا اسم را وارد نکرده اید ");


        }


    }


    public Object searchLegal(String legalCode) {


        LegalCustomer byLegalCode = legalCustomerDao.findByLegalCode(legalCode);


        if (Objects.isNull(byLegalCode)) {

            return  new ResponseException("پیدا نشد!");


        } else{

            LegalCustomerDto legalCustomerDto =  MapperClass.mapper(new LegalCustomerDto(), byLegalCode);

            return  legalCustomerDto ;
        }

    }


    public Object searchReal(String nationalCode) {


        RealCustomer byNationalCode = realCustomerDao.findByNationalCode(nationalCode);


        if (Objects.isNull(byNationalCode)) {

            return new ResponseException("پیدا نشد!");

        } else{

            RealCustomerDto realCustomerDto =  MapperClass.mapper(new RealCustomerDto(), byNationalCode);

            return realCustomerDto;
        }

    }


    public Object advanceLegalSearch(String name) {

        List<LegalCustomer> byName = legalCustomerDao.findByName(name.toUpperCase());

        if (byName.size() == 0)
            return new ResponseException("پیدا نشد!");


        return byName ;
    }


    public Object advanceRealSearch(String name) {

        List<RealCustomer> byName = realCustomerDao.findByName(name.toUpperCase());

        if (byName.size() != 0)
            return  byName;

        return new ResponseException("پیدا نشد!");
    }

    @Transactional(rollbackOn = Exception.class)
    public String updateLegal(LegalCustomerDto legalCustomerDto) {


        LegalCustomer byLegalCode = legalCustomerDao.findByLegalCode(legalCustomerDto.getLegalCode());

        legalCustomerDao.save( MapperClass.mapper(  byLegalCode , legalCustomerDto));

        return "با موفقیت ویرایش شد !";


    }

    @Transactional(rollbackOn = Exception.class)
    public String updateReal(RealCustomerDto realCustomerDto) {

        RealCustomer byReal = realCustomerDao.findByNationalCode( realCustomerDto.getNationalCode());

        realCustomerDao.save( MapperClass.mapper(byReal , realCustomerDto ));

        return "با موفقیت ویرایش شد !";

    }

    @Transactional(rollbackOn = Exception.class)
    public Object savingAccountForReal(SearchDto searchDto) {

        RealCustomer foundByNationalCode = realCustomerDao.findByNationalCode((String) searchDto.getCode());


        if (Objects.isNull(foundByNationalCode)) {

            return  new ResponseException("پیدا نشد!");

        } else {
            SavingAccount savingAccount = new SavingAccount();

            String currentTime=Long.toString(System.currentTimeMillis()) ;

            String accountNumber=currentTime.substring(currentTime.length()-3)+foundByNationalCode.getId();

            savingAccount.setAccountNumber(Integer.parseInt(accountNumber));

            foundByNationalCode.getSavingAccounts().add(savingAccount);

            savingAccountDao.save(savingAccount);

            realCustomerDao.save(foundByNationalCode);

            return" سپرده جدید با شماره حساب " + savingAccount.getAccountNumber() +"  برای شما ایجاد شد " ;

        }


    }

    @Transactional(rollbackOn = Exception.class)
    public Object savingAccountForLegal(SearchDto searchDto) {


        LegalCustomer foundByLegalCode = legalCustomerDao.findByLegalCode((String) searchDto.getCode());

        if (Objects.isNull(foundByLegalCode)) {

            return new ResponseException("پیدا نشد!");


        } else {

            SavingAccount savingAccount = new SavingAccount();

            String currentTime=Long.toString(System.currentTimeMillis()) ;

            String accountNumber=currentTime.substring(currentTime.length()-3)+foundByLegalCode.getId();

            savingAccount.setAccountNumber(Integer.parseInt(accountNumber));

            foundByLegalCode.getSavingAccounts().add(savingAccount);

            savingAccountDao.save(savingAccount);

            legalCustomerDao.save(foundByLegalCode);

            return" سپرده جدید با شماره حساب " + savingAccount.getAccountNumber() +"  برای شما ایجاد شد ";

        }
    }

    public Object searchByAccountNumber(SearchDto searchDto) {

        SavingAccount findByNum = savingAccountDao.findByAccountNumber((Integer) searchDto.getCode());

        if (Objects.nonNull(findByNum))

            return  findByNum ;

        return  new ResponseException("پیدا نشد");

    }

    @Transactional(rollbackOn = Exception.class)
    public Object deposit(TransactionRequirementsDto transactionRequirementsDto) {

        SavingAccount savingAccount  = savingAccountDao.findByAccountNumber(transactionRequirementsDto.getSavingAccountNumber());

        if(Objects.nonNull(savingAccount)){

            savingAccount.setBalance(savingAccount.getBalance().add(transactionRequirementsDto.getAmount()));

            Transaction depositTransaction = new Transaction();

            depositTransaction.setAmount(transactionRequirementsDto.getAmount());

            depositTransaction.setTransactionType(TransactionType.DEPOSIT);

            savingAccount.getTransactions().add(depositTransaction);

            return  "واریز انجام شد ";
        }
        return new ResponseException("پیدا نشد ! ");




    }

    @Transactional(rollbackOn = Exception.class)
    public Object withdrawal(TransactionRequirementsDto transactionRequirementsDto) {

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

                return "برداشت  وجه با موفقیت انجام شد ";

            } else

                return  new ResponseException("موجودی کافی نیست ");

        }
        return new ResponseException("پیدا نشد ! ");
    }

    @Transactional(rollbackOn = Exception.class)
    public Object transferMoney(TransferMoneyDto transferMoneyDto) {
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


                return "انتقال وجه با موفقیت انجام شد ";
            }
            return new ResponseException("موجودی کافی نیست ");

        }


        return new ResponseException( "کاربر پیدا نشد ");


    }
}

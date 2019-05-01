package com.rayanen.banking.service.impl;

import com.rayanen.banking.controller.ProfitManagerController;
import com.rayanen.banking.dto.*;
import com.rayanen.banking.model.dao.LegalCustomerDao;
import com.rayanen.banking.model.dao.RealCustomerDao;
import com.rayanen.banking.model.dao.SavingAccountDao;
import com.rayanen.banking.model.dao.TransactionDao;
import com.rayanen.banking.model.entity.LegalCustomer;
import com.rayanen.banking.model.entity.RealCustomer;
import com.rayanen.banking.model.entity.SavingAccount;
import com.rayanen.banking.model.entity.Transaction;
import com.rayanen.banking.service.BankingService;
import com.rayanen.banking.utility.MapperClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BankingServiceImpl implements BankingService {

    Logger logger = LoggerFactory.getLogger(ProfitManagerController.class);

    private LegalCustomerDao legalCustomerDao;
    private RealCustomerDao realCustomerDao;
    private SavingAccountDao savingAccountDao;
    private TransactionDao transactionDao;

    public BankingServiceImpl(LegalCustomerDao legalCustomerDao , RealCustomerDao realCustomerDao , SavingAccountDao savingAccountDao , TransactionDao transactionDao){
        this.legalCustomerDao = legalCustomerDao;
        this.realCustomerDao = realCustomerDao;
       this. savingAccountDao = savingAccountDao;
       this.transactionDao = transactionDao;
    }



    @Transactional(rollbackOn = Exception.class)
    public Object saveLegalCustomer(LegalCustomer legalCustomer) {


        if (Objects.nonNull(legalCustomer) && Objects.nonNull(legalCustomer.getLegalCode()) && Objects.nonNull(legalCustomer.getName())) {

            if (Objects.isNull(legalCustomerDao.findByLegalCode(legalCustomer.getLegalCode()))) {

                legalCustomerDao.save(legalCustomer);

                logger.info("createdLegal");

               return "اطلاعات ذخیره شد.";


            } else {

                logger.error("duplicateUser");
                return new ResponseException("قبلا ثبت نام کرده اید");

            }

        } else {

            logger.error("NullFields");
            return new ResponseException("کد ثبت شرکت/ارگان یا اسم را وارد نکرده اید ");


        }


    }

    @Transactional(rollbackOn = Exception.class)
    public Object saveRealCustomer(RealCustomer realCustomer)  {



        if (Objects.nonNull(realCustomer) && Objects.nonNull(realCustomer.getNationalCode()) && Objects.nonNull(realCustomer.getName())) {

           if (Objects.isNull(realCustomerDao.findByNationalCode(realCustomer.getNationalCode()))) {
             realCustomerDao.save(realCustomer);

               logger.info("createdReal");
               return "اطلاعات ذخیره شد.";

           } else {

               logger.error("duplicatePerson");
               return new ResponseException("قبلا ثبت نام کرده اید");

         }

        } else {
            logger.error("NullFields");
              return new ResponseException("کد ملی یا اسم را وارد نکرده اید ");


        }


    }


    public Object searchLegal(String legalCode) {


        LegalCustomer byLegalCode = legalCustomerDao.findByLegalCode(legalCode);


        if (Objects.isNull(byLegalCode)) {

            logger.error("notFound");

            return  new ResponseException("پیدا نشد!");


        } else{

            LegalCustomerDto legalCustomerDto =  MapperClass.mapper(new LegalCustomerDto(), byLegalCode);

            logger.info("returnedLegalCustomerFound");

            return  legalCustomerDto ;
        }

    }


    public Object searchReal(String nationalCode) {


        RealCustomer byNationalCode = realCustomerDao.findByNationalCode(nationalCode);


        if (Objects.isNull(byNationalCode)) {

            logger.error("notFound");

            return new ResponseException("پیدا نشد!");

        } else{

            RealCustomerDto realCustomerDto = new RealCustomerDto();

            MapperClass.mapper(realCustomerDto, byNationalCode);

            logger.info("returnedRealCustomerFound");

            return realCustomerDto;
        }

    }


    public Object advanceLegalSearch(String name) {

        List<LegalCustomer> byName = legalCustomerDao.findByName(name.toUpperCase());
        List<LegalCustomerDto> byNameMapped = new ArrayList<>();

        for (LegalCustomer legalCustomer : byName){
            byNameMapped.add(MapperClass.mapper(new LegalCustomerDto() , legalCustomer));
        }

        if (byName.size() == 0)
            return new ResponseException("پیدا نشد!");


        return byNameMapped ;
    }


    public Object advanceRealSearch(String name) {

        List<RealCustomer> byName = realCustomerDao.findByName(name.toUpperCase());
        List<RealCustomerDto> byNameMapped = new ArrayList<>();

        for (RealCustomer realCustomer : byName){
            byNameMapped.add(MapperClass.mapper(new RealCustomerDto() , realCustomer));
        }

        if (byName.size() != 0)
            return  byNameMapped;

        return new ResponseException("پیدا نشد!");
    }

    @Transactional(rollbackOn = Exception.class)
    public String updateLegal(LegalCustomerDto legalCustomerDto) {




        legalCustomerDao.save( MapperClass.mapper(  new LegalCustomer() , legalCustomerDto));

        logger.info("updatedSuccessfully");

        return "با موفقیت ویرایش شد !";


    }

    @Transactional(rollbackOn = Exception.class)
    public String updateReal(RealCustomerDto realCustomerDto) {


        realCustomerDao.save(MapperClass.mapper ( new RealCustomer() , realCustomerDto )) ;

        logger.info("updatedSuccessfully");

        return "با موفقیت ویرایش شد !";

    }

    @Transactional(rollbackOn = Exception.class)
    public Object savingAccountForReal(String nationalCode) {

        RealCustomer foundByNationalCode = realCustomerDao.findByNationalCode( nationalCode);


        if (Objects.isNull(foundByNationalCode)) {

            logger.error("NotFound");
            return  new ResponseException("پیدا نشد!");

        } else {
            SavingAccount savingAccount = new SavingAccount();

            String currentTime=Long.toString(System.currentTimeMillis()) ;

            String accountNumber=currentTime.substring(currentTime.length()-3)+foundByNationalCode.getId();

            savingAccount.setAccountNumber(Integer.parseInt(accountNumber));

            foundByNationalCode.getSavingAccounts().add(savingAccount);

            savingAccountDao.save(savingAccount);

            realCustomerDao.save(foundByNationalCode);

            logger.info("newSavingAccountCreated");

            return" سپرده جدید با شماره حساب " + savingAccount.getAccountNumber() +"  برای شما ایجاد شد " ;

        }



    }

    @Transactional(rollbackOn = Exception.class)
    public Object savingAccountForLegal(String legalCode) {


        LegalCustomer foundByLegalCode = legalCustomerDao.findByLegalCode( legalCode);

        if (Objects.isNull(foundByLegalCode)) {

            logger.error("notFound");
            return new ResponseException("پیدا نشد!");


        } else {

            SavingAccount savingAccount = new SavingAccount();

            String currentTime=Long.toString(System.currentTimeMillis()) ;

            String accountNumber=currentTime.substring(currentTime.length()-3)+foundByLegalCode.getId();

            savingAccount.setAccountNumber(Integer.parseInt(accountNumber));

            foundByLegalCode.getSavingAccounts().add(savingAccount);

            savingAccountDao.save(savingAccount);

            legalCustomerDao.save(foundByLegalCode);

            logger.info("newSavingAccountCreated");


            return" سپرده جدید با شماره حساب " + savingAccount.getAccountNumber() +"  برای شما ایجاد شد ";

        }
    }

    public Object searchByAccountNumber(Integer accountNumber) {

        SavingAccount findByNum = savingAccountDao.findByAccountNumber(accountNumber);

        SavingAccountDto savingAccountDto = new SavingAccountDto();

        if (Objects.nonNull(findByNum))

            return  MapperClass.mapper(savingAccountDto, findByNum) ;

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


            transactionDao.save(depositTransaction);

            savingAccountDao.save(savingAccount);

            logger.info("depositSuccessfully");


            return  "واریز انجام شد ";
        }

        logger.error("accountDoesntExist");
        return new ResponseException("پیدا نشد ! ");




    }

    @Transactional(rollbackOn = Exception.class)
    public Object withdrawal(TransactionRequirementsDto transactionRequirementsDto) {

        SavingAccount savingAccount = savingAccountDao.findByAccountNumber(transactionRequirementsDto.getSavingAccountNumber());


        if (Objects.nonNull(savingAccount)) {

            if (savingAccount.getBalance().compareTo(transactionRequirementsDto.getAmount()) >= 0) {

                savingAccount.setBalance( savingAccount.getBalance().subtract(transactionRequirementsDto.getAmount()) );

                Transaction depositTransaction = new Transaction();

                depositTransaction.setAmount(transactionRequirementsDto.getAmount());

                depositTransaction.setTransactionType(TransactionType.WITHDRAWAL);

                savingAccount.getTransactions().add(depositTransaction);

                transactionDao.save(depositTransaction);



                if (savingAccount.getBalance().compareTo(savingAccount.getMinBalance()) < 0)

                    savingAccount.setMinBalance(savingAccount.getBalance());

                    savingAccountDao.save(savingAccount);

                logger.info("withdrawalSuccessfully");

                return "برداشت  وجه با موفقیت انجام شد ";

            } else
                logger.error("notEnoughBalance");
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

                sourceAccount.setBalance(sourceAccount.getBalance().subtract(transferMoneyDto.getAmount()));

                if (sourceAccount.getBalance().compareTo(sourceAccount.getMinBalance()) < 0)

                    sourceAccount.setMinBalance(sourceAccount.getBalance());

                targetAccount.setBalance(targetAccount.getBalance().add(transferMoneyDto.getAmount()));

                Transaction transferTransaction = new Transaction();

                Transaction depositTransaction = new Transaction();

                transferTransaction.setAmount(transferMoneyDto.getAmount());

                depositTransaction.setAmount(transferMoneyDto.getAmount());

                depositTransaction.setTransactionType(TransactionType.TRANSFER);

                transferTransaction.setTransactionType(TransactionType.TRANSFER);

                sourceAccount.getTransactions().add(transferTransaction);

                targetAccount.getTransactions().add(depositTransaction);

                transactionDao.save(depositTransaction);

                transactionDao.save(transferTransaction);

                savingAccountDao.save(sourceAccount);

                savingAccountDao.save(targetAccount);

                return "انتقال وجه با موفقیت انجام شد ";
            }
            return new ResponseException("موجودی کافی نیست ");

        }


        return new ResponseException( "کاربر پیدا نشد ");


    }
}

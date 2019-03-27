package com.rayanen.banking.service;

import com.rayanen.banking.dto.*;
import com.rayanen.banking.model.dao.LegalCustomerDao;
import com.rayanen.banking.model.dao.RealCustomerDao;
import com.rayanen.banking.model.dao.SavingAccountDao;
import com.rayanen.banking.model.entity.LegalCustomer;
import com.rayanen.banking.model.entity.RealCustomer;
import com.rayanen.banking.model.entity.SavingAccount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class BankingService {
    private LegalCustomerDao legalCustomerDao;
    private RealCustomerDao realCustomerDao;
    private SavingAccountDao savingAccountDao;

    public BankingService(LegalCustomerDao legalCustomerDao , RealCustomerDao realCustomerDao , SavingAccountDao savingAccountDao ){
        this.legalCustomerDao = legalCustomerDao;
        this.realCustomerDao = realCustomerDao;
       this. savingAccountDao = savingAccountDao;
    }

    public BankingService(){

    }


    public ResponseDto<String> saveLegalCustomer(LegalCustomer legalCustomer) {


        if (Objects.nonNull(legalCustomer.getId()) && Objects.nonNull(realCustomerDao.findById(legalCustomer.getId()))) {
            legalCustomerDao.save(legalCustomer);

            return new ResponseDto<>(ResponseStatus.Ok, null, "ویرایش با موفقیت انجام  شد !", null);


        }
        if (Objects.nonNull(legalCustomer.getLegalCode()) && Objects.nonNull(legalCustomer.getName())) {

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


    public ResponseDto<String> saveRealCustomer(RealCustomer realCustomer) {


        if (Objects.nonNull(realCustomer.getId()) && Objects.nonNull(realCustomerDao.findById(realCustomer.getId()))) {
            realCustomerDao.save(realCustomer);

            return new ResponseDto<>(ResponseStatus.Ok, null, "با موفقیت ویرایش شد !", null);


        }
        if (Objects.nonNull(realCustomer.getNationalCode()) && Objects.nonNull(realCustomer.getName())) {

            if (Objects.isNull(realCustomerDao.findByNationalCode(realCustomer.getNationalCode()))) {
                realCustomerDao.save(realCustomer);

                return new ResponseDto<>(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
            } else {

                return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("قبلا ثبت نام کرده اید"));

            }

        } else {
            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("کد ملی یا اسم را وارد نکرده اید "));


        }


    }


    public ResponseDto<CustomerDto> searchLegal(SearchDto searchDto) {


        LegalCustomer byLegalCode = legalCustomerDao.findByLegalCode((String) searchDto.getCode());

        if (Objects.isNull(byLegalCode)) {

            return new ResponseDto(ResponseStatus.Error, null, "", new ResponseException("پیدا نشد!"));


        } else
            return new ResponseDto(ResponseStatus.Ok, byLegalCode, "", null);
    }


    public ResponseDto<CustomerDto> searchReal(SearchDto searchDto) {


        RealCustomer byNationalCode = realCustomerDao.findByNationalCode((String) searchDto.getCode());

        if (Objects.isNull(byNationalCode)) {

            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("پیدا نشد!"));

        } else
            return new ResponseDto(ResponseStatus.Ok, byNationalCode, null, null);
    }


    public ResponseDto<List<LegalCustomer>> advanceLegalSearch(AdvanceSearchDto advanceSearchDto) {
        List<LegalCustomer> byName = legalCustomerDao.findByName(advanceSearchDto.getName().toUpperCase());

        if (byName.size() == 0)
            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("پیدا نشد!"));


        return new ResponseDto<>(ResponseStatus.Ok, byName, null, null);
    }


    public ResponseDto<List<RealCustomerDto>> advanceRealSearch(AdvanceSearchDto advanceSearchDto) {

        List<RealCustomer> byName = realCustomerDao.findByName(advanceSearchDto.getName().toUpperCase());

        if (byName.size() != 0)
            return new ResponseDto(ResponseStatus.Ok, byName, null, null);

        return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("پیدا نشد!"));
    }


    public ResponseDto<RealCustomerDto> updateLegal(SearchDto searchDto) {


        LegalCustomer byLegalCode = legalCustomerDao.findByLegalCode((String) searchDto.getCode());

        if (Objects.nonNull(byLegalCode)) {

            return new ResponseDto(ResponseStatus.Ok, byLegalCode, null, null);
        } else

            return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("موجود نیست !"));
    }


    public ResponseDto<RealCustomerDto> updateReal(SearchDto searchDto) {

        RealCustomer byReal = realCustomerDao.findByNationalCode((String) searchDto.getCode());

        if (Objects.nonNull(byReal)) {

            return new ResponseDto(ResponseStatus.Ok, byReal, null, new ResponseException("موجود نیست !"));
        } else

            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("موجود نیست !"));
    }


    public ResponseDto<RealCustomerDto> savingAccountForReal(RealCustomer realCustomer) {

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setAccountNumber(realCustomer.getId());
        realCustomer.getSavingAccounts().add(savingAccount);
        savingAccountDao.save(savingAccount);
        realCustomerDao.save(realCustomer);

        return new ResponseDto(ResponseStatus.Ok, null, "سپرده جدید برای شما ایجاد شد", null);


    }


    public ResponseDto<RealCustomerDto> savingAccountForLegal(LegalCustomer legalCustomer) {

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setAccountNumber(legalCustomer.getId());
        legalCustomer.getSavingAccounts().add(savingAccount);
        savingAccountDao.save(savingAccount);
        legalCustomerDao.save(legalCustomer);

        return new ResponseDto(ResponseStatus.Ok, null, "سپرده جدید برای شما ایجاد شد :)", null);


    }

    public ResponseDto<RealCustomerDto> searchByAccountNumber(SearchDto searchDto) {

        SavingAccount findByNum = savingAccountDao.findByAccountNumber((Integer) searchDto.getCode());

        if (Objects.nonNull(findByNum))

            return new ResponseDto(ResponseStatus.Ok, findByNum, null, null);

        return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("پیدا نشد"));

    }

    public ResponseDto<RealCustomerDto> deposit(SavingAccount savingAccount) {

        savingAccount.setBalance(savingAccount.getBalance().add(savingAccount.getAmount()));

        return new ResponseDto(ResponseStatus.Ok, null, "واریز انجام شد ", null);


    }


    public ResponseDto<RealCustomerDto> withdrawal(SavingAccount savingAccount) {

        if (savingAccount.getBalance().compareTo(savingAccount.getAmount()) >= 0) {
            savingAccount.getBalance().subtract(savingAccount.getAmount());
            if (savingAccount.getBalance().compareTo(savingAccount.getMinBalance()) < 0)
                savingAccount.setMinBalance(savingAccount.getBalance());
            return new ResponseDto(ResponseStatus.Ok, null, "برداشت  وجه با موفقیت انجام شد ", null);
        } else
            return new ResponseDto(ResponseStatus.Error, null, "موجودی کافی نیست ", null);

    }

    public ResponseDto<RealCustomerDto> transferMoney(TransferMoneyDto transferMoneyDto) {
        SavingAccount sourceAccount = savingAccountDao.findByAccountNumber(transferMoneyDto.getSourceAccount());
        SavingAccount targetAccount = savingAccountDao.findByAccountNumber(transferMoneyDto.getDestinationAccount());

        if (Objects.nonNull(sourceAccount) && Objects.nonNull(targetAccount)) {
            if (sourceAccount.getBalance().compareTo(sourceAccount.getAmount()) >= 0) {
                sourceAccount.getBalance().subtract(transferMoneyDto.getAmount());
                if (sourceAccount.getBalance().compareTo(sourceAccount.getMinBalance()) < 0)
                    sourceAccount.setMinBalance(sourceAccount.getBalance());
                targetAccount.setBalance(targetAccount.getBalance().add(transferMoneyDto.getAmount()));


                return new ResponseDto(ResponseStatus.Ok, null, "انتقال وجه با موفقیت انجام شد ", null);
            }
            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("موجودی کافی نیست "));

        }


        return new ResponseDto(ResponseStatus.Ok, null, "کاربر پیدا نشد ", null);


    }
}

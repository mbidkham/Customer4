package com.rayanen.banking.controller;


import com.rayanen.banking.dto.*;
import com.rayanen.banking.dto.ResponseStatus;
import com.rayanen.banking.facade.BankingAccountFacade;
import com.rayanen.banking.model.dao.LegalCustomerDao;
import com.rayanen.banking.model.dao.RealCustomerDao;
import com.rayanen.banking.model.dao.SavingAccountDao;
import com.rayanen.banking.model.entity.LegalCustomer;
import com.rayanen.banking.model.entity.RealCustomer;
import com.rayanen.banking.model.entity.SavingAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@RestController
public class CustomerController {

    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);


    @RequestMapping(value = "/ws/menu/getUserMenu", method = RequestMethod.POST)
    public ResponseDto<MenuItmDto> getUserMenu() {
        MenuItmDto menuItmDto = new MenuItmDto(null, null, null, new ArrayList<MenuItmDto>(Arrays.asList(
                new MenuItmDto(MenuItemType.MENU, "کاربر جدید :", null, new ArrayList<MenuItmDto>(Arrays.asList(
                        new MenuItmDto(MenuItemType.PAGE, "ثبت کاربر حقیقی", new UIPageDto(null, "real.xml"), new ArrayList<MenuItmDto>()),
                        new MenuItmDto(MenuItemType.PAGE, "ثبت کاربر حقوقی", new UIPageDto(null, "legal.xml"), new ArrayList<MenuItmDto>())))),
                new MenuItmDto(MenuItemType.MENU, "جستجو  :", null, new ArrayList<MenuItmDto>(Arrays.asList(
                        new MenuItmDto(MenuItemType.PAGE, "جستجو کاربر حقیقی ", new UIPageDto(null, "searchReal.xml"), new ArrayList<MenuItmDto>()),
                        new MenuItmDto(MenuItemType.PAGE, "جستجوی پیشرفته(حقیقی) ", new UIPageDto(null, "advanceRealSearch.xml"), new ArrayList<MenuItmDto>()),
                        new MenuItmDto(MenuItemType.PAGE, "جستجو کاربر حقوقی ", new UIPageDto(null, "searchLegal.xml"), new ArrayList<MenuItmDto>()),
                        new MenuItmDto(MenuItemType.PAGE, "جستجوی پیشرفته(حقوقی) ", new UIPageDto(null, "advanceLegalSearch.xml"), new ArrayList<MenuItmDto>())
                ))),
                new MenuItmDto(MenuItemType.PAGE, " ویرایش اطلاعات(مشتریان حقیفی) ", new UIPageDto(null, "updateReal.xml"), null),
                new MenuItmDto(MenuItemType.PAGE, " ویرایش اطلاعات(مشتریان حقوقی) ", new UIPageDto(null, "updateLegal.xml"), null),
                new MenuItmDto(MenuItemType.MENU, "ایجاد سپرده  :", null, new ArrayList<MenuItmDto>(Arrays.asList(
                        new MenuItmDto(MenuItemType.PAGE, "سپرده کاربران حقیقی ", new UIPageDto(null, "savingAccountForReal.xml"), new ArrayList<MenuItmDto>()),
                        new MenuItmDto(MenuItemType.PAGE, "سپرده کاربران حقوقی ", new UIPageDto(null, "savingAccountForLegal.xml"), new ArrayList<MenuItmDto>())))),
                new MenuItmDto(MenuItemType.MENU, "خدمات  :", null, new ArrayList<MenuItmDto>(Arrays.asList(


                        new MenuItmDto(MenuItemType.PAGE, "برداشت  ", new UIPageDto(null, "withdrawal.xml"), new ArrayList<MenuItmDto>()),
                        new MenuItmDto(MenuItemType.PAGE, "واریز ", new UIPageDto(null, "deposit.xml"), new ArrayList<MenuItmDto>()),
                        new MenuItmDto(MenuItemType.PAGE, "انتفال وجه  ", new UIPageDto(null, "transferMoney.xml"), new ArrayList<MenuItmDto>()),
                        new MenuItmDto(MenuItemType.PAGE, "موجودی  ", new UIPageDto(null, "showBalance.xml"), new ArrayList<MenuItmDto>())

                ))

                ))

        ));

        return new ResponseDto(ResponseStatus.Ok, menuItmDto, null, null);
    }


    @RequestMapping(value = "/ws/uipage/getPage", method = RequestMethod.POST)
    public ResponseDto<String> getPage(@RequestParam String name) throws IOException {
        return new ResponseDto(ResponseStatus.Ok, readFile(name, StandardCharsets.UTF_8), null, null);
    }


    @RequestMapping(value = "/ws/saveLegalCustomer", method = RequestMethod.POST)
    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> saveLegalCustomer(@Valid @RequestBody LegalCustomerDto legalCustomerDto) {

        BankingAccountFacade bankingAccountFacade =  new BankingAccountFacade();
        bankingAccountFacade.saveNewLegalCustomer(legalCustomerDto);
        return new ResponseDto<>(ResponseStatus.Ok, null, "با موفقیت ذخیره شد !", null);



    }


    @RequestMapping(value = "/ws/saveRealCustomer", method = RequestMethod.POST)
    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> saveRealCustomer(@Valid @RequestBody RealCustomerDto realCustomerDto) {


        BankingAccountFacade bankingAccountFacade =  new BankingAccountFacade();
        bankingAccountFacade.saveNewRealCustomer(realCustomerDto);
        return new ResponseDto<>(ResponseStatus.Ok, null, "با موفقیت ذخیره شد !", null);




    }


    @RequestMapping(value = "/ws/searchLegal", method = RequestMethod.POST)
    public ResponseDto<CustomerDto> searchLegal(@Valid @RequestParam SearchDto searchDto) {

        BankingAccountFacade bankingAccountFacade =  new BankingAccountFacade();
        bankingAccountFacade.searchLegal(searchDto);
        return new ResponseDto<>(ResponseStatus.Ok, null, "با موفقیت ذخیره شد !", null);


    }


    @RequestMapping(value = "/ws/searchReal", method = RequestMethod.POST)
    public ResponseDto<CustomerDto> searchReal(@RequestParam SearchDto searchDto) {



        BankingAccountFacade bankingAccountFacade =  new BankingAccountFacade();
        bankingAccountFacade.searchReal(searchDto);
        return new ResponseDto<>(ResponseStatus.Ok, null, "با موفقیت ذخیره شد !", null);

    }

    @RequestMapping(value = "/ws/advanceLegalSearch", method = RequestMethod.POST)
    public ResponseDto<List<LegalCustomer>> advanceLegalSearch(@RequestParam AdvanceSearchDto advanceSearchDto) {

        BankingAccountFacade bankingAccountFacade=new BankingAccountFacade();
        bankingAccountFacade.advanceLegalSearch(advanceSearchDto);
        return new ResponseDto<>(ResponseStatus.Ok, null, null, null);
    }

    @RequestMapping(value = "/ws/advanceRealSearch", method = RequestMethod.POST)
    public ResponseDto<List<RealCustomerDto>> advanceRealSearch(@RequestParam AdvanceSearchDto advanceSearchDto) {


        BankingAccountFacade bankingAccountFacade=new BankingAccountFacade();
        bankingAccountFacade.advanceRealSearch(advanceSearchDto);
        return new ResponseDto<>(ResponseStatus.Ok, null, null, null);


    }

    @RequestMapping(value = "/ws/updateLegal", method = RequestMethod.POST)
    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<RealCustomerDto> updateLegal(@RequestParam SearchDto searchDto) {

        BankingAccountFacade bankingAccountFacade = new BankingAccountFacade();
        bankingAccountFacade.updateLegal(searchDto);


            return new ResponseDto(ResponseStatus.Ok, null, null, null);



    }

    @RequestMapping(value = "/ws/updateReal", method = RequestMethod.POST)
    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<RealCustomerDto> updateReal(@RequestParam SearchDto searchDto) {


        BankingAccountFacade bankingAccountFacade = new BankingAccountFacade();
        bankingAccountFacade.updateReal(searchDto);



        return new ResponseDto(ResponseStatus.Ok, null, null, new ResponseException("موجود نیست !"));
    }


    @RequestMapping(value = "/ws/savingAccountForReal", method = RequestMethod.POST)

    public ResponseDto<RealCustomerDto> savingAccountForReal(@Valid @RequestBody RealCustomerDto realCustomerDto) {


        BankingAccountFacade bankingAccountFacade = new BankingAccountFacade();
        bankingAccountFacade.savingAccountForReal(realCustomerDto);
        return new ResponseDto(ResponseStatus.Ok, null, "سپرده جدید برای شما ایجاد شد", null);


    }

    @RequestMapping(value = "/ws/savingAccountForLegal", method = RequestMethod.POST)

    public ResponseDto<RealCustomerDto> savingAccountForLegal(@Valid @RequestBody LegalCustomerDto legalCustomerDto) {


        BankingAccountFacade bankingAccountFacade = new BankingAccountFacade();
        bankingAccountFacade.savingAccountForLegal(legalCustomerDto);
        return new ResponseDto(ResponseStatus.Ok, null, "سپرده جدید برای شما ایجاد شد :)", null);


    }

    @RequestMapping(value = "/ws/searchByAccountNumber", method = RequestMethod.POST)
    public ResponseDto<RealCustomerDto> searchByAccountNumber(@RequestParam SearchDto searchDto) {

        BankingAccountFacade bankingAccountFacade = new BankingAccountFacade();
        bankingAccountFacade.searchByAccountNumber(searchDto);

        return new ResponseDto(ResponseStatus.Ok, null, "واریز انجام شد ", null);
    }

    @RequestMapping(value = "/ws/deposit", method = RequestMethod.POST)
    @Transactional
    public ResponseDto<RealCustomerDto> deposit(@Valid @RequestBody SavingAccountDto savingAccountDto) {

        BankingAccountFacade bankingAccountFacade = new BankingAccountFacade();
        bankingAccountFacade.deposit(savingAccountDto);

        return new ResponseDto(ResponseStatus.Ok, null, "واریز انجام شد ", null);


    }

    @RequestMapping(value = "/ws/withdrawal", method = RequestMethod.POST)
    @Transactional
    public ResponseDto<RealCustomerDto> withdrawal(@Valid @RequestBody SavingAccountDto savingAccountDto) {


        BankingAccountFacade bankingAccountFacade = new BankingAccountFacade();
        bankingAccountFacade.withdrawal(savingAccountDto);
        return new ResponseDto(ResponseStatus.Ok, null, "برداشت  وجه با موفقیت انجام شد ", null);

    }

    @RequestMapping(value = "/ws/transferMoney", method = RequestMethod.POST)
    @Transactional
    public ResponseDto<RealCustomerDto> transferMoney(@RequestBody TransferMoneyDto transferMoneyDto) {

        BankingAccountFacade bankingAccountFacade = new BankingAccountFacade();
        bankingAccountFacade.transferMoney(transferMoneyDto);

        return new ResponseDto(ResponseStatus.Ok, null, "کاربر پیدا نشد ", null);


    }

    String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(new ClassPathResource(path).getFile().getPath()));
        return new String(encoded, encoding);
    }
}

package com.rayanen.banking.controller;


import com.rayanen.banking.dto.*;
import com.rayanen.banking.dto.ResponseStatus;
import com.rayanen.banking.facade.BankingAccountFacade;

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

    private BankingAccountFacade bankingAccountFacade;

    public CustomerController( BankingAccountFacade bankingAccountFacade){

        this.bankingAccountFacade = bankingAccountFacade;

    }



    @RequestMapping(value = "/ws/menu/getUserMenu", method = RequestMethod.POST)
    public ResponseDto<MenuItmDto> getUserMenu() {
        MenuItmDto menuItmDto = new MenuItmDto(null, null, null, new ArrayList<>(Arrays.asList(
                new MenuItmDto(MenuItemType.MENU, "کاربر جدید :", null, new ArrayList<>(Arrays.asList(
                        new MenuItmDto(MenuItemType.PAGE, "ثبت کاربر حقیقی", new UIPageDto(null, "real.xml"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, "ثبت کاربر حقوقی", new UIPageDto(null, "legal.xml"), new ArrayList<>())))),
                new MenuItmDto(MenuItemType.MENU, "جستجو  :", null, new ArrayList<>(Arrays.asList(
                        new MenuItmDto(MenuItemType.PAGE, "جستجو کاربر حقیقی ", new UIPageDto(null, "searchReal.xml"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, "جستجوی پیشرفته(حقیقی) ", new UIPageDto(null, "advanceRealSearch.xml"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, "جستجو کاربر حقوقی ", new UIPageDto(null, "searchLegal.xml"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, "جستجوی پیشرفته(حقوقی) ", new UIPageDto(null, "advanceLegalSearch.xml"), new ArrayList<>())
                ))),
                new MenuItmDto(MenuItemType.PAGE, " ویرایش اطلاعات(مشتریان حقیفی) ", new UIPageDto(null, "updateReal.xml"), null),
                new MenuItmDto(MenuItemType.PAGE, " ویرایش اطلاعات(مشتریان حقوقی) ", new UIPageDto(null, "updateLegal.xml"), null),
                new MenuItmDto(MenuItemType.MENU, "ایجاد سپرده  :", null, new ArrayList<>(Arrays.asList(
                        new MenuItmDto(MenuItemType.PAGE, "سپرده کاربران حقیقی ", new UIPageDto(null, "savingAccountForReal.xml"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, "سپرده کاربران حقوقی ", new UIPageDto(null, "savingAccountForLegal.xml"), new ArrayList<>())))),
                new MenuItmDto(MenuItemType.MENU, "خدمات  :", null, new ArrayList<>(Arrays.asList(


                        new MenuItmDto(MenuItemType.PAGE, "برداشت  ", new UIPageDto(null, "withdrawal.xml"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, "واریز ", new UIPageDto(null, "deposit.xml"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, "انتفال وجه  ", new UIPageDto(null, "transferMoney.xml"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, "موجودی  ", new UIPageDto(null, "showBalance.xml"), new ArrayList<>())

                ))

                ))

        ));

        return new ResponseDto<>(ResponseStatus.Ok, menuItmDto, null, null);
    }


    @RequestMapping(value = "/ws/uipage/getPage", method = RequestMethod.POST)
    public ResponseDto<String> getPage(@RequestParam String name) throws IOException {
        return new ResponseDto<>(ResponseStatus.Ok, readFile(name, StandardCharsets.UTF_8), null, null);
    }


    @RequestMapping(value = "/ws/saveLegalCustomer", method = RequestMethod.POST)
    public ResponseDto<String> saveLegalCustomer(@Valid @RequestBody LegalCustomerDto legalCustomerDto) {


            Object returnObject = bankingAccountFacade.saveNewLegalCustomer(legalCustomerDto);

            if(returnObject instanceof ResponseException)
                return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException)returnObject);

            else
                return new ResponseDto<>(ResponseStatus.Ok, null, (String)returnObject, null);



    }


    @RequestMapping(value = "/ws/saveRealCustomer", method = RequestMethod.POST)
    public ResponseDto saveRealCustomer(@Valid @RequestBody RealCustomerDto realCustomerDto) {

        Object returnObj = bankingAccountFacade.saveNewRealCustomer(realCustomerDto);


        if(returnObj instanceof ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        else
            return new ResponseDto<>(ResponseStatus.Ok, null, (String) returnObj, null);


    }


    @RequestMapping(value = "/ws/searchLegal", method = RequestMethod.POST)
    public ResponseDto<CustomerDto> searchLegal(@RequestBody SearchDto  searchDto) {

       Object returnObj =  bankingAccountFacade.searchLegal(searchDto);

       if(returnObj instanceof  ResponseException)
           return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto<>(ResponseStatus.Ok, (CustomerDto) returnObj, null, null);


    }


    @RequestMapping(value = "/ws/searchReal", method = RequestMethod.POST)
    public ResponseDto searchReal(@RequestBody SearchDto searchDto) {

        Object returnObj =  bankingAccountFacade.searchReal(searchDto);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto<>(ResponseStatus.Ok, (CustomerDto) returnObj, null, null);



    }

    @RequestMapping(value = "/ws/advanceLegalSearch", method = RequestMethod.POST)
    public ResponseDto advanceLegalSearch(@RequestBody AdvanceSearchDto advanceSearchDto) {

        List returnObj =(List) bankingAccountFacade.advanceLegalSearch(advanceSearchDto);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto<>(ResponseStatus.Ok,  returnObj, null, null);


    }

    @RequestMapping(value = "/ws/advanceRealSearch", method = RequestMethod.POST)
    public ResponseDto advanceRealSearch(@RequestBody AdvanceSearchDto advanceSearchDto) {

        List returnObj = (List)bankingAccountFacade.advanceRealSearch(advanceSearchDto);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto<>(ResponseStatus.Ok,  returnObj, null, null);





    }

    @RequestMapping(value = "/ws/updateLegal", method = RequestMethod.POST)
    public ResponseDto<String> updateLegal(@RequestBody LegalCustomerDto legalCustomerDto) {


        String returnObj = bankingAccountFacade.updateLegal(legalCustomerDto);

        return new ResponseDto<>(ResponseStatus.Ok, null, returnObj, null);




    }

    @RequestMapping(value = "/ws/updateReal", method = RequestMethod.POST)
    public ResponseDto<String> updateReal(@RequestBody RealCustomerDto realCustomerDto) {

        String returnObj =  bankingAccountFacade.updateReal(realCustomerDto);

        return new ResponseDto<>(ResponseStatus.Ok, null, returnObj, null);


    }


    @RequestMapping(value = "/ws/savingAccountForReal", method = RequestMethod.POST)
    public ResponseDto savingAccountForReal(@RequestBody SearchDto searchDto) {

        Object returnObj = bankingAccountFacade.savingAccountForReal(searchDto);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto<>(ResponseStatus.Ok, null,  (String)returnObj, null);



    }

    @RequestMapping(value = "/ws/savingAccountForLegal", method = RequestMethod.POST)
    public ResponseDto<RealCustomerDto> savingAccountForLegal( @RequestBody SearchDto searchDto) {


        Object returnObj = bankingAccountFacade.savingAccountForLegal(searchDto);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto<>(ResponseStatus.Ok,null , (String)returnObj, null);




    }

    @RequestMapping(value = "/ws/searchByAccountNumber", method = RequestMethod.POST)
    public ResponseDto<CustomerDto> searchByAccountNumber(@RequestBody SearchDto searchDto) {

        Object returnObj = bankingAccountFacade.searchByAccountNumber(searchDto);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto<>(ResponseStatus.Ok, (CustomerDto) returnObj, null, null);


    }

    @RequestMapping(value = "/ws/deposit", method = RequestMethod.POST)
    public ResponseDto<RealCustomerDto> deposit(@RequestBody TransactionRequirementsDto transactionRequirementsDto) {

        Object returnObj = bankingAccountFacade.deposit(transactionRequirementsDto);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto<>(ResponseStatus.Ok, null, (String)returnObj, null);


    }

    @RequestMapping(value = "/ws/withdrawal", method = RequestMethod.POST)
    public ResponseDto<RealCustomerDto> withdrawal( @RequestBody TransactionRequirementsDto transactionRequirementsDto) {

        Object returnObj = bankingAccountFacade.withdrawal(transactionRequirementsDto);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto<>(ResponseStatus.Ok, null, (String)returnObj, null);



    }

    @RequestMapping(value = "/ws/transferMoney", method = RequestMethod.POST)
    public ResponseDto<RealCustomerDto> transferMoney(@RequestBody TransferMoneyDto transferMoneyDto) {

        Object returnObj = bankingAccountFacade.transferMoney(transferMoneyDto);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto<>(ResponseStatus.Ok, null, (String)returnObj, null);



    }

    String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(new ClassPathResource(path).getFile().getPath()));
        return new String(encoded, encoding);
    }
}

package com.rayanen.banking.controller;


import com.rayanen.banking.dto.*;
import com.rayanen.banking.dto.ResponseStatus;
import com.rayanen.banking.facade.BankingAccountFacade;
import com.rayanen.banking.facade.facadeImpl.BankingAccountFacadeImpl;
import com.rayanen.banking.model.entity.LegalCustomer;
import com.rayanen.banking.model.entity.RealCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.transaction.TransactionRequiredException;
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
    public ResponseDto<String> saveLegalCustomer( @RequestBody LegalCustomerDto legalCustomerDto) {

        return bankingAccountFacade.saveNewLegalCustomer(legalCustomerDto);



    }


    @RequestMapping(value = "/ws/saveRealCustomer", method = RequestMethod.POST)
    @Transactional(rollbackOn = Exception.class)
    public ResponseDto saveRealCustomer( @RequestBody RealCustomerDto realCustomerDto) {


        return bankingAccountFacade.saveNewRealCustomer(realCustomerDto);


    }


    @RequestMapping(value = "/ws/searchLegal", method = RequestMethod.POST)
    public ResponseDto<CustomerDto> searchLegal(@RequestBody SearchDto  searchDto) {

       return bankingAccountFacade.searchLegal(searchDto);

    }


    @RequestMapping(value = "/ws/searchReal", method = RequestMethod.POST)
    public ResponseDto searchReal(@RequestBody SearchDto searchDto) {

        return bankingAccountFacade.searchReal(searchDto);


    }

    @RequestMapping(value = "/ws/advanceLegalSearch", method = RequestMethod.POST)
    public ResponseDto advanceLegalSearch(@RequestBody AdvanceSearchDto advanceSearchDto) {

        return bankingAccountFacade.advanceLegalSearch(advanceSearchDto);
    }

    @RequestMapping(value = "/ws/advanceRealSearch", method = RequestMethod.POST)
    public ResponseDto advanceRealSearch(@RequestBody AdvanceSearchDto advanceSearchDto) {

       return bankingAccountFacade.advanceRealSearch(advanceSearchDto);



    }

    @RequestMapping(value = "/ws/updateLegal", method = RequestMethod.POST)
    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<RealCustomerDto> updateLegal(@RequestBody LegalCustomerDto legalCustomerDto) {


       return bankingAccountFacade.updateLegal(legalCustomerDto);


    }

    @RequestMapping(value = "/ws/updateReal", method = RequestMethod.POST)
    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<RealCustomerDto> updateReal(@RequestBody RealCustomerDto realCustomerDto) {

        return bankingAccountFacade.updateReal(realCustomerDto);
    }


    @RequestMapping(value = "/ws/savingAccountForReal", method = RequestMethod.POST)

    public ResponseDto savingAccountForReal(@RequestBody SearchDto searchDto) {

        return bankingAccountFacade.savingAccountForReal(searchDto);

    }

    @RequestMapping(value = "/ws/savingAccountForLegal", method = RequestMethod.POST)

    public ResponseDto<RealCustomerDto> savingAccountForLegal( @RequestBody SearchDto searchDto) {


        return bankingAccountFacade.savingAccountForLegal(searchDto);


    }

    @RequestMapping(value = "/ws/searchByAccountNumber", method = RequestMethod.POST)
    public ResponseDto<RealCustomerDto> searchByAccountNumber(@RequestBody SearchDto searchDto) {

        return bankingAccountFacade.searchByAccountNumber(searchDto);
    }

    @RequestMapping(value = "/ws/deposit", method = RequestMethod.POST)
    @Transactional
    public ResponseDto<RealCustomerDto> deposit(@RequestBody TransactionRequirementsDto transactionRequirementsDto) {

        return bankingAccountFacade.deposit(transactionRequirementsDto);

    }

    @RequestMapping(value = "/ws/withdrawal", method = RequestMethod.POST)
    @Transactional
    public ResponseDto<RealCustomerDto> withdrawal( @RequestBody TransactionRequirementsDto transactionRequirementsDto) {

       return bankingAccountFacade.withdrawal(transactionRequirementsDto);

    }

    @RequestMapping(value = "/ws/transferMoney", method = RequestMethod.POST)
    @Transactional
    public ResponseDto<RealCustomerDto> transferMoney(@RequestBody TransferMoneyDto transferMoneyDto) {

       return bankingAccountFacade.transferMoney(transferMoneyDto);

    }

    String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(new ClassPathResource(path).getFile().getPath()));
        return new String(encoded, encoding);
    }
}

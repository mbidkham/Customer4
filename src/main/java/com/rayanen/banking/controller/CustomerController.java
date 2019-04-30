package com.rayanen.banking.controller;


import com.rayanen.banking.dto.*;
import com.rayanen.banking.dto.ResponseStatus;
import com.rayanen.banking.facade.BankingAccountFacade;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;



//    @RequestMapping(value = "/ws/menu/getUserMenu", method = RequestMethod.POST)
//    public ResponseDto<MenuItmDto> getUserMenu() {
//        MenuItmDto menuItmDto = new MenuItmDto(null, null, null, new ArrayList<>(Arrays.asList(
//                new MenuItmDto(MenuItemType.MENU, "کاربر جدید :", null, new ArrayList<>(Arrays.asList(
//                        new MenuItmDto(MenuItemType.PAGE, "ثبت کاربر حقیقی", new UIPageDto(null, "real.xml"), new ArrayList<>()),
//                        new MenuItmDto(MenuItemType.PAGE, "ثبت کاربر حقوقی", new UIPageDto(null, "legal.xml"), new ArrayList<>())))),
//                new MenuItmDto(MenuItemType.MENU, "جستجو  :", null, new ArrayList<>(Arrays.asList(
//                        new MenuItmDto(MenuItemType.PAGE, "جستجو کاربر حقیقی ", new UIPageDto(null, "searchReal.xml"), new ArrayList<>()),
//                        new MenuItmDto(MenuItemType.PAGE, "جستجوی پیشرفته(حقیقی) ", new UIPageDto(null, "advanceRealSearch.xml"), new ArrayList<>()),
//                        new MenuItmDto(MenuItemType.PAGE, "جستجو کاربر حقوقی ", new UIPageDto(null, "searchLegal.xml"), new ArrayList<>()),
//                        new MenuItmDto(MenuItemType.PAGE, "جستجوی پیشرفته(حقوقی) ", new UIPageDto(null, "advanceLegalSearch.xml"), new ArrayList<>())
//                ))),
//                new MenuItmDto(MenuItemType.PAGE, " ویرایش اطلاعات(مشتریان حقیفی) ", new UIPageDto(null, "updateReal.xml"), null),
//                new MenuItmDto(MenuItemType.PAGE, " ویرایش اطلاعات(مشتریان حقوقی) ", new UIPageDto(null, "updateLegal.xml"), null),
//                new MenuItmDto(MenuItemType.MENU, "ایجاد سپرده  :", null, new ArrayList<>(Arrays.asList(
//                        new MenuItmDto(MenuItemType.PAGE, "سپرده کاربران حقیقی ", new UIPageDto(null, "savingAccountForReal.xml"), new ArrayList<>()),
//                        new MenuItmDto(MenuItemType.PAGE, "سپرده کاربران حقوقی ", new UIPageDto(null, "savingAccountForLegal.xml"), new ArrayList<>())))),
//                new MenuItmDto(MenuItemType.MENU, "خدمات  :", null, new ArrayList<>(Arrays.asList(
//
//
//                        new MenuItmDto(MenuItemType.PAGE, "برداشت  ", new UIPageDto(null, "withdrawal.xml"), new ArrayList<>()),
//                        new MenuItmDto(MenuItemType.PAGE, "واریز ", new UIPageDto(null, "deposit.xml"), new ArrayList<>()),
//                        new MenuItmDto(MenuItemType.PAGE, "انتفال وجه  ", new UIPageDto(null, "transferMoney.xml"), new ArrayList<>()),
//                        new MenuItmDto(MenuItemType.PAGE, "موجودی  ", new UIPageDto(null, "showBalance.xml"), new ArrayList<>())
//
//                ))
//
//                ))
//
//        ));

//        return new ResponseDto<>(ResponseStatus.Ok, menuItmDto, null, null);
//    }


    @RequestMapping(value = "/pws/uipage/getPage", method = RequestMethod.POST)
    public ResponseDto<String> getPage(@RequestParam String name) throws IOException {
        return new ResponseDto(ResponseStatus.Ok, readFile(name, StandardCharsets.UTF_8), null, null);
    }


    @RequestMapping(value = "/ws/login", method = RequestMethod.GET)
    public ResponseDto<AfterLoginInfoDto> loginSuccess() {


        AfterLoginInfoDto afterLoginInfoDto = new AfterLoginInfoDto();


        MenuItmDto menuItmDto = new MenuItmDto(null, null, null, new ArrayList<MenuItmDto>(Arrays.asList(
                new MenuItmDto(MenuItemType.MENU, "اکتیویتی", null, new ArrayList<MenuItmDto>(Arrays.asList(
                        new MenuItmDto(MenuItemType.PAGE, "کارتابل", new UIPageDto(null, "showTasks"), new ArrayList<MenuItmDto>()))))
        )));
        afterLoginInfoDto.setMenu(menuItmDto);
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CREDITS_OPERATOR"))) {


            menuItmDto.getChildren().get(0).getChildren().add(new MenuItmDto(MenuItemType.PAGE, "ثبت درخواست تسهیلات", new UIPageDto(null, "startTask"), new ArrayList<MenuItmDto>()));

            afterLoginInfoDto.setMenu(menuItmDto);

        }

        else if(principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_BRANCH_MANAGER"))){






        }
        else if(principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CASH_DESK"))){

            MenuItmDto menuItmDtoForCash = new MenuItmDto(null, null, null, new ArrayList<>(Arrays.asList(
                new MenuItmDto(MenuItemType.MENU, "کاربر جدید :", null, new ArrayList<>(Arrays.asList(
                        new MenuItmDto(MenuItemType.PAGE, "ثبت کاربر حقیقی", new UIPageDto(null, "real"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, "ثبت کاربر حقوقی", new UIPageDto(null, "legal"), new ArrayList<>())))),
                new MenuItmDto(MenuItemType.MENU, "جستجو  :", null, new ArrayList<>(Arrays.asList(
                        new MenuItmDto(MenuItemType.PAGE, "جستجو کاربر حقیقی ", new UIPageDto(null, "searchReal"), new ArrayList<>()),
                       new MenuItmDto(MenuItemType.PAGE, "جستجوی پیشرفته(حقیقی) ", new UIPageDto(null, "advanceRealSearch"), new ArrayList<>()),
                       new MenuItmDto(MenuItemType.PAGE, "جستجو کاربر حقوقی ", new UIPageDto(null, "searchLegal"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, "جستجوی پیشرفته(حقوقی) ", new UIPageDto(null, "advanceLegalSearch"), new ArrayList<>())
                ))),
                new MenuItmDto(MenuItemType.PAGE, " ویرایش اطلاعات(مشتریان حقیفی) ", new UIPageDto(null, "updateReal"), null),
              new MenuItmDto(MenuItemType.PAGE, " ویرایش اطلاعات(مشتریان حقوقی) ", new UIPageDto(null, "updateLegal"), null),
              new MenuItmDto(MenuItemType.MENU, "ایجاد سپرده  :", null, new ArrayList<>(Arrays.asList(
                       new MenuItmDto(MenuItemType.PAGE, "سپرده کاربران حقیقی ", new UIPageDto(null, "savingAccountForReal"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, "سپرده کاربران حقوقی ", new UIPageDto(null, "savingAccountForLegal"), new ArrayList<>())))),

               new MenuItmDto(MenuItemType.MENU, "خدمات  :", null, new ArrayList<>(Arrays.asList(

                      new MenuItmDto(MenuItemType.PAGE, "برداشت  ", new UIPageDto(null, "withdrawal"), new ArrayList<>()),
                      new MenuItmDto(MenuItemType.PAGE, "واریز ", new UIPageDto(null, "deposit"), new ArrayList<>()),
                       new MenuItmDto(MenuItemType.PAGE, "انتفال وجه  ", new UIPageDto(null, "transferMoney"), new ArrayList<>())

            ))

             ),
                    new MenuItmDto(MenuItemType.PAGE, "کارتابل", new UIPageDto(null, "showTasks"), new ArrayList<MenuItmDto>()))

      ));


            afterLoginInfoDto.setMenu(menuItmDtoForCash);


        }

        return new ResponseDto(ResponseStatus.Ok, afterLoginInfoDto, null, null);
    }

    @RequestMapping(value = "/pws/error", method = RequestMethod.GET)
    public ResponseDto error(@RequestParam String code) {
        switch (code) {
            case "loginFailure":
                return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("نام کاربری یا کلمه عبور درست وارد نشده"));
            default:
                return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("خطای سیستمی رخ داده است"));
        }
    }



    @RequestMapping(value = "/ws/startProcess", method = RequestMethod.POST)
    public ResponseDto startProcess(@RequestBody TaskInputDto taskInputDto) {
        Map<String,Object> map = new HashMap<>();
        map.put("customerNumber",taskInputDto.getCustomerNumber());
        runtimeService.startProcessInstanceByKey("FacilitiesService", map);
        return new ResponseDto(ResponseStatus.Ok, null, "فرایند آغاز شد.", null);

    }


    @RequestMapping(value = "/ws/getTasks", method = RequestMethod.POST)
    public ResponseDto<List<TaskDto>> getTasks() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(getUsername()).list();
        List<TaskDto> taskDtos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TaskDto taskDto = new TaskDto();
            taskDto.setTaskId(list.get(i).getId());
            taskDto.setName(list.get(i).getName());
            taskDto.setFormKey(list.get(i).getFormKey());
            taskDtos.add(taskDto);
        }
        return new ResponseDto(ResponseStatus.Ok, taskDtos, null, null);
    }

    @RequestMapping(value = "/ws/getUrlByFormKey", method = RequestMethod.POST)
    public ResponseDto<String> getUrlByFormKey(@RequestParam String formKey) {

        String url = "";

        switch (formKey){
            case "BranchManagerForm":
                url = "managerApproveTask";
                break;
            case "CashDeskForm":
                url = "cashApproveTask";
                break;
        }
        return new ResponseDto(ResponseStatus.Ok, url, null, null);
    }

    @RequestMapping(value = "/ws/managerApproveTask", method = RequestMethod.POST)
    public ResponseDto managerApproveTask(@RequestBody TaskDto taskDto) {
        runtimeService.getVariables(taskDto.getTaskId()).put("accepted",true);
        taskService.getVariables(taskDto.getTaskId()).put("accepted",true);
        // Map<String,Object> map = new HashMap<>();
        //   map.put("sanad",obj);
        // taskService.complete(taskId, map);
        taskService.complete(taskDto.getTaskId());
        //taskService.setVariable(taskId,"sanad", obj);
        return new ResponseDto<>(ResponseStatus.Ok, null, "تأیید شد.", null);
    }

    @RequestMapping (value = "ws/managerRejectTask" , method = RequestMethod.POST)
    public ResponseDto managerRejectTask (@RequestBody TaskDto taskDto){

        runtimeService.getVariables(taskDto.getTaskId()).put("accepted",false);
        taskService.getVariables(taskDto.getTaskId()).put("accepted",false);
        return new ResponseDto<>(ResponseStatus.Ok, null, "درخواست تسهیلات توسط مدیر شعبه رد شد.", null);
    }

    @RequestMapping(value = "/ws/cashApproveTask", method = RequestMethod.POST)
    public ResponseDto cashApproveTask(@RequestBody TaskDto taskDto) {
        // Map<String,Object> map = new HashMap<>();
        //   map.put("sanad",obj);
        // taskService.complete(taskId, map);
        taskService.complete(taskDto.getTaskId());
        //taskService.setVariable(taskId,"sanad", obj);
        return new ResponseDto(ResponseStatus.Ok, null, "تأیید شد.", null);
    }

    @RequestMapping(value = "/ws/getTaskByTaskId", method = RequestMethod.POST)
    public ResponseDto<TaskInputDto> getTaskByTaskId(@RequestParam String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        TaskInputDto taskInputDto = new TaskInputDto();
        taskInputDto.setTaskId(task.getId());
        Map<String, Object> taskLocalVariables = runtimeService.getVariables(task.getProcessInstanceId());
        taskInputDto.setCustomerNumber(taskLocalVariables.get("customerNumber").toString());
        return new ResponseDto(ResponseStatus.Ok, taskInputDto, null, null);
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
    public ResponseDto<CustomerDto> searchLegal(@RequestParam String  legalCode) {

       Object returnObj =  bankingAccountFacade.searchLegal(legalCode);

       if(returnObj instanceof  ResponseException)
           return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto<>(ResponseStatus.Ok, (CustomerDto) returnObj, null, null);


    }


    @RequestMapping(value = "/ws/searchReal", method = RequestMethod.POST)
    public ResponseDto searchReal(@RequestParam String nationalCode) {

        Object returnObj =  bankingAccountFacade.searchReal(nationalCode);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto<>(ResponseStatus.Ok, (CustomerDto) returnObj, null, null);



    }

    @RequestMapping(value = "/ws/advanceLegalSearch", method = RequestMethod.POST)
    public ResponseDto advanceLegalSearch(@RequestParam String name) {

        Object returnObj = bankingAccountFacade.advanceLegalSearch(name);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        List returnObjList = (List)returnObj;

        return new ResponseDto<>(ResponseStatus.Ok,  returnObjList, null, null);


    }

    @RequestMapping(value = "/ws/advanceRealSearch", method = RequestMethod.POST)
    public ResponseDto advanceRealSearch(@RequestParam String name) {

        Object returnObj = bankingAccountFacade.advanceRealSearch(name);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        List returnObjList = (List)returnObj;
        return new ResponseDto<>(ResponseStatus.Ok,  returnObjList, null, null);





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
    public ResponseDto savingAccountForReal(@RequestParam String nationalCode) {

        Object returnObj = bankingAccountFacade.savingAccountForReal(nationalCode);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto<>(ResponseStatus.Ok, null,  (String)returnObj, null);



    }

    @RequestMapping(value = "/ws/savingAccountForLegal", method = RequestMethod.POST)
    public ResponseDto<RealCustomerDto> savingAccountForLegal( @RequestParam String legalCode) {


        Object returnObj = bankingAccountFacade.savingAccountForLegal(legalCode);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto<>(ResponseStatus.Ok,null , (String)returnObj, null);




    }

    @RequestMapping(value = "/ws/searchByAccountNumber", method = RequestMethod.POST)
    public ResponseDto<SavingAccountDto> searchByAccountNumber(@RequestParam Integer accountNumber) {

        Object returnObj = bankingAccountFacade.searchByAccountNumber(accountNumber);

        if(returnObj instanceof  ResponseException)
            return new ResponseDto<>(ResponseStatus.Error, null, null, (ResponseException) returnObj);

        return new ResponseDto(ResponseStatus.Ok, (SavingAccountDto) returnObj, null, null);


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

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(new ClassPathResource(path + ".xml").getFile().getPath()));
        return new String(encoded, encoding);
    }
}

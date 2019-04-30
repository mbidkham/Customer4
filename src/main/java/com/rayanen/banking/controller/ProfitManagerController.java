package com.rayanen.banking.controller;

import com.rayanen.banking.model.dao.SavingAccountDao;
import com.rayanen.banking.model.entity.SavingAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.List;

@Component
public class ProfitManagerController {


    Logger logger = LoggerFactory.getLogger(ProfitManagerController.class);
    private SavingAccountDao savingAccountDao;

    public ProfitManagerController(SavingAccountDao savingAccountDao) {
        this.savingAccountDao = savingAccountDao;
    }
//هر  روز
//    @Scheduled(cron = "59 * * * * *")
//    public void profitManager() {
//        List<SavingAccount> savingAccounts = savingAccountDao.findAll();
//        for (SavingAccount savingAccount : savingAccounts) {
//            BigDecimal profitPercent= new BigDecimal(20);
//            BigDecimal year= new BigDecimal(36500);
//            savingAccount.setMonthlyProfit((savingAccount.getSumOfMinBalances() .multiply(profitPercent )).divide(year) );
//            savingAccount.setBalance(savingAccount.getBalance().add(savingAccount.getMonthlyProfit()));
//            savingAccount.setMonthlyProfit(BigDecimal.ZERO);
//            savingAccountDao.save(savingAccount);
//        }
//        logger.info("profitManager");
//
//
//    }
//هر ماه
//    @Scheduled(cron = "40 * * * * *")
//    public void minBalanceSetting() {
//
//        List<SavingAccount> savingAccounts = savingAccountDao.findAll();
//        if(savingAccounts.size()!=0){
//            for (SavingAccount sa : savingAccounts) {
//                sa.setSumOfMinBalances(sa.getMinBalance() .add(sa.getSumOfMinBalances()) );
//                sa.setMinBalance(sa.getBalance());
//                savingAccountDao.save(sa);
//            }
//        }
//
//
//
//    }
}

package com.dtx.seatademo.bank1.service.impl;

import com.dtx.seatademo.bank1.dao.AccountInfoDao;
import com.dtx.seatademo.bank1.service.AccountInfoService;
import com.dtx.seatademo.bank1.spring.Bank2Client;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    AccountInfoDao accountInfoDao;

    @Autowired
    Bank2Client bank2Client;

    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional//开启全局事务
    @Override
    public void updateAccountBalance(String accountNo, Double amount) {
        //扣减张三的金额
        accountInfoDao.updateAccountBalance(accountNo,amount *-1);
        //调用李四微服务，转账
        String transfer = bank2Client.transfer(amount);
        if("fallback".equals(transfer)){
            //调用李四微服务异常
            throw new RuntimeException("调用李四微服务异常");
        }
        if(amount == 2){
            //人为制造异常
            throw new RuntimeException("bank1 make exception..");
        }
    }
}

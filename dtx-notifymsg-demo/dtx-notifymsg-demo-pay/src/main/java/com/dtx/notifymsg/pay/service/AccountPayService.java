package com.dtx.notifymsg.pay.service;

import com.dtx.notifymsg.pay.entity.AccountPay;

/**
 * Created by Administrator.
 */
public interface AccountPayService {

    //充值
    public AccountPay insertAccountPay(AccountPay accountPay);
    //查询充值结果
    public AccountPay getAccountPay(String txNo);
}

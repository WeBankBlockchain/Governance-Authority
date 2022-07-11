package com.webank.authmanager.service;

import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.utils.TxReceiptUtils;

import java.math.BigInteger;

/**
 * @author aaronchu
 * @Description
 * @data 2020/05/12
 */
public class AuthByAdminService extends BasicAuthService {

    public AuthByAdminService(AuthManager authManager){
        super(authManager);
    }

    public void createGroup(String group, BigInteger mode) throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.createGroup(group, mode));
    }

    public void addAccountToGroup(String account, String group) throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.addAccountToGroup(account, group));
    }

    public void addFunctionToGroup(String contract, String functionName, String group) throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.addFunctionToGroup(contract, functionName, group));
    }

    public void removeAccountFromGroup(String account, String group) throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.removeAccountFromGroup(account, group));
    }

    public void removeFunctionFromGroup(String contract, String functionName, String group) throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.removeFunctionFromGroup(contract, functionName, group));
    }
}

package com.webank.authmanager.service;

import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.utils.TxReceiptUtils;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
/**
 * @author aaronchu
 * @Description
 * @data 2020/05/12
 */
public class GovByAdminService {

    private AuthManager authManager;

    public GovByAdminService(AuthManager authManager){
        this.authManager = authManager;
    }

    /**
     * TransferAdminAuth
     * @throws Exception
     */
    public void transferAdminAuth(String newAddress) throws Exception{
        TransactionReceipt r = authManager.transferAdminAuth(newAddress).send();
        TxReceiptUtils.ensureTxSucceed(r);
    }


}

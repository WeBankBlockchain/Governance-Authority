package com.webank.authmanager.service;

import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.utils.TxReceiptUtils;
import org.fisco.bcos.sdk.model.TransactionReceipt;

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
        TransactionReceipt r = authManager.transferAdminAuth(newAddress);
        TxReceiptUtils.ensureTxSucceed(r);
    }


}

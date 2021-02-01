/*
 * Copyright 2014-2020. the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 */

package com.webank.authmanager.handler;

import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.model.RequestInfo;
import com.webank.authmanager.utils.TxReceiptUtils;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple7;
import org.fisco.bcos.sdk.model.TransactionReceipt;

import java.math.BigInteger;
import java.util.List;

/**
 * AddGovernAccountHandler
 *
 * @author yuzhichu
 * @Description: AddGovernAccountHandler
 * @data 2020/3/2
 */
public class AddGovernAccountHandler {

    private AuthManager authManager;

    public AddGovernAccountHandler(AuthManager authManager){
        this.authManager = authManager;
    }

    //Add govern account
    public void createRequest(String account) throws Exception{
        TransactionReceipt r = this.authManager.requestAddGovernor(account);
        TxReceiptUtils.ensureTxSucceed(r);
    }

    public void deleteRequest(String account) throws Exception{
        TransactionReceipt r = this.authManager.deleteAddGovernorReq(account);
        TxReceiptUtils.ensureTxSucceed(r);
    }

    public void confirmRequest(String account) throws Exception{
        TransactionReceipt r = this.authManager.approveAddGovernorReq(account);
        TxReceiptUtils.ensureTxSucceed(r);
    }

    public void executeRequest(String account) throws Exception{
        TransactionReceipt r = this.authManager.executeAddGovernorReq(account);
        TxReceiptUtils.ensureTxSucceed(r);
    }

    public List<String> getPendingAccounts() throws Exception{
        List l = this.authManager.getGovernorsToAdd();
        return l;
    }

    public RequestInfo getRequest(String account) throws Exception{
        Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger> request =
                this.authManager.getAddGovRequest(account);
        return new RequestInfo(request.getValue1(), request.getValue2(), request.getValue3(),
                request.getValue4(), request.getValue5() ,request.getValue6(), request.getValue7());
    }
}

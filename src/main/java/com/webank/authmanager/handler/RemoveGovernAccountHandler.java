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
import org.fisco.bcos.web3j.tuples.generated.Tuple7;

import java.math.BigInteger;
import java.util.List;

/**
 * RemoveGovernAccountHandler
 *
 * @author yuzhichu
 * @Description: RemoveGovernAccountHandler
 * @data 2020/3/2
 */
public class RemoveGovernAccountHandler {

    private AuthManager authManager;

    public RemoveGovernAccountHandler(AuthManager authManager){
        this.authManager = authManager;
    }

    public void createRequest(String account) throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.requestRemoveGovernor(account).send());
    }

    public void deleteRequest(String account) throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.deleteRemoveGovernorReq(account).send());
    }

    public void confirmRequest(String account) throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.approveRemoveGovernorReq(account).send());
    }

    public void executeRequest(String account) throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.executeRemoveGovernorReq(account).send());
    }

    public RequestInfo getRequest(String account) throws Exception{
        Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger> request =
                this.authManager.getRemoveGovRequest(account).send();
        return new RequestInfo(request.getValue1(), request.getValue2(), request.getValue3(),
                request.getValue4(), request.getValue5() ,request.getValue6(), request.getValue7());
    }

    public List<String> getPendingAccounts() throws Exception{
        return this.authManager.getGovernorsToRemove().send();
    }
}

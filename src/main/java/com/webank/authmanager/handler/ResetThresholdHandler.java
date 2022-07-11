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

import com.webank.authmanager.constant.AuthConstants;
import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.model.RequestInfo;
import com.webank.authmanager.utils.TxReceiptUtils;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple7;

import java.math.BigInteger;

/**
 * ResetThresholdHandler
 *
 * @author yuzhichu
 * @Description: ResetThresholdHandler
 * @data 2020/3/2
 */

public class ResetThresholdHandler {

    private AuthManager authManager;

    public ResetThresholdHandler(AuthManager authManager){
        this.authManager = authManager;
    }

    public void createRequest(int newThreshold) throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.requestSetThreshold(BigInteger.valueOf(newThreshold)));
    }

    public void deleteRequest() throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.deleteSingle(AuthConstants.RESET_THRESHOLD_TXTYPE));
    }

    public void confirmRequest() throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.approveSingle(AuthConstants.RESET_THRESHOLD_TXTYPE));
    }

    public RequestInfo getResetRateRequest() throws Exception{
        Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger> request =
                this.authManager.getRequestSingle(AuthConstants.RESET_THRESHOLD_TXTYPE);
        return new RequestInfo(request.getValue1(), request.getValue2(), request.getValue3(),
                request.getValue4(), request.getValue5() ,request.getValue6(), request.getValue7());
    }

    public void executeRequest() throws Exception {
        TxReceiptUtils.ensureTxSucceed(this.authManager.executeSetThreshold());
    }
}

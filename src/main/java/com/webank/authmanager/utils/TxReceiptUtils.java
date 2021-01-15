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

package com.webank.authmanager.utils;

import com.webank.authmanager.exception.AuthException;
import com.webank.authmanager.exception.ErrorEnums;
import org.fisco.bcos.sdk.model.RetCode;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.codec.decode.ReceiptParser;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

/**
 * TransactionUtils
 *
 * @author yuzhichu
 * @Description: TransactionUtils
 * @data 2020/3/5
 */
public class TxReceiptUtils {

    public static void ensureTxSucceed(TransactionReceipt receipt) throws AuthException {
        try{
            ReceiptParser.parseTransactionReceipt(receipt);
        }
        catch (ContractException ex){
            throw new AuthException(ex.getMessage());
        }

    }

    /*
    public static Boolean decodeResultAsBoolean(TransactionReceipt receipt) throws Exception {
        ensureTxSucceed(receipt);
        InputAndOutputResult r =
                AuthManager.transactionDecoder.decodeOutputReturnObject(receipt.getInput(), receipt.getOutput());
        ResultEntity entity = r.getResult().get(0);
        Object data = entity.getData();
        if(!(data instanceof  Boolean)){
            throw new AuthException(ErrorEnums.DecodeFailed.getDescription());
        }
        Boolean v = (Boolean) entity.getData();
        return v;
    }

     */
}

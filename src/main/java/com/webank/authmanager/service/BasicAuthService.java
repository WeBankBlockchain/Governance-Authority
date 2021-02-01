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

package com.webank.authmanager.service;

import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.utils.FunctionUtils;
import org.fisco.bcos.sdk.crypto.hash.Hash;

/**
 * BasicAuthService
 *
 * @author yuzhichu
 * @Description: BasicAuthService
 * @data 2020/3/6
 */
class BasicAuthService {

    protected AuthManager authManager;

    BasicAuthService(AuthManager authManager){
        this.authManager = authManager;
    }

    public boolean canCallFunction(String contract, String functionName, String account) throws Exception{
        byte[] funcSig = FunctionUtils.resolveFuncSig(functionName);
        return this.authManager.canCallFunction(contract, funcSig, account).booleanValue();
    }

    public boolean containsAccount(String group, String account) throws Exception{
        return this.authManager.containsAccount(group, account).booleanValue();
    }

    public boolean containsFunction(String group, String contract, String function) throws Exception{
        return this.authManager.containsFunction(group, contract, function).booleanValue();
    }
}

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

package com.webank.authmanager;

import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.factory.AuthManagerFactory;
import com.webank.authmanager.service.*;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.hash.Hash;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;

/**
 * AuthServiceFactory
 *
 * @author yuzhichu
 * @Description: AuthServiceFactory
 * @data 2020/3/11
 */
public class AuthServiceFactory {

    public AuthManagerFactory getAuthManagerFactory(Client client, CryptoKeyPair credentials){
        return new AuthManagerFactory(client, credentials);
    }

    public GovByAdminService getGovByAdminService(AuthManager authManager){
        return new GovByAdminService(authManager);
    }

    public GovByMsigService getGovByMsigService(AuthManager authManager){
        return new GovByMsigService(authManager);
    }

    public GovByWeightedVoteService getGovByWeightedService(AuthManager authManager){
        return new GovByWeightedVoteService(authManager);
    }

    public AuthByAdminService getAuthSingleService(AuthManager authManager){
        return new AuthByAdminService(authManager);
    }

    public AuthByVoteService getAuthByVoteService(AuthManager authManager){
        return new AuthByVoteService(authManager);
    }
}

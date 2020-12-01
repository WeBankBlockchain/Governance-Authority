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

package com.webank.authmanager.factory;

import com.webank.authmanager.constant.AuthConstants;
import com.webank.authmanager.contract.AuthManager;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.springframework.stereotype.Component;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AuthManagerFactory {

    private Client client;

    private CryptoKeyPair credentials;

    public AuthManagerFactory(Client client, CryptoKeyPair credentials) {
        this.client = client;
        this.credentials = credentials;
    }

    public AuthManager createAdmin() throws Exception{
        AuthManager g = AuthManager
                .deploy(client, credentials, AuthConstants.ADMIN_MODE, new ArrayList<String>(), new ArrayList<BigInteger>(), BigInteger.ZERO);
        log.info("AuthManagerDeployed:"+g.getContractAddress());
        return g;
    }

    public AuthManager createGovWeight(List<String> accounts, List<BigInteger> weights, BigInteger threshold) throws Exception{
        AuthManager g = AuthManager
                .deploy(client, credentials, AuthConstants.GOVERNORS_MODE, accounts, weights, threshold);
        log.info("AuthManagerDeployed:"+g.getContractAddress());
        return g;
    }

    public AuthManager createGovMsig(List<String> accounts, BigInteger threshold) throws Exception{
        List<BigInteger> weights = new ArrayList<>();
        for(int i=0;i<accounts.size();i++){
            weights.add(BigInteger.ONE);
        }
        return createGovWeight(accounts, weights, threshold);
    }

    public AuthManager loadContract(String contractAddress) throws Exception{
        return AuthManager.load(contractAddress, client, credentials);
    }


}

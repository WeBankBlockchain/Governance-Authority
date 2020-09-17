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
import com.webank.authmanager.utils.TxReceiptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.NotImplementedException;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class AuthManagerFactory {

    private Web3j web3j;

    private Credentials credentials;

    private ContractGasProvider gasProvider;

    public AuthManagerFactory(Web3j web3j, Credentials credentials, ContractGasProvider gasProvider){
        this.web3j = web3j;
        this.credentials = credentials;
        this.gasProvider = gasProvider;
    }

    public AuthManager createAdmin() throws Exception{
        AuthManager g = AuthManager
                .deploy(web3j, credentials, gasProvider, AuthConstants.ADMIN_MODE, new ArrayList<String>(), new ArrayList<BigInteger>(), BigInteger.ZERO).send();
        log.info("AuthManagerDeployed:"+g.getContractAddress());
        return g;
    }

    public AuthManager createGovWeight(List<String> accounts, List<BigInteger> weights, BigInteger threshold) throws Exception{
        AuthManager g = AuthManager
                .deploy(web3j, credentials, gasProvider, AuthConstants.GOVERNORS_MODE, accounts, weights, threshold).send();
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
        return AuthManager.load(contractAddress, web3j, credentials, gasProvider);
    }


}

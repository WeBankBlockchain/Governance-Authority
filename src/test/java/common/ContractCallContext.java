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

package common;import com.webank.authmanager.contract.AuthManager;
import lombok.Getter;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * CallContext
 *
 * @author yuzhichu
 * @Description: CallContext
 * @data 2020/3/4
 */
@Getter
public class ContractCallContext {

    private String privateKey;

    private String publicKey;

    private String address;

    public ContractCallContext(String privateKey, String publicKey, String address){
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.address = address;
    }

    public AuthManager getAuthManager(AuthManager authManager){
        return AuthManager.load(authManager.getContractAddress(), BasicTest.INSTANCE.web3j, Credentials.create(privateKey, publicKey), BasicTest.INSTANCE.gasProvider);
    }

}

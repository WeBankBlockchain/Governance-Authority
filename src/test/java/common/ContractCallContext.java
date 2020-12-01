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

package common;

import com.webank.authmanager.contract.AuthManager;
import lombok.Getter;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.crypto.keypair.ECDSAKeyPair;

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
        this.privateKey = privateKey.startsWith("0x")?privateKey.substring(2):privateKey;
        this.publicKey = publicKey.startsWith("0x")?publicKey.substring(2):publicKey;;
        this.address =  address.startsWith("0x")?address.substring(2):address;
    }

    public AuthManager getAuthManager(AuthManager authManager){
        ECDSAKeyPair k = new ECDSAKeyPair();

        return AuthManager.load(authManager.getContractAddress(), BasicTest.INSTANCE.client, k.createKeyPair(privateKey));
    }

}

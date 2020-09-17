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

import com.webank.authmanager.App;
import com.webank.authmanager.config.SystemEnvironmentConfig;
import com.webank.authmanager.factory.AuthManagerFactory;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BasicTest
 *
 * @author yuzhichu
 * @Description: BasicTest
 * @data 2020/3/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class BasicTest {

    @Autowired
    public Web3j web3j;

    @Autowired
    public SystemEnvironmentConfig systemEnvironmentConfig;

    @Autowired
    public AuthManagerFactory factory;

    @Autowired
    public ContractGasProvider gasProvider;

    public static BasicTest INSTANCE;

    @PostConstruct
    public void init(){
        INSTANCE = this;
    }

    protected List<String> toAddresses(ContractCallContext ... ctxs){
        return Arrays.stream(ctxs).map(c->c.getAddress()).collect(Collectors.toList());
    }

    @Test
    public void any(){

    }
}

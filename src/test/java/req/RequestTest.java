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

package req;

import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.service.GovByMsigService;
import common.BasicTest;
import common.ContractCallContext;
import org.junit.Assert;
import org.junit.Test;


import java.math.BigInteger;

/**
 * RequestTest
 *
 * @author yuzhichu
 * @Description: RequestTest
 * @data 2020/3/13
 */
public class RequestTest extends BasicTest {

    @Test
    public void testDup() throws Exception{
        //Create contract with gov mode
        ContractCallContext gov1 = new ContractCallContext("0x79ce1b9fc3bbea4c5ccab8650c2d3af304a6c6afc5a69c09dafc5fb90eb27af2",
                "63063f696ddcb142761e33d2223510152a68de88dc6ba1fb1d0c96aefc087efa4b11213d556baea7f8b3bc61427e7c48447602d83134ed7a7c2dfd51c6cf9f17"
                ,"0x0f935671bc0ff46df02acfbd86379189dadafd7b");
        ContractCallContext gov2 = new ContractCallContext("0xb54ad7f2d8587dc3f8120d20287a9aae212d42f390d1d79fc11c56c96f2c73a7",
                "e7efa954f73ae9b1bbaa7310b24efad45818894439b14dfea855f4e45d54a267dc613c1eb892224a87f54d55a79ef0e6c0fa48b2b6ba2dfaba0ac32133f74fa",
                "0xec87b320b92cdf2d615b2a42e0aaf61090972517");
        AuthManager authManager1 = this.factory.createGovMsig(toAddresses(gov1, gov2), BigInteger.valueOf(2));
        authManager1 = gov1.getAuthManager(authManager1);
        GovByMsigService service1 = new GovByMsigService(authManager1);
        AuthManager authMgr2= gov2.getAuthManager(authManager1);
        GovByMsigService service2 = new GovByMsigService(authMgr2);


        service1.getResetThresholdHandler().createRequest(3);
        try{
            service2.getResetThresholdHandler().createRequest(3);
            Assert.assertTrue(false);
        }
        catch (Exception ex){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testAccountLevelDup() throws Exception{
        //Create contract with gov mode
        ContractCallContext gov1 = new ContractCallContext("0x79ce1b9fc3bbea4c5ccab8650c2d3af304a6c6afc5a69c09dafc5fb90eb27af2",
                "63063f696ddcb142761e33d2223510152a68de88dc6ba1fb1d0c96aefc087efa4b11213d556baea7f8b3bc61427e7c48447602d83134ed7a7c2dfd51c6cf9f17"
                ,"0x0f935671bc0ff46df02acfbd86379189dadafd7b");
        ContractCallContext gov2 = new ContractCallContext("0xb54ad7f2d8587dc3f8120d20287a9aae212d42f390d1d79fc11c56c96f2c73a7",
                "e7efa954f73ae9b1bbaa7310b24efad45818894439b14dfea855f4e45d54a267dc613c1eb892224a87f54d55a79ef0e6c0fa48b2b6ba2dfaba0ac32133f74fa",
                "0xec87b320b92cdf2d615b2a42e0aaf61090972517");
        AuthManager authManager1 = this.factory.createGovMsig(toAddresses(gov1, gov2), BigInteger.valueOf(2));
        authManager1 = gov1.getAuthManager(authManager1);
        GovByMsigService service1 = new GovByMsigService(authManager1);
        AuthManager authMgr2= gov2.getAuthManager(authManager1);
        GovByMsigService service2 = new GovByMsigService(authMgr2);


        service1.getAddGovernAccountHandler().createRequest("0x86726511057e38aec9320a63c466310c1e511355");
        try{
            service2.getAddGovernAccountHandler().createRequest("0x86726511057e38aec9320a63c466310c1e511355");
            Assert.assertTrue(false);
        }
        catch (Exception ex){
            ex.printStackTrace();
            Assert.assertTrue(true);
        }
    }

}

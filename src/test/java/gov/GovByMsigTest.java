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

package gov;

import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.model.RequestInfo;
import com.webank.authmanager.service.GovByMsigService;
import common.BasicTest;
import common.ContractCallContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

/**
 * GovByMsigTest
 *
 * @author yuzhichu
 * @Description: GovByMsigTest
 * @data 2020/3/5
 */
@Slf4j
public class GovByMsigTest extends BasicTest {

    @Test
    public void testAddGovernor() throws Exception{
        //Create contract with gov mode
        ContractCallContext gov1 = new ContractCallContext("0x79ce1b9fc3bbea4c5ccab8650c2d3af304a6c6afc5a69c09dafc5fb90eb27af2",
                "63063f696ddcb142761e33d2223510152a68de88dc6ba1fb1d0c96aefc087efa4b11213d556baea7f8b3bc61427e7c48447602d83134ed7a7c2dfd51c6cf9f17"
        ,"0x0f935671bc0ff46df02acfbd86379189dadafd7b");
        ContractCallContext gov2 = new ContractCallContext("0xb54ad7f2d8587dc3f8120d20287a9aae212d42f390d1d79fc11c56c96f2c73a7",
                "e7efa954f73ae9b1bbaa7310b24efad45818894439b14dfea855f4e45d54a267dc613c1eb892224a87f54d55a79ef0e6c0fa48b2b6ba2dfaba0ac32133f74fa",
                "0xec87b320b92cdf2d615b2a42e0aaf61090972517");
        ContractCallContext gov3 = new ContractCallContext("0xca8f81734944176eb1fa83cc1d01c594d64caa5387fe665688e8d30a5b6a3e62",
                "bc982d507fc05f0e3d94be813b35efe40a708fe35ffe4b7500c157f5be01ecabac4c1ba6e35cbfbf3e8a09bad0b96505939b4422a4b6a613c79ab85d1a80fced",
                "0x5c5bf65a384cf078afe31ab4a6137e91f180a105");
        ContractCallContext pending = new ContractCallContext("0xa77d07b09a27628d2a8dfad1d38ce6b20172957256ef4d4e7be9886a00bf9047",
                "f5a64689d8b9ce22f985f6b08c9744d261d98a5dc559e46605941f5f179ada907212de7e18dd4b7a901108d17e7601f095595e1badf5f1a97795833ddae04150",
                "0x3f347c3604f1ca44f996bf78aac8c3a5dd9f0f3e");
        AuthManager authManager1 = this.factory.createGovMsig(toAddresses(gov1, gov2, gov3), BigInteger.valueOf(2));
        authManager1 = gov1.getAuthManager(authManager1);
        GovByMsigService service1 = new GovByMsigService(authManager1);
        AuthManager authMgr2= gov2.getAuthManager(authManager1);
        GovByMsigService service2 = new GovByMsigService(authMgr2);
        AuthManager authMgr3= gov3.getAuthManager(authManager1);
        GovByMsigService service3 = new GovByMsigService(authMgr3);
        //Request Add governor
        service1.getAddGovernAccountHandler().createRequest(pending.getAddress());
        log.info("request create complete");
        List<String> pendingAccounts = service1.getAddGovernAccountHandler().getPendingAccounts();
        Assert.assertTrue(pending.getAddress().equalsIgnoreCase(pendingAccounts.get(0).substring(2)));
        //Vote
        service1.getAddGovernAccountHandler().confirmRequest(pending.getAddress());
        service2.getAddGovernAccountHandler().confirmRequest(pending.getAddress());
        service3.getAddGovernAccountHandler().confirmRequest(pending.getAddress());
        //Execute
        service1.getAddGovernAccountHandler().executeRequest(pending.getAddress());
        pendingAccounts = service1.getAddGovernAccountHandler().getPendingAccounts();
        Assert.assertTrue(pendingAccounts.isEmpty());
    }

    @Test
    public void testRemoveGovernor() throws Exception{
        //Create contract with gov mode
        ContractCallContext gov1 = new ContractCallContext("0x79ce1b9fc3bbea4c5ccab8650c2d3af304a6c6afc5a69c09dafc5fb90eb27af2",
                "63063f696ddcb142761e33d2223510152a68de88dc6ba1fb1d0c96aefc087efa4b11213d556baea7f8b3bc61427e7c48447602d83134ed7a7c2dfd51c6cf9f17"
                ,"0f935671bc0ff46df02acfbd86379189dadafd7b");
        ContractCallContext gov2 = new ContractCallContext("0xb54ad7f2d8587dc3f8120d20287a9aae212d42f390d1d79fc11c56c96f2c73a7",
                "e7efa954f73ae9b1bbaa7310b24efad45818894439b14dfea855f4e45d54a267dc613c1eb892224a87f54d55a79ef0e6c0fa48b2b6ba2dfaba0ac32133f74fa",
                "ec87b320b92cdf2d615b2a42e0aaf61090972517");
        ContractCallContext gov3 = new ContractCallContext("0xca8f81734944176eb1fa83cc1d01c594d64caa5387fe665688e8d30a5b6a3e62",
                "bc982d507fc05f0e3d94be813b35efe40a708fe35ffe4b7500c157f5be01ecabac4c1ba6e35cbfbf3e8a09bad0b96505939b4422a4b6a613c79ab85d1a80fced",
                "5c5bf65a384cf078afe31ab4a6137e91f180a105");
        AuthManager authManager1 = this.factory.createGovMsig(toAddresses(gov1, gov2, gov3), BigInteger.valueOf(2));
        authManager1 = gov1.getAuthManager(authManager1);
        GovByMsigService service1 = new GovByMsigService(authManager1);
        AuthManager authMgr2= gov2.getAuthManager(authManager1);
        GovByMsigService service2 = new GovByMsigService(authMgr2);
        //Request Add governor
        service1.getRemoveGovernAccountHandler().createRequest(gov3.getAddress());
        RequestInfo requestInfo = service1.getRemoveGovernAccountHandler().getRequest(gov3.getAddress());
        log.info("request create complete");
        List<String> pendingAccounts = service1.getRemoveGovernAccountHandler().getPendingAccounts();
        Assert.assertTrue(gov3.getAddress().equalsIgnoreCase(pendingAccounts.get(0).substring(2)));
        //Vote
        service1.getRemoveGovernAccountHandler().confirmRequest(gov3.getAddress());
        service2.getRemoveGovernAccountHandler().confirmRequest(gov3.getAddress());


        //Execute
        service1.getRemoveGovernAccountHandler().executeRequest(gov3.getAddress());
        pendingAccounts = service1.getRemoveGovernAccountHandler().getPendingAccounts();
        Assert.assertTrue(pendingAccounts.isEmpty());
    }

    @Test
    public void testResetThreshold() throws Exception{
        //Create contract with gov mode
        ContractCallContext gov1 = new ContractCallContext("0x79ce1b9fc3bbea4c5ccab8650c2d3af304a6c6afc5a69c09dafc5fb90eb27af2",
                "63063f696ddcb142761e33d2223510152a68de88dc6ba1fb1d0c96aefc087efa4b11213d556baea7f8b3bc61427e7c48447602d83134ed7a7c2dfd51c6cf9f17"
                ,"0x0f935671bc0ff46df02acfbd86379189dadafd7b");
        ContractCallContext gov2 = new ContractCallContext("0xb54ad7f2d8587dc3f8120d20287a9aae212d42f390d1d79fc11c56c96f2c73a7",
                "e7efa954f73ae9b1bbaa7310b24efad45818894439b14dfea855f4e45d54a267dc613c1eb892224a87f54d55a79ef0e6c0fa48b2b6ba2dfaba0ac32133f74fa",
                "0xec87b320b92cdf2d615b2a42e0aaf61090972517");
        ContractCallContext gov3 = new ContractCallContext("0xca8f81734944176eb1fa83cc1d01c594d64caa5387fe665688e8d30a5b6a3e62",
                "bc982d507fc05f0e3d94be813b35efe40a708fe35ffe4b7500c157f5be01ecabac4c1ba6e35cbfbf3e8a09bad0b96505939b4422a4b6a613c79ab85d1a80fced",
                "0x5c5bf65a384cf078afe31ab4a6137e91f180a105");
        AuthManager authManager1 = this.factory.createGovMsig(toAddresses(gov1, gov2, gov3), BigInteger.valueOf(3));
        authManager1 = gov1.getAuthManager(authManager1);
        GovByMsigService service1 = new GovByMsigService(authManager1);
        AuthManager authManager2 =  gov2.getAuthManager(authManager1);
        GovByMsigService service2 = new GovByMsigService(authManager2);
        AuthManager authManager3 = gov3.getAuthManager(authManager1);
        GovByMsigService service3 = new GovByMsigService(authManager3);

        //Create request
        service1.getResetThresholdHandler().createRequest(2);

        //Vote
        service1.getResetThresholdHandler().confirmRequest();
        service2.getResetThresholdHandler().confirmRequest();
        service3.getResetThresholdHandler().confirmRequest();
        RequestInfo r = service1.getResetThresholdHandler().getResetRateRequest();
        log.info("request {}/{}",r.getWeight(),r.getThreshold());
        //Execute
        service1.getResetThresholdHandler().executeRequest();
    }
}

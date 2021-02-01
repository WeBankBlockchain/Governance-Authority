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

package acl;

import com.webank.authmanager.constant.AuthConstants;
import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.model.RequestInfo;
import com.webank.authmanager.service.AuthByVoteService;
import common.BasicTest;
import common.ContractCallContext;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * AuthByVoteTest
 *
 * @author yuzhichu
 * @Description: AuthByVoteTest
 * @data 2020/3/6
 */
public class AuthByVoteTest extends BasicTest {

    private final String contract = "0x2d892c2a9c45cd640b232bbf33779d7ac8182eea";
    private final String function = "add(uint256,uint256)";

    @Test
    public void testWhitedList() throws Exception{
        String groupName = "whiteGroup";
        String inRoleAccount = "0xec87b320b92cdf2d615b2a42e0aaf61090972517";
        String outRoleAccount = "0x3f347c3604f1ca44f996bf78aac8c3a5dd9f0f3e";
        //Prepare governors
        ContractCallContext gov1 = new ContractCallContext("0x79ce1b9fc3bbea4c5ccab8650c2d3af304a6c6afc5a69c09dafc5fb90eb27af2",
                "63063f696ddcb142761e33d2223510152a68de88dc6ba1fb1d0c96aefc087efa4b11213d556baea7f8b3bc61427e7c48447602d83134ed7a7c2dfd51c6cf9f17"
                ,"0x0f935671bc0ff46df02acfbd86379189dadafd7b");
        ContractCallContext gov2 = new ContractCallContext("0xb54ad7f2d8587dc3f8120d20287a9aae212d42f390d1d79fc11c56c96f2c73a7",
                "e7efa954f73ae9b1bbaa7310b24efad45818894439b14dfea855f4e45d54a267dc613c1eb892224a87f54d55a79ef0e6c0fa48b2b6ba2dfaba0ac32133f74fa",
                "0xec87b320b92cdf2d615b2a42e0aaf61090972517");
        List<BigInteger> weights = new ArrayList<>();
        weights.add(BigInteger.ONE);
        weights.add(BigInteger.valueOf(2));
        AuthManager authManager = factory.createGovWeight(toAddresses(gov1, gov2), weights, BigInteger.valueOf(3));
        authManager = gov1.getAuthManager(authManager);

        AuthByVoteService service1 = new AuthByVoteService(authManager);
        AuthByVoteService service2 = new AuthByVoteService(gov2.getAuthManager(authManager));
        //Create group (delete then re create)
        service1.getCreateGroupHandler().request(groupName, AuthConstants.ACL_WHITELIST_MODE);
        service1.getCreateGroupHandler().confirm();
        service1.getCreateGroupHandler().delete();

        service1.getCreateGroupHandler().request(groupName, AuthConstants.ACL_WHITELIST_MODE);
        service1.getCreateGroupHandler().confirm();
        service2.getCreateGroupHandler().confirm();
        RequestInfo pendingInfo = service1.getCreateGroupHandler().getRequest();
        Assert.assertEquals(AuthConstants.CREATE_GROUP, pendingInfo.getTxType());
        Assert.assertEquals(BigInteger.valueOf(3), pendingInfo.getWeight());
        service1.getCreateGroupHandler().execute();

        //Add account to group
        service1.getAddAccountToGroupHandler().request(inRoleAccount, groupName);
        service1.getAddAccountToGroupHandler().confirm();
        service2.getAddAccountToGroupHandler().confirm();
        service2.getAddAccountToGroupHandler().execute();
        Assert.assertTrue(service1.containsAccount(groupName, inRoleAccount));
        Assert.assertFalse(service1.containsAccount(groupName, outRoleAccount));
        //Add function to group
        service1.getAddFunctionToGroupHandler().request(contract, function, groupName);
        service1.getAddFunctionToGroupHandler().confirm();
        service2.getAddFunctionToGroupHandler().confirm();
        service2.getAddFunctionToGroupHandler().execute();
        Assert.assertTrue(service1.containsFunction(groupName, contract, function));
        Assert.assertFalse(service2.containsFunction(groupName, contract, "whatever"));

        //Verify
        Assert.assertTrue(service1.canCallFunction(contract, function, inRoleAccount));
        Assert.assertFalse(service1.canCallFunction(contract, function, outRoleAccount));
    }

    @Test
    public void testBlackedList() throws Exception{
        String group = "blackGroup";
        String inRoleAccount = "0xec87b320b92cdf2d615b2a42e0aaf61090972517";
        String outRoleAccount = "0x3f347c3604f1ca44f996bf78aac8c3a5dd9f0f3e";
        //Prepare governors
        ContractCallContext gov1 = new ContractCallContext("0x79ce1b9fc3bbea4c5ccab8650c2d3af304a6c6afc5a69c09dafc5fb90eb27af2",
                "63063f696ddcb142761e33d2223510152a68de88dc6ba1fb1d0c96aefc087efa4b11213d556baea7f8b3bc61427e7c48447602d83134ed7a7c2dfd51c6cf9f17"
                ,"0x0f935671bc0ff46df02acfbd86379189dadafd7b");
        ContractCallContext gov2 = new ContractCallContext("0xb54ad7f2d8587dc3f8120d20287a9aae212d42f390d1d79fc11c56c96f2c73a7",
                "e7efa954f73ae9b1bbaa7310b24efad45818894439b14dfea855f4e45d54a267dc613c1eb892224a87f54d55a79ef0e6c0fa48b2b6ba2dfaba0ac32133f74fa",
                "0xec87b320b92cdf2d615b2a42e0aaf61090972517");
        List<BigInteger> weights = new ArrayList<>();
        weights.add(BigInteger.ONE);
        weights.add(BigInteger.valueOf(2));
        AuthManager authManager = factory.createGovWeight(toAddresses(gov1, gov2), weights, BigInteger.valueOf(3));
        authManager = gov1.getAuthManager(authManager);

        AuthByVoteService service1 = new AuthByVoteService(authManager);
        AuthByVoteService service2 = new AuthByVoteService(gov2.getAuthManager(authManager));
        //Create group
        service1.getCreateGroupHandler().request(group, AuthConstants.ACL_BLACKLIST_MODE);
        service1.getCreateGroupHandler().confirm();
        service2.getCreateGroupHandler().confirm();
        RequestInfo pendingInfo = service1.getCreateGroupHandler().getRequest();
        Assert.assertEquals(AuthConstants.CREATE_GROUP, pendingInfo.getTxType());
        Assert.assertEquals(BigInteger.valueOf(3), pendingInfo.getWeight());
        service1.getCreateGroupHandler().execute();

        //Add account to group
        service1.getAddAccountToGroupHandler().request(inRoleAccount, group);
        service1.getAddAccountToGroupHandler().confirm();
        service2.getAddAccountToGroupHandler().confirm();
        service2.getAddAccountToGroupHandler().execute();
        Assert.assertTrue(service1.containsAccount(group, inRoleAccount));
        Assert.assertFalse(service1.containsAccount(group, outRoleAccount));
        //Add function to group
        service1.getAddFunctionToGroupHandler().request(contract, function, group);
        service1.getAddFunctionToGroupHandler().confirm();
        service2.getAddFunctionToGroupHandler().confirm();
        service2.getAddFunctionToGroupHandler().execute();
        Assert.assertTrue(service1.containsFunction(group, contract, function));
        Assert.assertFalse(service2.containsFunction(group, contract, "whatever"));

        //Verify
        Assert.assertFalse(service1.canCallFunction(contract, function, inRoleAccount));
        Assert.assertTrue(service1.canCallFunction(contract, function, outRoleAccount));
    }


    @Test
    public void testRemoveAccount() throws Exception{
        String group = "group";
        String inRoleAccount = "0xec87b320b92cdf2d615b2a42e0aaf61090972517";
        //Prepare governors
        ContractCallContext gov1 = new ContractCallContext("0x79ce1b9fc3bbea4c5ccab8650c2d3af304a6c6afc5a69c09dafc5fb90eb27af2",
                "63063f696ddcb142761e33d2223510152a68de88dc6ba1fb1d0c96aefc087efa4b11213d556baea7f8b3bc61427e7c48447602d83134ed7a7c2dfd51c6cf9f17"
                ,"0x0f935671bc0ff46df02acfbd86379189dadafd7b");
        ContractCallContext gov2 = new ContractCallContext("0xb54ad7f2d8587dc3f8120d20287a9aae212d42f390d1d79fc11c56c96f2c73a7",
                "e7efa954f73ae9b1bbaa7310b24efad45818894439b14dfea855f4e45d54a267dc613c1eb892224a87f54d55a79ef0e6c0fa48b2b6ba2dfaba0ac32133f74fa",
                "0xec87b320b92cdf2d615b2a42e0aaf61090972517");
        List<BigInteger> weights = new ArrayList<>();
        weights.add(BigInteger.ONE);
        weights.add(BigInteger.valueOf(2));
        AuthManager authManager = factory.createGovWeight(toAddresses(gov1, gov2), weights, BigInteger.valueOf(3));
        authManager = gov1.getAuthManager(authManager);

        AuthByVoteService service1 = new AuthByVoteService(authManager);
        AuthByVoteService service2 = new AuthByVoteService(gov2.getAuthManager(authManager));
        //Create groups
        service1.getCreateGroupHandler().request(group, AuthConstants.ACL_WHITELIST_MODE);
        service1.getCreateGroupHandler().confirm();
        service2.getCreateGroupHandler().confirm();
        service1.getCreateGroupHandler().execute();
        //Add account to group1
        service1.getAddAccountToGroupHandler().request(inRoleAccount, group);
        service1.getAddAccountToGroupHandler().confirm();
        service2.getAddAccountToGroupHandler().confirm();
        service1.getAddAccountToGroupHandler().execute();
        boolean containsAccount = service1.containsAccount(group, inRoleAccount);
        Assert.assertTrue(containsAccount);

        //Remove account
        service1.getRemoveAccountFromGroupHandler().request(inRoleAccount, group);
        service1.getRemoveAccountFromGroupHandler().confirm();
        service2.getRemoveAccountFromGroupHandler().confirm();
        service1.getRemoveAccountFromGroupHandler().execute();
        containsAccount = service1.containsAccount(group, inRoleAccount);
        Assert.assertFalse(containsAccount);
    }

    @Test
    public void testRemoveFunction() throws Exception{
        String group = "group";
        String inRoleAccount = "0xec87b320b92cdf2d615b2a42e0aaf61090972517";
        //Prepare governors
        ContractCallContext gov1 = new ContractCallContext("0x79ce1b9fc3bbea4c5ccab8650c2d3af304a6c6afc5a69c09dafc5fb90eb27af2",
                "63063f696ddcb142761e33d2223510152a68de88dc6ba1fb1d0c96aefc087efa4b11213d556baea7f8b3bc61427e7c48447602d83134ed7a7c2dfd51c6cf9f17"
                ,"0x0f935671bc0ff46df02acfbd86379189dadafd7b");
        ContractCallContext gov2 = new ContractCallContext("0xb54ad7f2d8587dc3f8120d20287a9aae212d42f390d1d79fc11c56c96f2c73a7",
                "e7efa954f73ae9b1bbaa7310b24efad45818894439b14dfea855f4e45d54a267dc613c1eb892224a87f54d55a79ef0e6c0fa48b2b6ba2dfaba0ac32133f74fa",
                "0xec87b320b92cdf2d615b2a42e0aaf61090972517");
        List<BigInteger> weights = new ArrayList<>();
        weights.add(BigInteger.ONE);
        weights.add(BigInteger.valueOf(2));
        AuthManager authManager = factory.createGovWeight(toAddresses(gov1, gov2), weights, BigInteger.valueOf(3));
        authManager = gov1.getAuthManager(authManager);

        AuthByVoteService service1 = new AuthByVoteService(authManager);
        AuthByVoteService service2 = new AuthByVoteService(gov2.getAuthManager(authManager));
        //Create groups
        service1.getCreateGroupHandler().request(group, AuthConstants.ACL_WHITELIST_MODE);
        service1.getCreateGroupHandler().confirm();
        service2.getCreateGroupHandler().confirm();
        service1.getCreateGroupHandler().execute();
        //Add account to group1
        service1.getAddFunctionToGroupHandler().request(contract, function, group);
        service1.getAddFunctionToGroupHandler().confirm();
        service2.getAddFunctionToGroupHandler().confirm();
        service1.getAddFunctionToGroupHandler().execute();
        boolean containsFunction = service1.containsFunction(group, contract, function);
        Assert.assertTrue(containsFunction);

        //Remove account
        service1.getRemoveFunctionFromGroupHandler().request(contract, function, group);
        service1.getRemoveFunctionFromGroupHandler().confirm();
        service2.getRemoveFunctionFromGroupHandler().confirm();
        service1.getRemoveFunctionFromGroupHandler().execute();
        containsFunction = service1.containsAccount(group, inRoleAccount);
        Assert.assertFalse(containsFunction);
    }
}



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
import com.webank.authmanager.factory.AuthManagerFactory;
import com.webank.authmanager.service.AuthByAdminService;
import common.BasicTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AuthSingleService
 *
 * @author yuzhichu
 * @Description: AuthSingleService
 * @data 2020/3/6
 */
@Slf4j
public class AuthByAdminServiceTest extends BasicTest {

    private final String contract = "0x2d892c2a9c45cd640b232bbf33779d7ac8182eea";
    private final String function = "add(uint256,uint256)";

    @Autowired
    private AuthManagerFactory authManagerFactory;
    @Test
    public void testWhiteRole() throws Exception{
        String groupName = "whiteGroup";
        String inRoleAccount = "0xec87b320b92cdf2d615b2a42e0aaf61090972517";
        String outRoleAccount = "0x3f347c3604f1ca44f996bf78aac8c3a5dd9f0f3e";
        AuthManager authManager = authManagerFactory.createAdmin();
        AuthByAdminService authByAdminService = new AuthByAdminService(authManager);
        authByAdminService.createGroup(groupName, AuthConstants.ACL_WHITELIST_MODE);

        authByAdminService.addAccountToGroup(inRoleAccount, groupName);
        boolean containsAccount = authByAdminService.containsAccount(groupName, inRoleAccount);
        Assert.assertTrue(containsAccount);

        containsAccount = authByAdminService.containsAccount(groupName, outRoleAccount);
        Assert.assertFalse(containsAccount);

        authByAdminService.addFunctionToGroup(contract, function, groupName);
        boolean containsFunction = authByAdminService.containsFunction(groupName, contract, function);
        Assert.assertTrue(containsFunction);

        containsFunction = authByAdminService.containsFunction(groupName, contract, "whatever");
        Assert.assertFalse(containsFunction);

        boolean canCall = authByAdminService.canCallFunction(contract, function, inRoleAccount);
        Assert.assertTrue(canCall);

        canCall = authByAdminService.canCallFunction(contract, function, outRoleAccount);
        Assert.assertFalse(canCall);
    }

    @Test
    public void testBlackedRole() throws Exception{
        String groupName = "blackGroup";
        String inRoleAccount = "0xec87b320b92cdf2d615b2a42e0aaf61090972517";
        String outRoleAccount = "0x3f347c3604f1ca44f996bf78aac8c3a5dd9f0f3e";
        AuthManager authManager = authManagerFactory.createAdmin();
        AuthByAdminService authByAdminService = new AuthByAdminService(authManager);
        authByAdminService.createGroup(groupName, AuthConstants.ACL_BLACKLIST_MODE);

        authByAdminService.addAccountToGroup(inRoleAccount, groupName);
        boolean containsAccount = authByAdminService.containsAccount(groupName, inRoleAccount);
        Assert.assertTrue(containsAccount);

        containsAccount = authByAdminService.containsAccount(groupName, outRoleAccount);
        Assert.assertFalse(containsAccount);

        authByAdminService.addFunctionToGroup(contract, function, groupName);
        boolean containsFunction = authByAdminService.containsFunction(groupName, contract, function);
        Assert.assertTrue(containsFunction);

        containsFunction = authByAdminService.containsFunction(groupName, contract, "whatever");
        Assert.assertFalse(containsFunction);

        boolean canCall = authByAdminService.canCallFunction(contract, function, inRoleAccount);
        Assert.assertFalse(canCall);

        canCall = authByAdminService.canCallFunction(contract, function, outRoleAccount);
        Assert.assertTrue(canCall);
    }
    @Test
    public void testRemoveAccount() throws Exception{
        String groupName = "group";
        String inRoleAccount = "0xec87b320b92cdf2d615b2a42e0aaf61090972517";

        AuthManager authManager = authManagerFactory.createAdmin();
        AuthByAdminService authByAdminService = new AuthByAdminService(authManager);
        authByAdminService.createGroup(groupName, AuthConstants.ACL_WHITELIST_MODE);
        authByAdminService.addAccountToGroup(inRoleAccount, groupName);
        boolean containsAccount = authByAdminService.containsAccount(groupName, inRoleAccount);
        Assert.assertTrue(containsAccount);

        authByAdminService.addFunctionToGroup(contract, function, groupName);
        boolean canCall = authByAdminService.canCallFunction(contract, function, inRoleAccount);
        Assert.assertTrue(canCall);

        //
        authByAdminService.removeAccountFromGroup(inRoleAccount, groupName);
        containsAccount = authByAdminService.containsAccount(groupName, inRoleAccount);
        Assert.assertFalse(containsAccount);

        canCall = authByAdminService.canCallFunction(contract, function, inRoleAccount);
        Assert.assertFalse(canCall);
    }
    @Test
    public void testRemoveFunction() throws Exception{
        String group = "groupName";
        String inRoleAccount = "0xec87b320b92cdf2d615b2a42e0aaf61090972517";

        AuthManager authManager = authManagerFactory.createAdmin();
        AuthByAdminService authByAdminService = new AuthByAdminService(authManager);
        authByAdminService.createGroup(group, AuthConstants.ACL_BLACKLIST_MODE);
        authByAdminService.addAccountToGroup(inRoleAccount, group);
        authByAdminService.addFunctionToGroup(contract, function, group);
        boolean containsFunction = authByAdminService.containsFunction(group, contract, function);
        Assert.assertTrue(containsFunction);

        boolean canCall = authByAdminService.canCallFunction(contract, function, inRoleAccount);
        Assert.assertFalse(canCall);

        //
        authByAdminService.removeFunctionFromGroup(contract,function, group);
        containsFunction = authByAdminService.containsFunction(group, contract, function);
        Assert.assertFalse(containsFunction);

        canCall = authByAdminService.canCallFunction(contract, function, inRoleAccount);
        Assert.assertTrue(canCall);
    }

    @Test
    public void testAdmin() throws Exception{
        AuthManager authManager = this.authManagerFactory.createAdmin();
        AuthByAdminService service = new AuthByAdminService(authManager);

        boolean canCall = service.canCallFunction(contract, function, "0x3f347c3604f1ca44f996bf78aac8c3a5dd9f0f3e");
        System.out.println(canCall);
    }



}

















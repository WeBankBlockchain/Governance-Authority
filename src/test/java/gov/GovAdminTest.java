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

import com.webank.authmanager.App;
import com.webank.authmanager.config.SystemEnvironmentConfig;
import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.factory.AuthManagerFactory;
import com.webank.authmanager.service.GovByAdminService;
import common.BasicTest;
import common.ContractCallContext;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * GovAdminTest
 *
 * @author yuzhichu
 * @Description: GovAdminTest
 * @data 2020/3/4
 */

@Slf4j
public class GovAdminTest extends BasicTest {

    @Autowired
    private Credentials credentials;

    @Test
    public void test() throws Exception{
        //Init
        AuthManager authManager = factory.createAdmin();
        log.info(authManager.getContractAddress());

        boolean isAdmin = authManager.isAdmin().send();
        Assert.assertTrue(isAdmin);

        //Transfer
        String finalAdmin = "0x5c5bf65a384cf078afe31ab4a6137e91f180a105";
        GovByAdminService govByAdminService = new GovByAdminService(authManager);
        govByAdminService.transferAdminAuth(finalAdmin);

        //Verify
        isAdmin = authManager.isAdmin().send();
        Assert.assertFalse(isAdmin);

        ContractCallContext ctx = new ContractCallContext("0xca8f81734944176eb1fa83cc1d01c594d64caa5387fe665688e8d30a5b6a3e62",
                "bc982d507fc05f0e3d94be813b35efe40a708fe35ffe4b7500c157f5be01ecabac4c1ba6e35cbfbf3e8a09bad0b96505939b4422a4b6a613c79ab85d1a80fced",
                finalAdmin);
        authManager = ctx.getAuthManager(authManager);//From final admin's perspective
        isAdmin = authManager.isAdmin().send();
        Assert.assertTrue(isAdmin);
    }

}
/**
 私钥:0xb54ad7f2d8587dc3f8120d20287a9aae212d42f390d1d79fc11c56c96f2c73a7
 公钥：e7efa954f73ae9b1bbaa7310b24efad45818894439b14dfea855f4e45d54a267dc613c1eb892224a87f54d55a79ef0e6c0fa48b2b6ba2dfaba0ac32133f74fa
 地址:0xec87b320b92cdf2d615b2a42e0aaf61090972517

 私钥:0xca8f81734944176eb1fa83cc1d01c594d64caa5387fe665688e8d30a5b6a3e62
 公钥：bc982d507fc05f0e3d94be813b35efe40a708fe35ffe4b7500c157f5be01ecabac4c1ba6e35cbfbf3e8a09bad0b96505939b4422a4b6a613c79ab85d1a80fced
 地址:0x5c5bf65a384cf078afe31ab4a6137e91f180a105

 私钥:0xa77d07b09a27628d2a8dfad1d38ce6b20172957256ef4d4e7be9886a00bf9047
 公钥：f5a64689d8b9ce22f985f6b08c9744d261d98a5dc559e46605941f5f179ada907212de7e18dd4b7a901108d17e7601f095595e1badf5f1a97795833ddae04150
 地址:0x3f347c3604f1ca44f996bf78aac8c3a5dd9f0f3e

 私钥:0x23a411376549e6f72db98ec6e21b9331fd596030007c26b6c16b6030ff9170b6
 公钥：c1bfd5f8b81c2db0338415538901adafdfefaddb1cf5060bb6a62dfe16609c6a395c3b7a857f2adaedf2f093b6ced717213f08b473e98b3f560c385aef1f106d
 地址:0x63f4d723aef61ee4f25e5ac82ded538c141dc7d1

 私钥:0xea998688709b8892faa3137587a470842132e5b9f6da36e4000f02b388161a75
 公钥：4d7fc2b056a882e758702b3cbbb26da7334b852a52aeb83957d0d3690305e8e3dc0dcbdc63eba718a249e1dcd60f7c1ed3ff388779b5a1085e51f686c02429c0
 地址:0x2d892c2a9c45cd640b232bbf33779d7ac8182eea

 私钥:0xf3371335b9b393c5840f0260914ee2378a73b474e9e4da04f61bbe60404fd19f
 公钥：145a2af654c7d31d088c1f4fd030fb3f5c4b8a9ae01e046d513a6b30329bb4f53947ec6ffd10c9f9532719734cece706fe2633b9cd3aba0467b66f5c17bd5554
 地址:0x86726511057e38aec9320a63c466310c1e511355

 私钥:0x29ce8822ca428454ae32fe26133d024168c2344a9392b3da44f89402a520cc58
 公钥：d3b0c272156ddebd2c71cde4f27d824e018b34d1887587d9579774426a95681f373bf21bad9bc34b06a7e0a524007629716c66c65e0995d5d38aa2e223a2461e
 地址:0xb92b93e8865c60df7c3532a81911e43f14d8227a

 私钥:0x640d5efb8ca3e4a517ec67838fcd2fe630201b47b3c18ace92d1b81376fa88ec
 公钥：8f789ed68e5df7f5a1656facd648d4397f86c2137feb1b495d2c8b9e183311385f8e42f4f83e7a6a1e1167967ad45ca020bbaedac874cdc377f82b2c94c357f9
 地址:0x58898e1b95154f4b3d479d3a32a820f7cdc95772

 私钥:0x1fce8553b66bdc60455d7a3e28b570b3cf0ad5c503c377a494a08998f265a026
 公钥：724f889270e941d4d552aad132e33fb817c0cbb74b0ef050f03b322bd6ba495c17e81a25f6a8f4a41107217fd2030ff296fc1cae1da1665b3654defff340787f
 地址:0x0b82f16762eeb2313380263f280ececc2a5fb809

 当前用户：
 私钥:0x79ce1b9fc3bbea4c5ccab8650c2d3af304a6c6afc5a69c09dafc5fb90eb27af2
 公钥：63063f696ddcb142761e33d2223510152a68de88dc6ba1fb1d0c96aefc087efa4b11213d556baea7f8b3bc61427e7c48447602d83134ed7a7c2dfd51c6cf9f17
 地址:0x0f935671bc0ff46df02acfbd86379189dadafd7b
 */

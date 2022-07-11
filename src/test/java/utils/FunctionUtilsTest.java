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

package utils;

import com.webank.authmanager.utils.FunctionUtils;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.utils.Hex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FunctionUtilsTest
 *
 * @author yuzhichu
 * @Description: FunctionUtilsTest
 * @data 2020/3/6
 */
@Slf4j
public class FunctionUtilsTest {

    @Test
    public void test() throws Exception{
        byte[] bytes = FunctionUtils.resolveFuncSig("add(uint256,uint256)");
        Assertions.assertEquals("771602f7", Hex.toHexString(bytes));

        bytes = FunctionUtils.resolveFuncSig("demo(uint256)");
        Assertions.assertEquals("071bd079", Hex.toHexString(bytes));

        bytes = FunctionUtils.resolveFuncSig("set(uint256,string,uint256[])");
        System.out.println( Hex.toHexString(bytes));
    }


}

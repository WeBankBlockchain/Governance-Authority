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

package com.webank.authmanager.utils;


import org.fisco.bcos.sdk.v3.crypto.hash.Hash;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * FunctionUtils
 *
 * @author yuzhichu
 * @Description: FunctionUtils
 * @data 2020/3/3
 */
public class FunctionUtils {

    public static byte[] resolveFuncSig(String functionName, Hash hash){

        byte[] funcSig = Arrays.copyOfRange(hash.hash(functionName.getBytes(StandardCharsets.UTF_8)), 0, 4);
        return funcSig;
    }

    public static byte[] resolveFuncSig(String functionName){

        byte[] funcSig = Arrays.copyOfRange(HashUtils.hash(functionName.getBytes(StandardCharsets.UTF_8)), 0, 4);
        return funcSig;
    }
}

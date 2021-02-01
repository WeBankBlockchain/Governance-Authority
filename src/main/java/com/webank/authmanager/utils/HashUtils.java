package com.webank.authmanager.utils;

import org.fisco.bcos.sdk.crypto.hash.Hash;
import org.fisco.bcos.sdk.crypto.hash.Keccak256;

/**
 * @author aaronchu
 * @Description
 * @data 2020/11/30
 */
public class HashUtils {

    private HashUtils(){}

    private static Hash hash = new Keccak256();


    public static void setHash(Hash hash) {
        HashUtils.hash = hash;
    }

    public static String hash(final String inputData){
        return hash.hash(inputData);
    }

    public static byte[] hash(final byte[] inputBytes){
        return hash.hash(inputBytes);
    }

    public static Hash getHash(){
        return hash;
    }

}

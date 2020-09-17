package com.webank.authmanager.constant;

import com.fasterxml.jackson.databind.node.BigIntegerNode;

import java.math.BigInteger;

public class AuthConstants {

    public static final BigInteger ADMIN_MODE  = BigInteger.ONE;

    public static final BigInteger GOVERNORS_MODE = BigInteger.valueOf(2);

    public static final BigInteger RESET_THRESHOLD_TXTYPE = BigInteger.valueOf(1);

    public static final BigInteger RESET_GOVERNORS_TXTYPE = BigInteger.valueOf(2);

    public static final BigInteger ADD_GOVERNOR = BigInteger.valueOf(11);

    public static final BigInteger REMOVE_GOVERNOR = BigInteger.valueOf(12);

    public static final BigInteger CREATE_GROUP = BigInteger.valueOf(3);

    public static final BigInteger ADD_ACCOUNT_TO_GROUP = BigInteger.valueOf(4);

    public static final BigInteger ADD_FUNCTION_TO_GROUP = BigInteger.valueOf(5);

    public static final BigInteger REMOVE_ACCOUNT_FROM_GROUP = BigInteger.valueOf(6);

    public static final BigInteger REMOVE_FUNCTION_FROM_GROUP = BigInteger.valueOf(7);

    public static final BigInteger ACL_WHITELIST_MODE = BigInteger.valueOf(1);

    public static final BigInteger ACL_BLACKLIST_MODE = BigInteger.valueOf(2);


}

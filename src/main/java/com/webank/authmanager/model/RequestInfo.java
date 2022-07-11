package com.webank.authmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@AllArgsConstructor
@Data
public class RequestInfo {

    private BigInteger id;

    private String requestAddress;

    private BigInteger threshold;

    private String address;

    private BigInteger weight;

    private BigInteger txType;

    private BigInteger status;

}

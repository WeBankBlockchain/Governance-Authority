package com.webank.authmanager.model;

import lombok.Data;

/**
 * @author aaronchu
 * @Description
 * @data 2020/07/24
 */
@Data
public class AddFunctionToGroupParam {

    private String contract;

    private String function;

    private String group;
}

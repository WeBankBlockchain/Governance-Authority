package com.webank.authmanager.model;

import lombok.Data;

/**
 * @author aaronchu
 * @Description
 * @data 2020/07/24
 */
@Data
public class AddAccountToGroupParam {

    private String account;

    private String group;
}

package com.webank.authmanager.service;

import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.handler.ResetGovernAccountsHandler;
import com.webank.authmanager.handler.ResetThresholdHandler;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author aaronchu
 * @Description
 * @data 2020/05/12
 */
public class GovByWeightedVoteService {

    private ResetThresholdHandler resetThresholdHandler;
    private ResetGovernAccountsHandler resetGovernAccountsHandler;

    @Autowired
    public GovByWeightedVoteService(AuthManager authManager){
        this.resetThresholdHandler = new ResetThresholdHandler(authManager);
        this.resetGovernAccountsHandler = new ResetGovernAccountsHandler(authManager);
    }

    public ResetThresholdHandler getResetThresholdHandler(){
        return resetThresholdHandler;
    }

    public ResetGovernAccountsHandler getResetGovernAccountsHandler(){return resetGovernAccountsHandler;}
}

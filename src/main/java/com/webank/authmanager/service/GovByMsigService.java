package com.webank.authmanager.service;

import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.handler.AddGovernAccountHandler;
import com.webank.authmanager.handler.RemoveGovernAccountHandler;
import com.webank.authmanager.handler.ResetThresholdHandler;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author aaronchu
 * @Description
 * @data 2020/05/12
 */
public class GovByMsigService {

    private ResetThresholdHandler resetThresholdHandler;
    private AddGovernAccountHandler addGovernAccountHandler;
    private RemoveGovernAccountHandler removeGovernAccountHandler;

    @Autowired
    public GovByMsigService(AuthManager authManager){
        this.resetThresholdHandler = new ResetThresholdHandler(authManager);
        this.addGovernAccountHandler = new AddGovernAccountHandler(authManager);
        this.removeGovernAccountHandler = new RemoveGovernAccountHandler(authManager);
    }


    public ResetThresholdHandler getResetThresholdHandler(){
        return resetThresholdHandler;
    }

    public AddGovernAccountHandler getAddGovernAccountHandler(){
        return this.addGovernAccountHandler;
    }

    public RemoveGovernAccountHandler getRemoveGovernAccountHandler(){
        return this.removeGovernAccountHandler;
    }
}

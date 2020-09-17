package com.webank.authmanager.service;

import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.handler.*;
import lombok.Getter;
/**
 * @author aaronchu
 * @Description
 * @data 2020/05/12
 */
@Getter
public class AuthByVoteService extends BasicAuthService {

    private CreateGroupHandler createGroupHandler;
    private AddAccountToGroupHandler addAccountToGroupHandler;
    private AddFunctionToGroupHandler addFunctionToGroupHandler;
    private RemoveAccountFromGroupHandler removeAccountFromGroupHandler;
    private RemoveFunctionFromGroupHandler removeFunctionFromGroupHandler;

    public AuthByVoteService(AuthManager authManager){
        super(authManager);
        this.createGroupHandler = new CreateGroupHandler(authManager);
        this.addAccountToGroupHandler = new AddAccountToGroupHandler(authManager);
        this.addFunctionToGroupHandler = new AddFunctionToGroupHandler(authManager);
        this.removeAccountFromGroupHandler = new RemoveAccountFromGroupHandler(authManager);
        this.removeFunctionFromGroupHandler = new RemoveFunctionFromGroupHandler(authManager);
  }
}

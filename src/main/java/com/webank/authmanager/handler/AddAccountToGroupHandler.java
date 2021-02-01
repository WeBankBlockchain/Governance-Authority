package com.webank.authmanager.handler;

import com.webank.authmanager.constant.AuthConstants;
import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.model.AddAccountToGroupParam;
import com.webank.authmanager.model.RequestInfo;
import com.webank.authmanager.utils.TxReceiptUtils;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple7;


import java.math.BigInteger;

/**
 * @author aaronchu
 * @Description
 * @data 2020/05/12
 */
public class AddAccountToGroupHandler {

    private AuthManager authManager;

    public AddAccountToGroupHandler(AuthManager authManager){
        this.authManager = authManager;
    }

    public void request(String account, String group) throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.requestAddAccountToGroup(account, group));
    }

    public void confirm() throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.approveSingle(AuthConstants.ADD_ACCOUNT_TO_GROUP));
    }

    public void execute() throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.executeAddAccountToGroup());
    }

    public void delete() throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.deleteSingle(AuthConstants.ADD_ACCOUNT_TO_GROUP));
    }

    public RequestInfo getRequest() throws Exception{
        Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger> request =
                this.authManager.getRequestSingle(AuthConstants.ADD_ACCOUNT_TO_GROUP);
        return new RequestInfo(request.getValue1(), request.getValue2(), request.getValue3(),
                request.getValue4(), request.getValue5() ,request.getValue6(), request.getValue7());
    }

    public AddAccountToGroupParam getDetail() throws Exception{
        Tuple2<String, String> tuple = this.authManager.viewAddAccountToGroup();
        AddAccountToGroupParam param = new AddAccountToGroupParam();
        param.setAccount(tuple.getValue1());
        param.setGroup(tuple.getValue2());
        return param;
    }

}

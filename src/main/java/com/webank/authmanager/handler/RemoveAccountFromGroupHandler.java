package com.webank.authmanager.handler;

import com.webank.authmanager.constant.AuthConstants;
import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.model.RemoveAccountFromGroupParam;
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
public class RemoveAccountFromGroupHandler {

    private AuthManager authManager;

    public RemoveAccountFromGroupHandler(AuthManager authManager){
        this.authManager = authManager;
    }

    public void request(String account, String group) throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.requestRemoveAccountFromGroup(account, group));
    }

    public void confirm() throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.approveSingle(AuthConstants.REMOVE_ACCOUNT_FROM_GROUP));
    }

    public void execute() throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.executeRemoveAccountFromGroup());
    }

    public void delete() throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.deleteSingle(AuthConstants.REMOVE_ACCOUNT_FROM_GROUP));
    }

    public RequestInfo getRequest() throws Exception{
        Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger> request =
                this.authManager.getRequestSingle(AuthConstants.REMOVE_ACCOUNT_FROM_GROUP);
        return new RequestInfo(request.getValue1(), request.getValue2(), request.getValue3(),
                request.getValue4(), request.getValue5() ,request.getValue6(), request.getValue7());
    }

    public RemoveAccountFromGroupParam getDetail() throws Exception{
        Tuple2<String, String> tuple = this.authManager.viewRemoveAccountToGroup();
        RemoveAccountFromGroupParam param = new RemoveAccountFromGroupParam();
        param.setAccount(tuple.getValue1());
        param.setGroup(tuple.getValue2());
        return param;
    }

}

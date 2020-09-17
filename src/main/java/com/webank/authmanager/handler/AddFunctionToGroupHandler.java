package com.webank.authmanager.handler;

import com.webank.authmanager.constant.AuthConstants;
import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.model.AddAccountToGroupParam;
import com.webank.authmanager.model.AddFunctionToGroupParam;
import com.webank.authmanager.model.RequestInfo;
import com.webank.authmanager.utils.TxReceiptUtils;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;

import java.math.BigInteger;

/**
 * @author aaronchu
 * @Description
 * @data 2020/05/12
 */
public class AddFunctionToGroupHandler {

    private AuthManager authManager;

    public AddFunctionToGroupHandler(AuthManager authManager){
        this.authManager = authManager;
    }

    public void request(String contract, String function, String group) throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.requestAddFunctionToGroup(contract, function, group).send());
    }

    public void confirm() throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.approveSingle(AuthConstants.ADD_FUNCTION_TO_GROUP).send());
    }

    public void execute() throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.executeAddFunctionToGroup().send());
    }

    public void delete() throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.deleteSingle(AuthConstants.ADD_FUNCTION_TO_GROUP).send());
    }

    public RequestInfo getRequest() throws Exception{
        Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger> request =
                this.authManager.getRequestSingle(AuthConstants.ADD_FUNCTION_TO_GROUP).send();
        return new RequestInfo(request.getValue1(), request.getValue2(), request.getValue3(),
                request.getValue4(), request.getValue5() ,request.getValue6(), request.getValue7());
    }

    public AddFunctionToGroupParam getDetail() throws Exception{
        Tuple3<String, String, String> tuple = this.authManager.viewAddFunctionToGroup().send();
        AddFunctionToGroupParam param = new AddFunctionToGroupParam();
        param.setContract(tuple.getValue1());
        param.setFunction(tuple.getValue2());
        param.setGroup(tuple.getValue3());
        return param;
    }

}

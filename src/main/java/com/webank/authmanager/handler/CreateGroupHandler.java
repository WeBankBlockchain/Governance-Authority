package com.webank.authmanager.handler;

import com.webank.authmanager.constant.AuthConstants;
import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.model.CreateGroupParam;
import com.webank.authmanager.model.RequestInfo;
import com.webank.authmanager.utils.TxReceiptUtils;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple7;

import java.math.BigInteger;

/**
 * @author aaronchu
 * @Description
 * @data 2020/05/12
 */
public class CreateGroupHandler{
    private AuthManager authManager;

    public CreateGroupHandler(AuthManager authManager){
        this.authManager = authManager;
    }

    public void request(String group, BigInteger mode) throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.requestCreateGroup(group, mode));
    }

    public void confirm() throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.approveSingle(AuthConstants.CREATE_GROUP));
    }

    public void execute() throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.executeCreateGroup());
    }

    public void delete() throws Exception{
        TxReceiptUtils.ensureTxSucceed(this.authManager.deleteSingle(AuthConstants.CREATE_GROUP));
    }

    public RequestInfo getRequest() throws Exception{
        Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger> request =
                this.authManager.getRequestSingle(AuthConstants.CREATE_GROUP);
        return new RequestInfo(request.getValue1(), request.getValue2(), request.getValue3(),
                request.getValue4(), request.getValue5() ,request.getValue6(), request.getValue7());
    }

    public CreateGroupParam getDetail() throws Exception{
        Tuple2<String, BigInteger> tuple = this.authManager.viewCreateGroup();
        CreateGroupParam createGroupParam = new CreateGroupParam();
        createGroupParam.setGroup(tuple.getValue1());
        createGroupParam.setMode(tuple.getValue2().intValue());
        return createGroupParam;
    }

}

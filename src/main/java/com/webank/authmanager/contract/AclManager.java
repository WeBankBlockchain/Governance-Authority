package com.webank.authmanager.contract;

import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.*;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint8;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class AclManager extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50610574806100206000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063397bc52a1461005c578063a7ea3ab614610143578063abef281e146101e4575b600080fd5b34801561006857600080fd5b50610129600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610275565b604051808215151515815260200191505060405180910390f35b34801561014f57600080fd5b506101ca600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506103e9565b604051808215151515815260200191505060405180910390f35b3480156101f057600080fd5b5061024b600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506104ab565b604051808460ff1660ff168152602001838152602001828152602001935050505060405180910390f35b600080846040518082805190602001908083835b6020831015156102ae5780518252602082019150602081019050602083039250610289565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060040160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000836040518082805190602001908083835b6020831015156103595780518252602082019150602081019050602083039250610334565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390207bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1690509392505050565b600080836040518082805190602001908083835b60208310151561042257805182526020820191506020810190506020830392506103fd565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060030160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b6000806000806000856040518082805190602001908083835b6020831015156104e957805182526020820191506020810190506020830392506104c4565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902090508060000160009054906101000a900460ff16816001015482600201549350935093505091939092505600a165627a7a723058208df458ba47e55176f69df959b96a602c480b5c1927e62173a56bc547cf9b976c0029"};

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50610574806100206000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806348fc7e041461005c5780634d85cca4146100fd578063ffa747a81461018e575b600080fd5b34801561006857600080fd5b506100e3600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610275565b604051808215151515815260200191505060405180910390f35b34801561010957600080fd5b50610164600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610337565b604051808460ff1660ff168152602001838152602001828152602001935050505060405180910390f35b34801561019a57600080fd5b5061025b600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506103d4565b604051808215151515815260200191505060405180910390f35b600080836040518082805190602001908083835b6020831015156102ae5780518252602082019150602081019050602083039250610289565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060030160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16905092915050565b6000806000806000856040518082805190602001908083835b6020831015156103755780518252602082019150602081019050602083039250610350565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902090508060000160009054906101000a900460ff1681600101548260020154935093509350509193909250565b600080846040518082805190602001908083835b60208310151561040d57805182526020820191506020810190506020830392506103e8565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060040160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000836040518082805190602001908083835b6020831015156104b85780518252602082019150602081019050602083039250610493565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390207bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff16905093925050505600a165627a7a723058204993cca56dbdecc4f08da332fcdf90679808206c3b6a170f47411871e651b38b0029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"conflictFields\":[{\"kind\":0}],\"constant\":true,\"inputs\":[{\"name\":\"group\",\"type\":\"string\"},{\"name\":\"contractAddr\",\"type\":\"address\"},{\"name\":\"func\",\"type\":\"string\"}],\"name\":\"containsFunction\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"selector\":[964412714,4289152936],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0}],\"constant\":true,\"inputs\":[{\"name\":\"group\",\"type\":\"string\"},{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"containsAccount\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"selector\":[2817145526,1224506884],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":3,\"slot\":0,\"value\":[0]}],\"constant\":true,\"inputs\":[{\"name\":\"group\",\"type\":\"string\"}],\"name\":\"getGroup\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"},{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"selector\":[2884577310,1300614308],\"stateMutability\":\"view\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"group\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"mode\",\"type\":\"uint8\"}],\"name\":\"CreateGroup\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"account\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"group\",\"type\":\"string\"}],\"name\":\"AddAccountToGroup\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"contractAddr\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"func\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"group\",\"type\":\"string\"}],\"name\":\"AddFunctionToGroup\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"account\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"group\",\"type\":\"string\"}],\"name\":\"RemoveAccountFromGroup\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"contractAddr\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"func\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"group\",\"type\":\"string\"}],\"name\":\"RemoveFunctionFromGroup\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_CONTAINSFUNCTION = "containsFunction";

    public static final String FUNC_CONTAINSACCOUNT = "containsAccount";

    public static final String FUNC_GETGROUP = "getGroup";

    public static final Event CREATEGROUP_EVENT = new Event("CreateGroup", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}));
    ;

    public static final Event ADDACCOUNTTOGROUP_EVENT = new Event("AddAccountToGroup", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event ADDFUNCTIONTOGROUP_EVENT = new Event("AddFunctionToGroup", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event REMOVEACCOUNTFROMGROUP_EVENT = new Event("RemoveAccountFromGroup", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event REMOVEFUNCTIONFROMGROUP_EVENT = new Event("RemoveFunctionFromGroup", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected AclManager(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public Boolean containsFunction(String group, String contractAddr, String func) throws
            ContractException {
        final Function function = new Function(FUNC_CONTAINSFUNCTION, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(group), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(contractAddr), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(func)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public Boolean containsAccount(String group, String account) throws ContractException {
        final Function function = new Function(FUNC_CONTAINSACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(group), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public Tuple3<BigInteger, BigInteger, BigInteger> getGroup(String group) throws
            ContractException {
        final Function function = new Function(FUNC_GETGROUP, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(group)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple3<BigInteger, BigInteger, BigInteger>(
                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue());
    }

    public List<CreateGroupEventResponse> getCreateGroupEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CREATEGROUP_EVENT, transactionReceipt);
        ArrayList<CreateGroupEventResponse> responses = new ArrayList<CreateGroupEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CreateGroupEventResponse typedResponse = new CreateGroupEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.group = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.mode = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<AddAccountToGroupEventResponse> getAddAccountToGroupEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDACCOUNTTOGROUP_EVENT, transactionReceipt);
        ArrayList<AddAccountToGroupEventResponse> responses = new ArrayList<AddAccountToGroupEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddAccountToGroupEventResponse typedResponse = new AddAccountToGroupEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.group = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<AddFunctionToGroupEventResponse> getAddFunctionToGroupEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADDFUNCTIONTOGROUP_EVENT, transactionReceipt);
        ArrayList<AddFunctionToGroupEventResponse> responses = new ArrayList<AddFunctionToGroupEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AddFunctionToGroupEventResponse typedResponse = new AddFunctionToGroupEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.contractAddr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.func = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.group = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<RemoveAccountFromGroupEventResponse> getRemoveAccountFromGroupEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REMOVEACCOUNTFROMGROUP_EVENT, transactionReceipt);
        ArrayList<RemoveAccountFromGroupEventResponse> responses = new ArrayList<RemoveAccountFromGroupEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RemoveAccountFromGroupEventResponse typedResponse = new RemoveAccountFromGroupEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.group = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<RemoveFunctionFromGroupEventResponse> getRemoveFunctionFromGroupEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REMOVEFUNCTIONFROMGROUP_EVENT, transactionReceipt);
        ArrayList<RemoveFunctionFromGroupEventResponse> responses = new ArrayList<RemoveFunctionFromGroupEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RemoveFunctionFromGroupEventResponse typedResponse = new RemoveFunctionFromGroupEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.contractAddr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.func = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.group = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static AclManager load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new AclManager(contractAddress, client, credential);
    }

    public static AclManager deploy(Client client, CryptoKeyPair credential) throws
            ContractException {
        return deploy(AclManager.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }

    public static class CreateGroupEventResponse {
        public TransactionReceipt.Logs log;

        public String group;

        public BigInteger mode;
    }

    public static class AddAccountToGroupEventResponse {
        public TransactionReceipt.Logs log;

        public String account;

        public String group;
    }

    public static class AddFunctionToGroupEventResponse {
        public TransactionReceipt.Logs log;

        public String contractAddr;

        public String func;

        public String group;
    }

    public static class RemoveAccountFromGroupEventResponse {
        public TransactionReceipt.Logs log;

        public String account;

        public String group;
    }

    public static class RemoveFunctionFromGroupEventResponse {
        public TransactionReceipt.Logs log;

        public String contractAddr;

        public String func;

        public String group;
    }
}

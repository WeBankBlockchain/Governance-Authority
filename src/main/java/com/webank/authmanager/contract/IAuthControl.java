package com.webank.authmanager.contract;

import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.Bool;
import org.fisco.bcos.sdk.v3.codec.datatypes.Function;
import org.fisco.bcos.sdk.v3.codec.datatypes.Type;
import org.fisco.bcos.sdk.v3.codec.datatypes.TypeReference;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class IAuthControl extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b5061011a806100206000396000f300608060405260043610603f576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063b3a26939146044575b600080fd5b348015604f57600080fd5b5060cb600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505060e5565b604051808215151515815260200191505060405180910390f35b600093925050505600a165627a7a7230582070aabd3636f878448fa87e83511bad6cd52c0f0c6ead74eb989868e32cbf3edb0029"};

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b5061011a806100206000396000f300608060405260043610603f576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806328e9610c146044575b600080fd5b348015604f57600080fd5b5060cb600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505060e5565b604051808215151515815260200191505060405180910390f35b600093925050505600a165627a7a72305820b8d29d8a94d07f372666e269add235485cc669ba1c2ccb5952b8fc0c4a1263ae0029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"conflictFields\":[{\"kind\":5}],\"constant\":true,\"inputs\":[{\"name\":\"contractAddr\",\"type\":\"address\"},{\"name\":\"sig\",\"type\":\"bytes4\"},{\"name\":\"caller\",\"type\":\"address\"}],\"name\":\"canCallFunction\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"selector\":[3013765433,686383372],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_CANCALLFUNCTION = "canCallFunction";

    protected IAuthControl(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public Boolean canCallFunction(String contractAddr, byte[] sig, String caller) throws
            ContractException {
        final Function function = new Function(FUNC_CANCALLFUNCTION, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.Address(contractAddr), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes4(sig), 
                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(caller)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public static IAuthControl load(String contractAddress, Client client,
            CryptoKeyPair credential) {
        return new IAuthControl(contractAddress, client, credential);
    }

    public static IAuthControl deploy(Client client, CryptoKeyPair credential) throws
            ContractException {
        return deploy(IAuthControl.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }
}

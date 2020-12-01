package com.webank.authmanager.contract;

import java.util.Arrays;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class IAuthControl extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b5061011a806100206000396000f300608060405260043610603f576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063b3a26939146044575b600080fd5b348015604f57600080fd5b5060cb600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505060e5565b604051808215151515815260200191505060405180910390f35b600093925050505600a165627a7a723058206a2b424e6f92a79bf9d6c920d045854d52452d87ca6747001f1c11aa978664d20029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b5061011a806100206000396000f300608060405260043610603f576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063b3a26939146044575b600080fd5b348015604f57600080fd5b5060cb600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505060e5565b604051808215151515815260200191505060405180910390f35b600093925050505600a165627a7a723058206a2b424e6f92a79bf9d6c920d045854d52452d87ca6747001f1c11aa978664d20029"};

    public static final String SM_BINARY = String.join("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"contractAddr\",\"type\":\"address\"},{\"name\":\"sig\",\"type\":\"bytes4\"},{\"name\":\"caller\",\"type\":\"address\"}],\"name\":\"canCallFunction\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final String FUNC_CANCALLFUNCTION = "canCallFunction";

    protected IAuthControl(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public Boolean canCallFunction(String contractAddr, byte[] sig, String caller) throws ContractException {
        final Function function = new Function(FUNC_CANCALLFUNCTION, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(contractAddr), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes4(sig), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(caller)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public static IAuthControl load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new IAuthControl(contractAddress, client, credential);
    }

    public static IAuthControl deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(IAuthControl.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}

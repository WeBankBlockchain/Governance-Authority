package com.webank.authmanager.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint16;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint8;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class SingletonVoter extends Contract {
    public static final String[] BINARY_ARRAY = {"60806040526064600081905550610014610112565b604051809103906000f080158015610030573d6000803e3d6000fd5b50600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167fd6d5234051966a63c01d2f905f4d6a60242a27a434cc31f991c190d273b269bb30604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390a2610122565b6040516114308061092083390190565b6107ef806101316000396000f300608060405260043610610078576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630b8160451461007d5780633d829b691461015c578063ac6c5251146101b3578063bc558e8714610221578063d428cffc1461029b578063d77652f7146102f2575b600080fd5b34801561008957600080fd5b506100a8600480360381019080803590602001909291905050506103d4565b604051808881526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018661ffff1661ffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018461ffff1661ffff1681526020018360ff1660ff1681526020018260ff1660ff16815260200197505050505050505060405180910390f35b34801561016857600080fd5b5061017161050b565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101bf57600080fd5b506101f4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610531565b604051808361ffff1661ffff1681526020018261ffff1661ffff1681526020019250505060405180910390f35b34801561022d57600080fd5b5061024c6004803603810190808035906020019092919050505061063e565b604051808360ff1660ff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390f35b3480156102a757600080fd5b506102b061072b565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156102fe57600080fd5b50610320600480360381019080803560ff169060200190929190505050610755565b604051808881526020018773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018661ffff1661ffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018461ffff1661ffff1681526020018360ff1660ff1681526020018260ff1660ff16815260200197505050505050505060405180910390f35b600080600080600080600080600260008a815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508073ffffffffffffffffffffffffffffffffffffffff1663f28676f46040518163ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040160e060405180830381600087803b15801561047a57600080fd5b505af115801561048e573d6000803e3d6000fd5b505050506040513d60e08110156104a457600080fd5b8101908080519060200190929190805190602001909291908051906020019092919080519060200190929190805190602001909291908051906020019092919080519060200190929190505050975097509750975097509750975050919395979092949650565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600080600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ac6c5251846040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019150506040805180830381600087803b1580156105f057600080fd5b505af1158015610604573d6000803e3d6000fd5b505050506040513d604081101561061a57600080fd5b81019080805190602001909291908051906020019092919050505091509150915091565b60008060006002600085815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508073ffffffffffffffffffffffffffffffffffffffff16636900187d6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016040805180830381600087803b1580156106dc57600080fd5b505af11580156106f0573d6000803e3d6000fd5b505050506040513d604081101561070657600080fd5b8101908080519060200190929190805190602001909291905050509250925050915091565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b600080600080600080600080600360008a60ff1660ff168152602001908152602001600020541415151561078857600080fd5b6107aa600360008a60ff1660ff168152602001908152602001600020546103d4565b96509650965096509650965096509193959790929496505600a165627a7a72305820e87b2c6136af68d3201e8f6f495419ffd84a46195084b75188b2e4b41c86793b00296080604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055503073ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fe9babf7227595470b3626ae5ccf58b60155b302e762cffc79c52bfd8a800c53c60405160405180910390a461136b806100c56000396000f300608060405260043610610099576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806313af40351461009e5780631400a813146100e1578063793e64101461014a5780637f3c816014610193578063ac6c5251146101c6578063b2bdfa7b14610234578063d428cffc1461028b578063d58846371461034e578063f22d9bc5146103e8575b600080fd5b3480156100aa57600080fd5b506100df600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506104b7565b005b3480156100ed57600080fd5b50610130600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803561ffff169060200190929190505050610676565b604051808215151515815260200191505060405180910390f35b34801561015657600080fd5b50610179600480360381019080803561ffff16906020019092919050505061080c565b604051808215151515815260200191505060405180910390f35b34801561019f57600080fd5b506101a861091e565b604051808261ffff1661ffff16815260200191505060405180910390f35b3480156101d257600080fd5b50610207600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610932565b604051808361ffff1661ffff1681526020018261ffff1661ffff1681526020019250505060405180910390f35b34801561024057600080fd5b50610249610963565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561029757600080fd5b506102a0610988565b604051808461ffff1661ffff1681526020018060200180602001838103835285818151815260200191508051906020019060200280838360005b838110156102f55780820151818401526020810190506102da565b50505050905001838103825284818151815260200191508051906020019060200280838360005b8381101561033757808201518184015260208101905061031c565b505050509050019550505050505060405180910390f35b34801561035a57600080fd5b506103ce60048036038101908080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290803561ffff169060200190929190803561ffff1690602001909291905050506109be565b604051808215151515815260200191505060405180910390f35b3480156103f457600080fd5b5061049d6004803603810190808035906020019082018035906020019080806020026020016040519081016040528093929190818152602001838360200280828437820191505050505050919291929080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290803561ffff169060200190929190505050610cb2565b604051808215151515815260200191505060405180910390f35b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156105a1576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260268152602001807f57454261736963417574683a206f6e6c79206f776e657220697320617574686f81526020017f72697a65642e000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055503073ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff16","73ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff167fe9babf7227595470b3626ae5ccf58b60155b302e762cffc79c52bfd8a800c53c60405160405180910390a450565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610762576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260268152602001807f57454261736963417574683a206f6e6c79206f776e657220697320617574686f81526020017f72697a65642e000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b61077883836001610ff89092919063ffffffff16565b8273ffffffffffffffffffffffffffffffffffffffff167f2c00e0bbfed7415a2cfaabe8c8c0c4e511702b7ede4a3de9ff3e4352ab2b760d8330604051808361ffff1661ffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a26001905092915050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156108f8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260268152602001807f57454261736963417574683a206f6e6c79206f776e657220697320617574686f81526020017f72697a65642e000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b81600460006101000a81548161ffff021916908361ffff16021790555060019050919050565b600460009054906101000a900461ffff1681565b60008061094983600161119b90919063ffffffff16565b600460009054906101000a900461ffff1691509150915091565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600060608060608061099a6001611229565b91509150600460009054906101000a900461ffff1682829450945094505050909192565b6000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610aab576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260268152602001807f57454261736963417574683a206f6e6c79206f776e657220697320617574686f81526020017f72697a65642e000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b60008551111515610b24576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600c8152602001807f496c6c6567616c2077686f73000000000000000000000000000000000000000081525060200191505060405180910390fd5b60008461ffff16111515610ba0576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600f8152602001807f496c6c6567616c2077656967687421000000000000000000000000000000000081525060200191505060405180910390fd5b82600460006101000a81548161ffff021916908361ffff160217905550600090505b8451811015610ca657610bf88582815181101515610bdc57fe5b90602001906020020151856001610ff89092919063ffffffff16565b8481815181101515610c0657fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff167f2c00e0bbfed7415a2cfaabe8c8c0c4e511702b7ede4a3de9ff3e4352ab2b760d8530604051808361ffff1661ffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a28080600101915050610bc2565b60019150509392505050565b6000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610d9f576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260268152602001807f57454261736963417574683a206f6e6c79206f776e657220697320617574686f81526020017f72697a65642e000000000000000000000000000000000000000000000000000081525060400191505060405180910390fd5b60008551118015610db1575083518551145b1515610e25576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f496c6c6567616c2061727261797300000000000000000000000000000000000081525060200191505060405180910390fd5b82600460006101000a81548161ffff021916908361ffff160217905550600090505b8451811015610fec5760008482815181101515610e6057fe5b9060200190602002015161ffff16111515610ee3576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600f8152602001807f496c6c6567616c2077656967687421000000000000000000000000000000000081525060200191505060405180910390fd5b610f278582815181101515610ef457fe5b906020019060200201518583815181101515610f0c57fe5b906020019060200201516001610ff89092919063ffffffff16565b8481815181101515610f3557fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff167f2c00e0bbfed7415a2cfaabe8c8c0c4e511702b7ede4a3de9ff3e4352ab2b760d8583815181101515610f8457fe5b9060200190602002015130604051808361ffff1661ffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a28080600101915050610e47565b60019150509392505050565b60008360000160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050600081141561115057836001018390806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550508360020182908060018154018082558091505090600182039060005260206000209060109182820401919006600202909192909190916101000a81548161ffff021916908361ffff1602179055505083600101805490508460000160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550611195565b81846002016001830381548110151561116557fe5b90600052602060002090601091828204019190066002026101000a81548161ffff021916908361ffff1602179055505b50505050565b60008060008460000160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054915084600201600183038154811015156111f857fe5b90600052602060002090601091828204019190066002029054906101000a900461ffff169050809250505092915050565b6060808260010183600201818054806020026020016040519081016040528092919081815260200182805480156112b557602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001906001019080831161126b575b505050505091508080548060200260200160405190810160405280929190818152602001828054801561132f57602002820191906000526020600020906000905b82829054906101000a900461ffff1661ffff16815260200190600201906020826001010492830192600103820291508084116112f65790505b50505050509050915091509150915600a165627a7a7230582064214d806962209f2a1a5fce70148272fa10a7da8f7c01ce9359fd8d2223969b0029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"getRequestInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"uint16\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"uint16\"},{\"name\":\"\",\"type\":\"uint8\"},{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_voteWeight\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"who\",\"type\":\"address\"}],\"name\":\"getWeight\",\"outputs\":[{\"name\":\"\",\"type\":\"uint16\"},{\"name\":\"\",\"type\":\"uint16\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\"}],\"name\":\"getRequestTxType\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"},{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getVoteWeight\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"txType\",\"type\":\"uint8\"}],\"name\":\"getRequestSingle\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"uint16\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"uint16\"},{\"name\":\"\",\"type\":\"uint8\"},{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"txType\",\"type\":\"uint8\"}],\"name\":\"ApproveSingle\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"txType\",\"type\":\"uint8\"}],\"name\":\"DeleteSingle\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"voteWeight\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"contractAddress\",\"type\":\"address\"}],\"name\":\"LogSetVoteWeight\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":true,\"name\":\"txType\",\"type\":\"uint8\"},{\"indexed\":true,\"name\":\"requestAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"threshold\",\"type\":\"uint16\"},{\"indexed\":false,\"name\":\"contractAddress\",\"type\":\"address\"}],\"name\":\"LogRegister\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":true,\"name\":\"result\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"contractAddress\",\"type\":\"address\"}],\"name\":\"LogUnregister\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":true,\"name\":\"who\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"b\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"contractAddress\",\"type\":\"address\"}],\"name\":\"LogApprove\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":true,\"name\":\"who\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"b\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"contractAddress\",\"type\":\"address\"}],\"name\":\"LogUnapprove\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":true,\"name\":\"requestAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"contractAddress\",\"type\":\"address\"}],\"name\":\"LogCanCall\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_GETREQUESTINFO = "getRequestInfo";

    public static final String FUNC__VOTEWEIGHT = "_voteWeight";

    public static final String FUNC_GETWEIGHT = "getWeight";

    public static final String FUNC_GETREQUESTTXTYPE = "getRequestTxType";

    public static final String FUNC_GETVOTEWEIGHT = "getVoteWeight";

    public static final String FUNC_GETREQUESTSINGLE = "getRequestSingle";

    public static final Event APPROVESINGLE_EVENT = new Event("ApproveSingle", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>(true) {}));
    ;

    public static final Event DELETESINGLE_EVENT = new Event("DeleteSingle", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
    ;

    public static final Event LOGSETVOTEWEIGHT_EVENT = new Event("LogSetVoteWeight", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event LOGREGISTER_EVENT = new Event("LogRegister", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint8>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint16>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event LOGUNREGISTER_EVENT = new Event("LogUnregister", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Bool>(true) {}, new TypeReference<Address>() {}));
    ;

    public static final Event LOGAPPROVE_EVENT = new Event("LogApprove", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event LOGUNAPPROVE_EVENT = new Event("LogUnapprove", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event LOGCANCALL_EVENT = new Event("LogCanCall", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected SingletonVoter(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SingletonVoter(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SingletonVoter(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SingletonVoter(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger>> getRequestInfo(BigInteger id) {
        final Function function = new Function(FUNC_GETREQUESTINFO, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint16>() {}, new TypeReference<Address>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}));
        return new RemoteCall<Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<String> _voteWeight() {
        final Function function = new Function(FUNC__VOTEWEIGHT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple2<BigInteger, BigInteger>> getWeight(String who) {
        final Function function = new Function(FUNC_GETWEIGHT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(who)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}, new TypeReference<Uint16>() {}));
        return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<Tuple2<BigInteger, String>> getRequestTxType(BigInteger id) {
        final Function function = new Function(FUNC_GETREQUESTTXTYPE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple2<BigInteger, String>>(
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<String> getVoteWeight() {
        final Function function = new Function(FUNC_GETVOTEWEIGHT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger>> getRequestSingle(BigInteger txType) {
        final Function function = new Function(FUNC_GETREQUESTSINGLE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint8(txType)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint16>() {}, new TypeReference<Address>() {}, new TypeReference<Uint16>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}));
        return new RemoteCall<Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<BigInteger, String, BigInteger, String, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    public List<ApproveSingleEventResponse> getApproveSingleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVESINGLE_EVENT, transactionReceipt);
        ArrayList<ApproveSingleEventResponse> responses = new ArrayList<ApproveSingleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApproveSingleEventResponse typedResponse = new ApproveSingleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.txType = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerApproveSingleEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(APPROVESINGLE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerApproveSingleEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(APPROVESINGLE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<DeleteSingleEventResponse> getDeleteSingleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DELETESINGLE_EVENT, transactionReceipt);
        ArrayList<DeleteSingleEventResponse> responses = new ArrayList<DeleteSingleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DeleteSingleEventResponse typedResponse = new DeleteSingleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.txType = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerDeleteSingleEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(DELETESINGLE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerDeleteSingleEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(DELETESINGLE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<LogSetVoteWeightEventResponse> getLogSetVoteWeightEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGSETVOTEWEIGHT_EVENT, transactionReceipt);
        ArrayList<LogSetVoteWeightEventResponse> responses = new ArrayList<LogSetVoteWeightEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogSetVoteWeightEventResponse typedResponse = new LogSetVoteWeightEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voteWeight = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.contractAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerLogSetVoteWeightEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGSETVOTEWEIGHT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerLogSetVoteWeightEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGSETVOTEWEIGHT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<LogRegisterEventResponse> getLogRegisterEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGREGISTER_EVENT, transactionReceipt);
        ArrayList<LogRegisterEventResponse> responses = new ArrayList<LogRegisterEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogRegisterEventResponse typedResponse = new LogRegisterEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.txType = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.requestAddress = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.threshold = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.contractAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerLogRegisterEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGREGISTER_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerLogRegisterEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGREGISTER_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<LogUnregisterEventResponse> getLogUnregisterEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGUNREGISTER_EVENT, transactionReceipt);
        ArrayList<LogUnregisterEventResponse> responses = new ArrayList<LogUnregisterEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogUnregisterEventResponse typedResponse = new LogUnregisterEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.result = (Boolean) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.contractAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerLogUnregisterEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGUNREGISTER_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerLogUnregisterEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGUNREGISTER_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<LogApproveEventResponse> getLogApproveEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGAPPROVE_EVENT, transactionReceipt);
        ArrayList<LogApproveEventResponse> responses = new ArrayList<LogApproveEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogApproveEventResponse typedResponse = new LogApproveEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.who = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.b = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.contractAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerLogApproveEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGAPPROVE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerLogApproveEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGAPPROVE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<LogUnapproveEventResponse> getLogUnapproveEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGUNAPPROVE_EVENT, transactionReceipt);
        ArrayList<LogUnapproveEventResponse> responses = new ArrayList<LogUnapproveEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogUnapproveEventResponse typedResponse = new LogUnapproveEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.who = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.b = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.contractAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerLogUnapproveEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGUNAPPROVE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerLogUnapproveEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGUNAPPROVE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<LogCanCallEventResponse> getLogCanCallEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGCANCALL_EVENT, transactionReceipt);
        ArrayList<LogCanCallEventResponse> responses = new ArrayList<LogCanCallEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogCanCallEventResponse typedResponse = new LogCanCallEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.requestAddress = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.contractAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerLogCanCallEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGCANCALL_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerLogCanCallEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGCANCALL_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static SingletonVoter load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SingletonVoter(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SingletonVoter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SingletonVoter(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SingletonVoter load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SingletonVoter(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SingletonVoter load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SingletonVoter(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SingletonVoter> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SingletonVoter.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SingletonVoter> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SingletonVoter.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SingletonVoter> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SingletonVoter.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SingletonVoter> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SingletonVoter.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ApproveSingleEventResponse {
        public Log log;

        public BigInteger txType;
    }

    public static class DeleteSingleEventResponse {
        public Log log;

        public BigInteger txType;
    }

    public static class LogSetVoteWeightEventResponse {
        public Log log;

        public String voteWeight;

        public String contractAddress;
    }

    public static class LogRegisterEventResponse {
        public Log log;

        public BigInteger id;

        public BigInteger txType;

        public String requestAddress;

        public BigInteger threshold;

        public String contractAddress;
    }

    public static class LogUnregisterEventResponse {
        public Log log;

        public BigInteger id;

        public Boolean result;

        public String contractAddress;
    }

    public static class LogApproveEventResponse {
        public Log log;

        public BigInteger id;

        public String who;

        public Boolean b;

        public String contractAddress;
    }

    public static class LogUnapproveEventResponse {
        public Log log;

        public BigInteger id;

        public String who;

        public Boolean b;

        public String contractAddress;
    }

    public static class LogCanCallEventResponse {
        public Log log;

        public BigInteger id;

        public String requestAddress;

        public String contractAddress;
    }
}

package com.webank.authmanager;

import com.webank.authmanager.contract.AuthManager;
import com.webank.authmanager.factory.AuthManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class App  {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }

}

















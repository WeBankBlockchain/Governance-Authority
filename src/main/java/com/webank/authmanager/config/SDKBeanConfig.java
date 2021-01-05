package com.webank.authmanager.config;

import com.webank.authmanager.utils.HashUtils;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aaronchu
 * @Description
 * @data 2020/11/30
 */
@Slf4j
@Configuration
public class SDKBeanConfig {
    @Autowired
    private SystemEnvironmentConfig systemEnvironmentConfig;


    @Bean
    public CryptoKeyPair cryptoKeyPair(Client client) throws ConfigException {
        return client.getCryptoSuite().getCryptoKeyPair();
    }

    @Bean
    public Client getClient(BcosSDK bcosSDK) throws ConfigException {
        Client c =  bcosSDK.getClient(systemEnvironmentConfig.getGroupId());
        HashUtils.setHash(c.getCryptoSuite().hashImpl);
        return c;
    }

    @Bean
    public BcosSDK getSDK() throws ConfigException {
        return BcosSDK.build(systemEnvironmentConfig.getSdkConfigPath());
    }
}

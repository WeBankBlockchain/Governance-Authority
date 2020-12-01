package com.webank.authmanager.config;

import com.webank.authmanager.utils.HashUtils;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.config.ConfigOption;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.config.model.AccountConfig;
import org.fisco.bcos.sdk.config.model.ConfigProperty;
import org.fisco.bcos.sdk.crypto.hash.Keccak256;
import org.fisco.bcos.sdk.crypto.hash.SM3Hash;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostConstruct
    public void init(){
        if(systemEnvironmentConfig.getEncryptType() == 0){
            HashUtils.setHash(new Keccak256());
        }
        else{
            HashUtils.setHash(new SM3Hash());
        }
    }

    @Bean
    public CryptoKeyPair cryptoKeyPair() throws ConfigException {
        Client client = getClient();
        return client.getCryptoSuite().createKeyPair();
    }

    @Bean
    public Client getClient() throws ConfigException {
        BcosSDK sdk = getSDK();
        return sdk.getClient(systemEnvironmentConfig.getGroupId());
    }


    @Bean
    public BcosSDK getSDK() throws ConfigException {
        ConfigProperty configProperty = new ConfigProperty();
        setPeers(configProperty);
        setCertPath(configProperty);
        setAccount(configProperty);
        ConfigOption option =
                new ConfigOption(configProperty, systemEnvironmentConfig.getEncryptType());
        log.info("Is gm {}", systemEnvironmentConfig.getEncryptType() == 1);
        return new BcosSDK(option);
    }

    private void setAccount(ConfigProperty configProperty) throws ConfigException {
        Map<String, Object> account = new HashMap<>();
        account.put("accountFilePath",systemEnvironmentConfig.getPrivateKeyPem());

        configProperty.setAccount(account);
    }

    public void setPeers(ConfigProperty configProperty) {
        String[] nodes = systemEnvironmentConfig.getNodeStr().split(";");
        List<String> peers = Arrays.asList(nodes);
        Map<String, Object> network = new HashMap<>();
        network.put("peers", peers);
        configProperty.setNetwork(network);
    }

    public void setCertPath(ConfigProperty configProperty) {
        Map<String, Object> cryptoMaterial = new HashMap<>();
        cryptoMaterial.put("certPath", systemEnvironmentConfig.getCertPath());
        configProperty.setCryptoMaterial(cryptoMaterial);
    }
}

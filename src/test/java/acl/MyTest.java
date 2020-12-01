package acl;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.response.NodeIDList;
import org.fisco.bcos.sdk.config.Config;
import org.fisco.bcos.sdk.config.ConfigOption;
import org.junit.Test;

/**
 * @author aaronchu
 * @Description
 * @data 2020/11/30
 */
public class MyTest {

    @Test
    public void hi() throws Exception {
        /*
        //配置信息包括：1)节点channel列表、2)证书列表 3)个人私钥
        ConfigOption configOption
                = Config.load("src/main/resources/config-example.toml");
        BcosSDK bcosSDK = new BcosSDK(configOption);
        Client client = bcosSDK.getClient(1);

        System.out.println(client.getCryptoSuite().getCryptoKeyPair().getHexPrivateKey());
        System.out.println(client.getCryptoSuite().getCryptoKeyPair().getHexPublicKey());
        System.out.println(client.getCryptoSuite().getCryptoKeyPair().getAddress());

        //如何从非resource的方式完成配置？
        System.out.println(configOption.getNetworkConfig().getPeers());
        System.out.println(configOption.getCryptoMaterialConfig().getCertPath());

        System.out.println(configOption.getAccountConfig().getKeyStoreDir());
        System.out.println(configOption.getAccountConfig().getAccountAddress());
        System.out.println(configOption.getAccountConfig().getAccountFileFormat());

        System.out.println(configOption.getAccountConfig().getAccountPassword());
        System.out.println(configOption.getAccountConfig().getAccountFilePath());
*/
    }
}

# 组件介绍

随着智能合约业务日益丰富，越来越多的合约需要引入权限控制。如果不对智能合约做权限控制，那么无法满足业务的安全性要求。例如，存证场景中，除了上传存证的函数外，还有许多专供审核人员调用的函数，这些函数应仅由审核人员来使用，如果这些函数没有正确设置权限拦截逻辑，整套逻辑就会被攻击者轻易操控。

权限治理组件的目的在于为智能合约开发者提供权限控制功能。开发者只要添加少量代码，即可拦截非法调用。同时，有一个专门的权限治理合约用于治理各个业务合约的拦截规则，对规则的修改只需操作权限治理合约，不需要调整业务合约，且修改会实时生效。

## 关键特性

    - 函数级的权限粒度
    - 批量设置用户权限
    - 侵入性低
    - 易于集成
    - 支持可插拔的设计，对业务侵入小
    - 支持多种治理方式

## 环境要求

在使用本组件前，请确认系统环境已安装相关依赖软件，清单如下：

| 依赖软件   | 说明                                                         | 备注 |
| ---------- | ------------------------------------------------------------ | ---- |
| FISCO-BCOS       | >= 2.0 |      |
| Java       | \>= JDK[1.8]                                                 |      |
| Git        | 下载的安装包使用Git                                          |      |


## 文档
- [**中文**](https://gov-doc.readthedocs.io/zh_CN/latest/docs/WeBankBlockchain-Gov-Auth/index.html)
- [**快速开始**](https://gov-doc.readthedocs.io/zh_CN/latest/docs/WeBankBlockchain-Gov-Auth/quickstart.html)


## 贡献代码vv
欢迎参与本项目的社区建设：
- 如项目对您有帮助，欢迎点亮我们的小星星(点击项目左上方Star按钮)。
- 欢迎提交代码(Pull requests)。
- [提问和提交BUG](https://github.com/WeBankBlockchain/WeBankBlockchain-Governance-Authority/issues)。
- 如果发现代码存在安全漏洞，请在[这里](https://security.webank.com)上报。


![](https://media.githubusercontent.com/media/FISCO-BCOS/LargeFiles/master/images/QR_image.png)


## License
![license](http://img.shields.io/badge/license-Apache%20v2-blue.svg)

开源协议为[Apache License 2.0](http://www.apache.org/licenses/). 详情参考[LICENSE](../LICENSE)。


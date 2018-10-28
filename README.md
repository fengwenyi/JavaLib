[![](https://jitpack.io/v/fengwenyi/JavaLib.svg)](https://jitpack.io/#fengwenyi/JavaLib)

目前正在更新1.0.5.RELEASE，敬请期待

# 开发者工具类库——JavaLib

### 功能

JavaLib，是一个Java开发基础工具类库，对项目开发中常用的工具进行封装，如：加密、http请求、API接口。

目的是帮助开发者更快速、更快捷的开发。

无侵入性，轻量级，常用功能，无数次测试，不断完善

* SSLClient          SSL Client

* ICallback          回调接口

* IReturnCode        返回码接口

* Result             API返回封装

* Base64             Base64

* Constant           常量接口（值）

* FileUtil           文件操作工具类

* HexUtil            进制转换工具类

* HttpsClientUtil    Https 请求工具类

* HttpUtil           Http 请求工具类

* MathUtil           Math 工具类

* RequestUtil        Request 工具类

* RSAUtil            RSA 加密解密签名与验证工具类

* SafeUtil           加密工具类

* StringUtil         字符串工具类

* TimeUtil           时间工具类

* ToastUtil          打印工具类

* Utils              常用工具类

* 消息引擎

* aop模块


### 目录结构

```
JavaLib
├── src
│   ├── main
│   │   └── java
│   │       └──com
│   │          └── fengwenyi
│   │              └── javalib
│   │                  ├── aop
│   │                  │   └── LBaseWebLogAspect
│   │                  ├── handler
│   │                  │   ├── Handler                           // handler
│   │                  │   └── HandlerRegister                   // handler注册
│   │                  ├── https
│   │                  │   └── SSLClient                         // SSL Client
│   │                  ├── jk
│   │                  │   └── ICallback                         // 回调接口
│   │                  ├── messageengine
│   │                  │   └── CommonMessage                     // 通用消息组件
│   │                  ├── result
│   │                  │   ├── DefaultResult
│   │                  │   ├── DefaultReturnCode
│   │                  │   ├── ResultResponseUtil
│   │                  │   ├── IReturnCode                       // 返回码接口
│   │                  │   └── Result                            // Result
│   │                  └── util
│   │                      ├── Base64                            // Base64
│   │                      ├── Constant                          // 常量接口（值）
│   │                      ├── FileUtil                          // File 工具类
│   │                      ├── HexUtil                           // Hex 工具类
│   │                      ├── HttpsClientUtil                   // Https 请求工具类
│   │                      ├── HttpUtil                          // Http 请求工具类
│   │                      ├── MathUtil                          // Math 工具类
│   │                      ├── RequestUtil                       // Request 工具类
│   │                      ├── RSAUtil                           // RSA 加密解密签名与验证工具类
│   │                      ├── SafeUtil                          // Safe 工具类
│   │                      ├── StringUtil                        // String 工具类
│   │                      ├── TimeUtil                          // Time 工具类
│   │                      ├── ToastUtil                         // Time 工具类
│   │                      └── Utils                             // 常用工具类
│   └── test
│       └── java
│           └──com
│              └── fengwenyi
│                  └── test
│                      ├── INetCallback                         // 继承 ICallback
│                      ├── MyTest                               // Test
│                      ├── ResultTest                           // Result
│                      └── TestSign                             // Test RSA 签名与验证
└── pom.xml                                                     // Maven 配置

```

**`Module资料`**

|  GroupId  | com.fengwenyi|
|   ---     | ---|
| ArtifactId| JavaLib|

**`仓库`**

[中央仓库](https://search.maven.org/search?q=g:com.fengwenyi%20AND%20a:JavaLib&core=gav) 
| 
[JitPack](https://jitpack.io/#fengwenyi/JavaLib)


### Blog

* [JavaLib | Result让你的API接口统一化（2）](https://www.jianshu.com/p/2e466db0907c)

* [JavaLib | RSAUtil非对称加密工具类](https://www.jianshu.com/p/bdf59b6a01ab)



### About Me

```
    ©author Wenyi Feng
```

### Licensed

```
   Copyright 2017-2018 Wenyi Feng(xfsy2014@gmail.com)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
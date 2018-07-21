# 开发者工具类库——JavaLib

正如名字所说，他——JavaLib是一个Java开发工具类库，目的是帮助开发者更快速、更快捷的开发。

无侵入性，轻量级，常用功能，无数次测试，不断完善

### 功能


* 回调接口

* Https 请求

* API 返回结果统一化

* Base 64 加密

* 文件操作工具

* 进制转换工具

* 请求数据处理

* 常用加密工具

* 时间处理工具


### 目录结构

```
JavaLib
├── src
│   ├── main
│   │   └── java
│   │       └──com
│   │          └── fengwenyi
│   │              └── javalib
│   │                  ├── https
│   │                  │   └── SSLClient                         // SSL Client
│   │                  ├── jk
│   │                  │   └── ICallback                         // 回调接口
│   │                  ├── result
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

### 快速开始

从21版本开始，jar、pom、doc都托管的[中央仓库](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.fengwenyi%22)，所以，你可以通过中央仓库实现依赖。

由于其他仓库（如阿里云Maven仓库）同步的时间比较慢，如果你的Maven仓库地址修改过的话，可能依赖失败。

**`Module资料`**

|  GroupId  | com.fengwenyi|
|   ---     | ---|
| ArtifactId| JavaLib|

**`版本信息`**

| Version | 说明 |
|  ---    | --- |
|  0.0.9  | 发布版 |
|  1.0.0.beta.1  | 1.0.0 测试版 |

**`依赖`**

```xml
<dependency>
    <groupId>com.fengwenyi</groupId>
    <artifactId>JavaLib</artifactId>
    <version>1.0.0.beta.1</version>
</dependency>
```


### API

* API.md 只适用于 20 版本，从 21 版本（版本号为 0.0.8）开始，请使用JavaLib-doc

* [JavaLib-doc](https://javalib-doc.fengwenyi.com)

* JavaLib中用到以下依赖：

|groupId                   |artifactId           |version  |
|:----                     |:---:                 |:---:     |
|org.apache.tomcat         |servlet-api          |6.0.53   |
|com.google.code.gson      |gson                 |2.8.1    |
|org.apache.httpcomponents |httpclient           |4.2      |
|junit                     |junit                |4.10     |


### Blog

* **[JavaLib | result 模块](https://www.jianshu.com/p/03c8c95bd661)**

### About Me

```
©author Wenyi Feng
```

### Licensed

```
   Copyright 2017-2018 Wenyi Feng

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
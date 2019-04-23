[![](https://jitpack.io/v/fengwenyi/JavaLib.svg)](https://jitpack.io/#fengwenyi/JavaLib)

<h1 align="center">JavaLib</h1>

## JavaLib是什么？

JavaLib，是一个Java开发基础工具类库，对项目开发中常用的工具进行封装，如：加密、http请求、API接口。

目的是帮助开发者更快速、更快捷的开发。

无侵入性，轻量级，常用功能，无数次测试，不断完善

## JavaLib怎么用？

## JavaLib性能如何？

### 功能

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


```
JavaLib
├── bak  备份
├── images 存放图片
│   ├── icon 图标
│   └── screenshot 截图
├── src
│   ├── main
│   │   └── java
│   │       └──com
│   │          └── fengwenyi
│   │              └── javalib 代码
│   └── test
│       └── java
│           └──com
│              └── fengwenyi
│                  └── javalib  测试
├── API.md  wiki文档
├── LICENSE 
├── pom.xml  Maven依赖配置
├── README.md 说明
└── VERSION 版本更新记录

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

### 更新日志

* 2019年01月24日（V1.0.5.3.SNAPSHOT）

```$xslt
   1、修复MD5加密问题。
   
   加密不正确。
   
   之前采用32位大写，现在采用32位小写。
   
   另外，提供MD5加密解密功能。
   
   2、进制转换问题
   
   修复2进制转16进制不正确。
   
   另外，提供16进制转2进制。 
```  

* 2019年01月22日（V1.0.5.2.1.SNAPSHOT）

```$xslt
    去掉Http请求工具类，作者以后都用OkHttp了。
    
    重写Result，更简洁、更方便、更优雅。
    
    重新规划CodeMsg，采用class的方式。
    
    JSON由Gson变为fastjson。
```  

### About Me

```
    ©author Erwin Feng
```

### Licensed

```
   Copyright 2017-2019 Erwin Feng(xfsy_2015@163.com)

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
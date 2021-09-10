
<h1 align="center">
    JavaLib
</h1>

<p align="center">
	<strong>JAVA开发，常用工具集</strong>
</p>

<p align="center">
	<a target="_blank" href="https://www.apache.org/licenses/LICENSE-2.0.html">
		<img src="https://img.shields.io/:license-apache-blue.svg" ></img>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
	</a>
	<a target="_blank" href='https://github.com/fengwenyi/JavaLib'>
		<img src="https://img.shields.io/github/stars/fengwenyi/JavaLib.svg?style=social" alt="github star"></img>
	</a>
</p>

## 概述

JavaLib，是一个Java开发基础工具类库，对项目开发中常用的工具进行封装，如：加密、http请求、API接口等。
目的是帮助开发者更快速、更快捷的开发。

目标：`无侵入性`，`轻量级`，`常用功能`，`无数次测试`，`不断完善`


> 注意：JavaLib基于jdk8开发，如果你的jdk版本过低，请找到相应的工具类，复制相关代码，进行使用。

## 快速开始

**Maven**

```xml
<dependency>
    <groupId>com.fengwenyi</groupId>
    <artifactId>JavaLib</artifactId>
    <version>2.1.5</version>
</dependency>
```

**Gradle**

```groovy
implementation 'com.fengwenyi:JavaLib:2.1.5'
```

## 功能列表：

| 名称 | 描述 | 发布版本 | 发布日期 |
| --- | ---  | :---: | :---: |
| DateTimeUtils | 日期时间工具类  | - | 2020.04.30 |
| JsonUtils | JSON转换工具类  | - | 2020.08.22 |
| XmlUtils | XML转换工具类  | - | 2020.08.22 |
| BeanUtils | Bean工具类  | - | 2020.08.22 |
| CollectionUtils | 集合工具类  | - | 2020.08.22 |
| MapUtils | Map工具类  | - | 2020.08.22 |
| RSAUtils | RSA加/解密工具类  | - | 2020.08.22 |
| MD5Utils | MD5加/解密工具类  | - | 2020.08.22 |
| IdUtils | Id工具类  | - | 2020.08.22 |
| StringUtils | 字符串工具类  | - | 2020.08.22 |
| PrintUtils | 控制台打印工具类  | - | 2020.08.22 |
| ICallback | 回调接口  | - | 2020.4.30 |
| StarHandleUtils | 星号处理工具类  | v2.1.2 | 2021.01.12 |
| MdcUtils | MDC工具类  | v2.1.3 | 2021.08.16 |
| TraceIdUtils | traceId工具类  | v2.1.3 | 2021.08.16 |
| IBuilder | 构造者接口  | v2.1.4 | 2021.08.24 |


## 文档

- [wiki](https://github.com/fengwenyi/JavaLib/wiki)
- [中央仓库](https://search.maven.org/artifact/com.fengwenyi/JavaLib)
- [更新日志](LOG.md)


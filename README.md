
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

添加如下依赖：

```xml
<dependency>
    <groupId>com.fengwenyi</groupId>
    <artifactId>JavaLib</artifactId>
    <version>2.1.1</version>
</dependency>
```

即可使用提供的工具方法。

> 如果依赖拉取失败，请检查maven仓库地址是否是从中央仓库下载。

## 功能列表：

- [x] DateTimeUtils - 日期时间工具类（2020.4.30）
- [x] JsonUtils - JSON转换工具类。（2020.08.22）
- [x] XmlUtils - XML转换工具类。（2020.08.22）
- [x] BeanUtils - Bean工具类。（2020.08.22）
- [x] CollectionUtils - 集合工具类。（2020.08.22）
- [x] MapUtils - Map工具类。（2020.08.22）
- [x] RSAUtils - RSA加/解密工具类。（2020.08.22）
- [x] MD5Utils - MD5加/解密工具类。（2020.08.22）
- [x] IdUtils - Id工具类。（2020.08.22）
- [x] StringUtils - String工具类。（2020.08.22）
- [x] PrintUtils - 控制台打印工具类。（2020.08.22）
- [x] StarHandleUtils - 星号处理工具类。【2021.01.12】

## Wiki

[中文文档](https://github.com/fengwenyi/JavaLib/wiki)
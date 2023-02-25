# 更新日志

格式：

```
:star: Feature

:bug: Bug
```

## v2.2.5



## v2.2.4

- 支持使用 OkHttp 发起请求 
- 弃用 `com.fengwenyi.javalib.third.Base64`
- RSAUtils 中使用 `com.fengwenyi.javalib.third.Base64` 都进行替换



## v2.2.3

:bug: Bug Fixes

- 修复 `DateTimeUtils.toInstant(Date)` 可能出现报错的问题。


## v2.2.2

2022.11.17 ~

:star: Features

- 【http】新增 http 模块，提供Http调用工具类 HttpUtils。
- 【util】新增 JsoupUtils，从 html 中解析标题。



## v2.2.1

2022.09.24 ~ 2022.11.15

:star: Features

- 【DateTimeUtils】新增判断时间是否在指定时间区间内，另外，提供了判断含边界的方法。
- 【DateTimeUtils】新增 `java.time.LocalDateTime` 和 `java.time.LocalDate` 转 `java.util.Date` 的方法。

:arrow_up: Dependency Upgrades

- Upgrade to Jackson 2.14.0
- Upgrade to maven-javadoc-plugin 3.4.1
- Upgrade to slf4j-api 2.0.3
- Upgrade to transmittable-thread-local 2.14.2


## v2.2.0

2022.08.28 ~ 2022.09.18

:star: Features

- 【DateTimeUtils】新增获取自然周的开始时间
- 【DateTimeUtils】新增获取月的开始时间
- 【新增】MapHelper，方便构建 `Map`


## v2.1.8

2022.07.22 ~ 2022.08.20

:star: New Features

- DateTimeUtils 增加 `Long toMillisecond(LocalDate localDate)` 方法
- CharsetConstant 标记过时
- 优化 Jackson Mapper 属性设置
- Jackson Json 改用 `JsonMapper`

:bug: Bug Fixes

- 修复 Jackson 在版本较低时，可能出现大小写重名的问题



## v2.1.7

2022.05.05 ~ 2022.07.14

:star: New Features

- JsonUtils 提供 TypeReference 参数转换方法
- XmlUtils 提供 TypeReference 参数转换方法
- JsonUtils 提供 mapper 常规配置
- XmlUtils 提供 mapper 常规配置
- DateTimeUtils 提供 日期字符串转换成 LocalDate 方法

:arrow_up: Dependency Upgrades

- Upgrade to Jackson 2.13.3
- Upgrade to thumbnailator 0.14.7
- Upgrade to slf4j-api 0.14.7
- Upgrade to transmittable-thread-local 2.12.6
- Upgrade to transmittable-thread-local 2.12.6
- Upgrade to maven-javadoc-plugin 3.4.0



## v2.1.6

2021.12.08 ~ 2022.05.05

:star: Feature

- 新增获取年的方法：DateTimeUtils.getYear()
- DateTimeUtils 转换成时间戳 增加对 `null` 的处理


## v2.1.5

2021.12.03

- 【新增】新增命名工具类，提供下划线和驼峰命名转换方法。
- 【新增】新增DateTimePattern常量类。
- 【新增】新增FormatterUtils工具类。
- 【新增】新增发布 `shell` 命令。
- 【修复】修复打包插件依赖报错的问题。
- 【优化】JsonUtils，优化对日期时间的规范化处理。
- 【升级】fasterxml.jackson -> 2.13.0。
- 【升级】google-thumbnailator -> 0.4.14。
- 【升级】transmittable-thread-local -> 2.12.2。
- 【升级】maven-source-plugin -> 3.2.1。
- 【升级】maven-javadoc-plugin -> 3.3.1。
- 【升级】maven-gpg-plugin -> 3.0.1。

## v2.1.4

2021.08.25

- 【新增】新增构造者接口：IBuilder
- 【优化】对README中功能列表部分进行了优化
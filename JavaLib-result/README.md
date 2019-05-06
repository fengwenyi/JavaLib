
<h1 align="center">JavaLib result</h1>

[JavaLib-result](./)是开源工具[JavaLib](https://github.com/fengwenyi/JavaLib)的子模块，主要是对接口结果规范处理。他是在实际应用环境下产生了，并不断更新和完善，当然这只是我个人提出的解决方案。不足之处，还请多多指正。

## 快速开始

#### 添加依赖

因为这个jar目前没有提交到中央仓库，所以，你需要指定一下仓库的位置

我们在 `pom.xml` 文件中添加如下配置：

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

然后，我们就可以引入包了：

```xml
<dependencies>
    <!-- 添加 JavaLib-result 支持 -->
    <dependency>
        <groupId>com.github.fengwenyi</groupId>
        <artifactId>JavaLib-result</artifactId>
        <version>V1.1.0.Beta</version>
    </dependency>
</dependencies>
```

完整版如下（基于Spring Boot 2.1.3.RELEASE搭建）：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.3.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.fengwenyi</groupId>
    <artifactId>javalib-result-demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>javalib-result-demo</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- 添加 JavaLib-result 支持 -->
        <dependency>
            <groupId>com.github.fengwenyi</groupId>
            <artifactId>JavaLib-result</artifactId>
            <version>V1.1.0.Beta</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

</project>
```

让 Maven 去仓库拉取文件，拉取完毕，如果不报错，就说明依赖成功，就可以正常使用了。

#### 测试

我们来写一个例子：

首先在启动类（xxxApplication）上添加注解： `@RestController` 指明，这个类中的方法将字符串返回给页面。

然后，我们写一个测试的方法：

```java
@RequestMapping("/hello")
public Result hello() {
    return Result.success("Hello");
}
```

测试：访问页面 http://localhost:8080/hello

返回：

```json
{
    "code": 0,
    "msg": "Success",
    "data": "Hello"
}
```

到此就算成功了。更多用法，请参考[测试代码](https://github.com/fengwenyi/JavaLib-result/blob/master/src/test/java/com/fengwneyi/javalib/result/TestResult.java)。


## 源码地址

https://github.com/fengwenyi/JavaLib-result

## 当前最新版本

[![](https://jitpack.io/v/fengwenyi/JavaLib-result.svg)](https://jitpack.io/#fengwenyi/JavaLib-result)

## 作者

我是冯文议，英文名：Erwin Feng。

2017年毕业于阿坝师范学院计算机应用专业，主修Java方向。

有2年工作经验，现就职于深圳警圣技术股份有限公司，主要负责服务器接口开发工作。

开源软件：JavaLib。

## 链接

- [个人网站](https://fengwenyi.com)

- [我的简书](https://www.jianshu.com/u/c1a1f1fefc78)

- [SegmentFault](https://segmentfault.com/u/fengwenyi)

- [CSDN](https://blog.csdn.net/qq_28336351)

- [开源中国-个人博客](https://my.oschina.net/fengwenyi)

- [慕课-手记](https://www.imooc.com/u/2815937)

- 邮箱：xfsy_2015@163.com

## Lisence

Lisenced under [Apache 2.0 lisence](https://opensource.org/licenses/Apache-2.0)
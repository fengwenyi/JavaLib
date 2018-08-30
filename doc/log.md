# 关于日志

### 日志变量定义的问题：

在Android中，日志用Log

在Log4j中，很多人用logger，我估计是怕变量重复

在@Slf4j(lombok)中，用log

看了一下，我决定还是用 `log`

### 日志初始化的问题

目前日志初始化的方式有这样三种（我这里选用slf4j框架）
```java
// 第一种，使用lombok插件
@Slf4j

// 第二种
protected final Logger log 
= LoggerFactory.getLogger(this.getClass());

// 第三种
protected static final Logger log 
= LoggerFactory.getLogger(LBaseWebLogAspect.class);
```

为什么要讨论呢？

有这样两个方面的原因：

一是，如果使用静态变量，那么在任何方法中，都可以使用（该类中的静态方法或是非静态方法），另外，静态变量也是相当比较安全的。

二是，第1，3种，日志打印的名字是 `LBaseWebLogAspect`,也就是定义变量的类名，这不利于我们查找日志。


最后，你是否有注意到我前面的 `protected`，是的，你继承该类后，如果你需要记录日志，那么，你就可以直接使用 `log`这个变量了。
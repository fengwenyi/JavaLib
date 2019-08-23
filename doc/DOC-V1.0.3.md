# DOC V1.0.0

## 控制台打印工具类(PrintUtils)

`(Boolean) DEBUG`：控制是否打印，默认true，打印

`info(Object)`：打印对象

`info()`：换行

## ID生成工具类(IdUtils)

`getIdByUUID()`：通过UUID生成Id，无`-`

`getUUID()`：UUID，有`-`

## AES加密/解密工具类(AESUtils)

`String encrypt(String content, String password)`：加密方法，参数为加密内容和密码，返回类型：String

`String decrypt(String content, String password)`：解密方法，参数为解密内容和密码，返回类型：String

## 集合工具类(CollectionUtils)

`boolean isEmpty(Collection<?> collection)`：判断集合是否为空

`boolean isNotEmpty(Collection<?> collection)`：判断集合是否非空

## 日期时间工具类(DateTimeUtils)

`Date parse(String source, String format)`: 将时间字符串解析成时间类型

`Long toLong(Date date)`: 获取毫秒数

`String format(Object source, String format)`：时间格式化成时间字符串

`Integer getYear(Date date)`：获取年

`Integer getMonth(Date date)`：获取月

`Integer getDay(Date date)`：获取日

`String descPastTime(Date source)`：自然语言描述时间过去多久了

`long differMillis(Instant start, Instant end)`: 计算两个时间点相差多少毫秒

`long differSeconds(Instant start, Instant end)`: 计算两个时间点相差多少秒

`long differMinutes(Instant start, Instant end)`: 计算两个时间点相差多少分钟

`long differHours(Instant start, Instant end)`: 计算两个时间点相差多少小时

`long differDays(Instant start, Instant end)`: 计算两个时间点相差多少天

`String format(Instant instant, String format)`: 时间点格式化

`Instant parseDate(String source)`: 日期字符串转化为Instant

`Instant parseDateTime(String source, String format)`: 将 时间字符串 转为 Instant

## 进制转换工具类(HexUtils)

`String _2_16(byte[] bytes)`: 二进制转十六进制

`byte[] _16_2(String hexStr)`：16进制转化为 2进制

## IP工具类(IpUtils)

`String getPosition(String ip)`：通过ip获取位置

## Map工具类(MapUtils)

`boolean isEmpty(Map<?, ?> map)`: 判断Map是否为空

`boolean isNotEmpty(Map<?, ?> map)`: 判断Map是否非空

## 数学工具类(MathUtils)

`double randomNum(int range)`: 在[0, range)范围内产生一个随机数

`double randomNum(int x, int y)`: 在`[y, x]`范围内产生一个随机数

## MD5工具类(MD5Utils)

`String md5(String plainText)`: MD5加密

`String convert(String str)`: 加密解密算法 执行一次加密，两次解密

## 参数工具类(ParamUtils)

`String getUrlParamsByMap(Map<String, String> data)`: 将Map型转为请求参数型

`Map<String, String> getUrlParams(String param)`: 将url参数转换成map

## 请求工具类(RequestUtils)

`String getRequestIp(HttpServletRequest request)`：获取客户端IP地址

`String getPostData(HttpServletRequest request)`: 如果客户端通过 request 传递数据，那么就可以使用该方法获取数据

## RSA加密解密工具类(RSAUtils)

`String[] getKey(int keySize)`: 获取密钥，`[0]`:私钥，`[1]`:公钥

`String[] getKey()`: 获取密钥，`[0]`:私钥，`[1]`:公钥

`String privateKeyEncrypt(String key, String plainText)`：私钥加密

`publicKeyDecrypt(String key, String cipherText)`：公钥解密

`publicKeyEncrypt(String key, String plainText)`：公钥加密

`privateKeyDecrypt(String key, String cipherText)`：私钥解密

`String sign(String content, String algorithm, String privateKey, String charset)`：签名

`verify(String content, String signature, String algorithm, String publicKey, String charset)`：验证签名

## 字符串工具类(StringUtils)

`boolean isEmpty(String str)`: 判断字符串是否为空

`boolean isNotEmpty(String str)`: 判断字符串是否不为空

`String autoFill(Integer source, Integer length)`: 自动填充。比如，待填充是：11，填充长度：10，则填充后的字符串为：0000000011

`String autoFill(String source, Integer length, String str, Boolean isRight)`: 自动填充

`boolean hasOnlyNum(String str)`: 字符串中只有数字。如果只含有数字，则返回true，反之，返回false.

`String getRandomString(int length)`：获取随机字符串

`String NULL = "null"`：空字符串常量

## HTTP网络请求工具类(HttpUtils)

`String get(String url)`: get请求

`String get(String url, Map<String, String> paramMap)`: get请求

`String postForm(String url, Map<String, String> paramMap)`: post方式提交表单

`String postJson(String url,  String jsonStr)`: post方式提交json字符串格式参数

## 异常工具类(ExceptionUtils)

`void isNull(Object object, String message)`：断言 是空

`void notNull(Object object, String message)`：断言 不空

`void isNull(Object object)`：断言 是空

`void notNull(Object object)`：断言 不空

`String getMessage(Throwable e)`：获得完整消息，包括异常名

`String getSimpleMessage(Throwable e)`：获得消息

## 网络工具类(NetUtils)

`InetAddress getLocalhost()`：获取本地网卡

`String getLocalhostStr()`：获取本地IP地址
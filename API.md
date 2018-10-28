**API**

注意：API.md从第21版（版本号0.0.8）开始，停止更新。详情请查看说明中的 `API` 部分

![API-20](https://github.com/fengwenyi/JavaLib/blob/master/screenshot/JavaLib-API-20.png)

### 1、SSLClient

	它不是工具类，我认为它只是一个对象类，一个Http(s) Client的对象类，拿到它，你就可以访问Http(s)。

	这里我不准备对他做更多的说明，至于如何使用，你可以参见HttpsClientUtil的源码。之所以提供它，也是为了您可以实现自定义，来满足您的需求。

### 2、Constant

	这是一个常量接口，可以直接调用。

|名称                  |值      |说明|
|:----                 |:---:   |:---|
|DEFAULT_CHATSET       |UTF-8   |编码 |
|DEFAULT_CONN_TIMEOUT  |1000 * 60    |连接超时（1分钟）|
|DEFAULT_READ_TIMEOUT  |1000 \* 60 * 10    |读取超时（10分钟）|
|RequestMethodGet      |GET     |Get方式请求数据 |
|RequestMethodPost     |POST    |Post方式请求数据|
|RequestMethodPut      |PUT     |Put方式请求数据 |
|RequestMethodDelete     |DELETE    |Delete方式请求数据|
|IP_INFO_URI           |[……]    |获取ip地址的信息的uri|

### 3、FileUtil

	文件操作工具类。

	这里我们只提供了文件写入方法write()，只所以会这样，是因为我们发现，Java已经为我们提供了很方便的文件读写方法。

* write(String path, String str, boolean isNewMode) 把字符串写入到文件中

```
	参数：
		path        文件路径
		str         要写入的字符串
		isNewMode   写入文件的方式（true 追加；false 忘掉）
	异常：
		IOException io异常
```

### 4、HttpsClientUtil

	Http(s)工具类。

	在这里，我们实现了向Http(s)上传并获取数据，可以让你的工作变得更简单。

	你可以传递String或Map类型的数据。

	虽然方法名是Https，但实际上也是支持http的。

* doPost(String url, Map<String, String> header, Map<String, String> param) POST方式向Http(s)提交数据并获取返回结果

```
	参数：
		url         服务器地址
		header      header
		param       参数（Map）
	返回：
		(String)服务器数据
	异常：
		KeyManagementException
		NoSuchAlgorithmException
		IOException
```

* doPost(String url, Map<String, String> header, String param) POST方式向Http(s)提交数据并获取返回结果
```
	参数：
		url         服务器地址
		header      header
		param       参数（String）
	返回：
		(String)服务器数据
	异常：
		KeyManagementException
	    NoSuchAlgorithmException
	    IOException
```

### 5、MathUtil
* randomNum(int range) 在[0, range)范围内产生一个随机数

```
	参数：
		range
	返回：
		[0, range)范围内的随机数
```

* randomNum(int x, int y) 在[y, x]范围内产生一个随机数

```
	参数：
		x  最大值
	    y  最小值
	返回：
		[y, x]范围内的随机数
```

### 6、NetDataUtil

```
说明：
	1、这是一个网络请求工具类，如果遇到需要访问URL获取数据，便可以使用。

	2、支持Get、Pos、Put、Delete方式请求URL数据。

	3、支持自定义Header。

	4、如果你对自定义Header很模糊，可以参考源码提供的注释代码或搜索例子。

	5、如果你不需要header或者param，均可以为（null）

```

* get(String url, Map<String, String> header, Map<String, String> param) throws IOException

* post(String url, Map<String, String> header, Map<String, String> params) throws IOException

* put(String url, Map<String, String> header, Map<String, String> param) throws IOException

* delete(String url, Map<String, String> header, Map<String, String> param) throws IOException
							  
### 7、RequestUtil

	请求信息工具类。

	在web请求中，你可以使用下面的返回获取到请求的一些ip信息。

* getRequestIp(HttpServletRequest request) 获取客户端IP地址

```
	参数：
		request		请求
	返回：
		ip地址
		对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
	异常：
		UnknownHostException
```
* getPostData(HttpServletRequest request) 获取POST提交的数据

### 8、StringUtil
	尽管很多提供了判断字符串是否为空的方法，但作为框架，我们也为你提供。

* isNullStr(String str) 判断字符串是否为空

### 9、Utils

	工具类。

* getUrlParamsByMap(Map<String, String> data) 将Map型转为请求参数型

* getUrlParams(String param) 将url参数转换成Map类型

```
	参数：
		param		aa=11&bb=22&cc=33
	返回：
		(Map)参数
```

* getIpInfo(String ip) 获取ip位置信息

```
	参数：
		ip	ip地址
	返回：
		(JsonObjct)json数据
	异常：
		IOException
```

* getPosition(String ip) 通过ip获取位置

```
	参数：
		ip	ip地址
	返回：
		(String)（国家）（地区）（省份）（城市）（县级）（运营商）
	异常：
		IOException
```

* String getUUID() 获取UUID（32）

### 10、Base64

	作者（@author Josh Bloch）如是说：

	Static methods for translating Base64 encoded strings to byte arrays and vice-versa.


### 11、RSAUtil

	RSA非对称加密算法工具类。

	在项目的配置文件中使用明文密码对于维护人员来说这是十分有难度的，因此，我们就需要对明文密码进行加密运算，来保障数据的安全性，RSA非对称加密就是一个非常不错的选择。

	当然在数据传输中或许你也有这样需求。

* getKey() 获取密钥

```
	返回：数组（长度默认512）
		[0]=>privateKey
		[1]=>publicKey
	异常：
		请参见源码进行处理
```

* getKey(int keySize) 获取密钥

```
	参数：
		keySize 密钥长度（64的整数倍，最小512）
	返回：数组
		[0]=>privateKey
		[1]=>publicKey
	异常：
		请参见源码进行处理
```

* privateKeyEncrypt(String key, String plainText) 私钥加密

* publicKeyDecrypt(String key, String cipherText) 公钥解密

* publicKeyEncrypt(String key, String plainText) 公钥加密

* privateKeyDecrypt(String key, String cipherText) 私钥解密

### 12、IReturnCode

	返回码接口。

	请写一个返回码枚举类，并实现它。

	与Result结合使用

* getCode() 返回码

* getMsg() 返回码说明

### 13、Result

	封装接口返回数据。

	与IReturnCode结合使用

* Result() 实例化

* setResult(IReturnCode iReturnCode) 错误码

* setData(IReturnCode iReturnCode, Object data) 返回数据

### 14、SafeUtil
* SHA1(String plainText)  SHA1加密

* String MD5(String plainText) MD5加密

### 15、HexUtil
* _2_16(byte[] bytes)   二进制转十六进制

### 16、TimeUtil
* longTime(String time) 距离现在多久，语义化
```
参数：格式(yyyy-MM-dd HH:mm:ss)
```

* longTime(long time) 距离现在多久，语义化
```
参数：格式(毫秒数)
```

### 17、ICallback

```
回调接口
```
* void onSuccess(T t);

* void onFail(String msg);

你可以这样使用它：
```
public void getData(ICallBack<String> callBack) {
	callBack.onSuccess("Success");
	callBack.onFail("Fail");
}

getData(new ICallBack<String>() {
	@Override
	public void onSuccess(String s) {

	}
	@Override
	public void onFail(String msg) {

	}
}
```

如果你需要更多的回调内容，你可以继承它：
```
public interface INetCallback<S, T> extends ICallback<T> {

    // 加载之前
    void before();

    // 加载多少
    void loading(int progress, int max);

    // more ...
    void m(S s);
}
```
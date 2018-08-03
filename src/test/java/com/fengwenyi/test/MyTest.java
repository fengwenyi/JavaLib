package com.fengwenyi.test;

import com.fengwenyi.javalib.util.HttpUtil;
import com.fengwenyi.javalib.util.RSAUtil;
import com.fengwenyi.javalib.util.SafeUtil;
import com.fengwenyi.javalib.util.Utils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Wenyi Feng(xfsy_2015@163.com)
 * 2017-12-16 00:33
 */
public class MyTest {

    // test RSA
    @Test
    public void testRSAUtil() {
        try {
            String [] keys = RSAUtil.getKey();

            String privateKey = keys[0];
            String publicKey = keys[1];

            System.out.println("密钥----------------------------");
            System.out.println("privateKey=>" + privateKey);
            System.out.println("publicKey=>" + publicKey);

            System.out.println("密码文本----------------------------");
            String pwd = "rPMstexg*^BFMSwf"; //由在线随机密码生成器生成
            System.out.println("pwd=>" + pwd);

            // 私钥加密、公钥解密
            String password1 = RSAUtil.privateKeyEncrypt(privateKey, pwd);
            String pwd1 = RSAUtil.publicKeyDecrypt(publicKey, password1);
            System.out.println("私钥加密、公钥解密----------------------------");
            System.out.println("password1=>" + password1);
            System.out.println("pwd1=>" + pwd1);

            // 公钥加密、私钥解密
            String password2 = RSAUtil.publicKeyEncrypt(publicKey, pwd);
            String pwd2 = RSAUtil.privateKeyDecrypt(privateKey, password2);
            System.out.println("公钥加密、私钥解密----------------------------");
            System.out.println("password2=>" + password2);
            System.out.println("pwd2=>" + pwd2);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }  catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }


        /*
        测试结果：
        密钥----------------------------
        privateKey=>MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAmXOKJBqpaFf5Mhn+xZqVsAcGo/Fc6X7F+AbhHIQ4Rz6GdXu9+9vpKvbNt5BZ0xe6ky9XMQBMS3PiPhlbYq+9YwIDAQABAkBaU3GAORV5LzenbzfRt2HfQPN+s0eexhngGqSNtaKvBEtgX7DV/lpdhxaBCMcqPU62pIJOTuN7a7Ar7jnTXopBAiEA3B4EwssxH/8NMygNnLhf8ELGhr2SNumrDoulobhXnyECIQCyd2ddePdIvopQ61GoLI8oa7BZ/dFH8LD5EvHTXOzgAwIhAIxkeRTlFIgb3Qdr3ILA9i//5y+5abCsiPXWC+aB9SEBAiBCQ8+fh2DaI9WbwiRpu1HUglfSsknY2mf7s6sS/ff+HQIhAKGUw09WL+u7sKrnGwE4h7UASaMJbT1ZDBsYWN/vsvp8
        publicKey=>MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJlziiQaqWhX+TIZ/sWalbAHBqPxXOl+xfgG4RyEOEc+hnV7vfvb6Sr2zbeQWdMXupMvVzEATEtz4j4ZW2KvvWMCAwEAAQ==
        密码文本----------------------------
        pwd=>rPMstexg*^BFMSwf
        私钥加密、公钥解密----------------------------
        password1=>GrWq7dmWeH+22ZtObvNhMSTwVjC8IXytA5D58+AY3/Tty1u5/+mySeXy/tsi3yBeeXMfs+cZqqHaHe7fG90gwA==
        pwd1=>rPMstexg*^BFMSwf
        公钥加密、私钥解密----------------------------
        password2=>ST9HfIbwCCm7R4q3ai/w5EejGwPpNnB3s6LC82uUuqvqsE/R0IGgOjbzBUTafLNoabz5gUvPmdIBcHg0dN4n4w==
        pwd2=>rPMstexg*^BFMSwf
         */
    }

    @Test
    public void testSHA1() {
        try {
            String s = SafeUtil.SHA1("xfsy");
            System.out.println(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMD5() {
        try {
            String s = SafeUtil.MD5("xfsy");
            System.out.println(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNet() {
        String url = "http://www.baidu.com";

        String rs = null;
        try {
            rs = HttpUtil.get(url, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(rs);

    }

    @Test
    public void testPositionByIp() {
        String ip = "223.85.223.128";
        try {
            String rs = Utils.getPosition(ip);
            System.out.println(rs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.fengwenyi.javalib;

import com.fengwenyi.javalib.encryption.RsaUtils;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

/**
 * @author ycq
 * @since 2019/6/27 11:41
 */
public class RsaUtilsTest {

    @Test
    public void ras() {
        try {
            String[] key = RsaUtils.getKey(2048);
            String rsaPrivateKey = key[0];
            String rsaPublicKey = key[1];
            System.out.println("rsaPrivateKey: " + rsaPrivateKey);
            System.out.println("rsaPublicKey: " + rsaPublicKey);
            //签名 SHA256WithRSA
            String sign = RsaUtils.sign("签名测试内容", "SHA256WithRSA", rsaPrivateKey, "utf-8");
            System.out.println("私钥签名：" + sign);
            //验签
            boolean verify = RsaUtils.verify("签名测试内容", sign, "SHA256WithRSA", rsaPublicKey, "utf-8");
            System.out.println("公钥验签：" + verify);
            //私钥加密
            String privateKeyEncrypt = RsaUtils.privateKeyEncrypt(rsaPrivateKey, "私钥加密公钥解密");
            System.out.println("私钥加密：" + privateKeyEncrypt);
            //公钥解密
            String publicKeyDecrypt = RsaUtils.publicKeyDecrypt(rsaPublicKey, privateKeyEncrypt);
            System.out.println("公钥解密：" + publicKeyDecrypt);
            //公钥加密
            String publicKeyEncrypt = RsaUtils.publicKeyEncrypt(rsaPublicKey, "公钥加密私钥解密");
            System.out.println("公钥加密：" + publicKeyEncrypt);
            //私钥解密
            String privateKeyDecrypt = RsaUtils.privateKeyDecrypt(rsaPrivateKey, publicKeyEncrypt);
            System.out.println("私钥解密：" + privateKeyDecrypt);
        } catch (Exception e) {

        }
    }

    @Test
    public void generateKeys() throws NoSuchAlgorithmException {
        String[] key = RsaUtils.getKey();
        String rsaPrivateKey = key[0];
        String rsaPublicKey = key[1];
        System.out.println("rsaPrivateKey: " + rsaPrivateKey);
        System.out.println("rsaPublicKey: " + rsaPublicKey);
    }

}

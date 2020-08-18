package com.fengwenyi.javalib;

import com.fengwenyi.javalib.util.PrintUtils;
import com.fengwenyi.javalib.encryption.RSAUtils;
import org.junit.Test;

/**
 * @author ycq
 * @since 2019/6/27 11:41
 */
public class RSAUtilsTest {

    @Test
    public void ras(){
        try{
            String[] key = RSAUtils.getKey(2048);
            String rsaPrivateKey = key[0];
            String rsaPublicKey = key[1];
            PrintUtils.info("rsaPrivateKey: "+rsaPrivateKey);
            PrintUtils.info("rsaPublicKey: "+rsaPublicKey);
            //签名 SHA256WithRSA
            String sign = RSAUtils.sign("签名测试内容", "SHA256WithRSA", rsaPrivateKey, "utf-8");
            PrintUtils.info("私钥签名："+sign);
            //验签
            boolean verify = RSAUtils.verify("签名测试内容", sign, "SHA256WithRSA", rsaPublicKey, "utf-8");
            PrintUtils.info("公钥验签："+verify);
            //私钥加密
            String privateKeyEncrypt = RSAUtils.privateKeyEncrypt(rsaPrivateKey, "私钥加密公钥解密");
            PrintUtils.info("私钥加密："+privateKeyEncrypt);
            //公钥解密
            String publicKeyDecrypt = RSAUtils.publicKeyDecrypt(rsaPublicKey, privateKeyEncrypt);
            PrintUtils.info("公钥解密："+publicKeyDecrypt);
            //公钥加密
            String publicKeyEncrypt = RSAUtils.publicKeyEncrypt(rsaPublicKey, "公钥加密私钥解密");
            PrintUtils.info("公钥加密："+publicKeyEncrypt);
            //私钥解密
            String privateKeyDecrypt = RSAUtils.privateKeyDecrypt(rsaPrivateKey, publicKeyEncrypt);
            PrintUtils.info("私钥解密："+privateKeyDecrypt);
        }catch (Exception e){

        }
    }

}

package com.fengwenyi.test;

import com.fengwenyi.javalib.util.RSAUtil;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author Wenyi Feng
 */
public class TestSign {


    @Test
    public void test() {
        String content = "Hello";
        String algorithm = "SHA1WithRSA";
        try {
            String [] keys = RSAUtil.getKey();
            String sign = RSAUtil.sign(content, algorithm, keys[0], null);
            boolean bool = RSAUtil.verify(content, sign, algorithm, keys[1], null);
            System.out.println(bool);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}

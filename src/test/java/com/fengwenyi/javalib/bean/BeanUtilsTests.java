package com.fengwenyi.javalib.bean;

import org.junit.Test;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-05-10
 */
public class BeanUtilsTests {

    @Test
    public void testGetFieldName() {
        String result = BeanUtils.getFieldName(User::getUserName);
        System.out.println(result);
        String result2 = BeanUtils.convertToFieldName(User::setUserName);
        System.out.println(result2);
    }

    public static class User {
        private String userName;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

}

package com.fengwenyi.javalib.bean;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-05-10
 */
public class BeanUtilsTests {

    @Test
    public void testGetFieldName() {
        String result = BeanUtils.getFieldNameByGet(User::getUserName);
        System.out.println(result);
        String result2 = BeanUtils.getFieldNameBySet(User::setUserName);
        System.out.println(result2);
        String result3 = BeanUtils.getFieldNameByGet(Entity::get_x);
        System.out.println(result3);
        String result4 = BeanUtils.getFieldNameBySet(Entity::set_x);
        System.out.println(result4);
        String result5 = BeanUtils.getFieldNameByGet(Entity::isStatus);
        System.out.println(result5);
        String result6 = BeanUtils.getFieldNameBySet(Entity::setStatus);
        System.out.println(result6);
        String result7 = BeanUtils.getFieldNameByGet(Entity::getuName);
        System.out.println(result7);
        String result8 = BeanUtils.getFieldNameBySet(Entity::setuName);
        System.out.println(result8);
        String result9 = BeanUtils.getFieldNameBySet(Entity::setId);
        System.out.println(result9);
/*
userName
userName
_x
_x
status
status
uName
uName
id
 */
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

    public static class Entity {
        private String _x;
        private Boolean deleted;
        private boolean status;
        private String uName;

        private String id;

        public String get_x() {
            return _x;
        }

        public void set_x(String _x) {
            this._x = _x;
        }

        public Boolean getDeleted() {
            return deleted;
        }

        public void setDeleted(Boolean deleted) {
            this.deleted = deleted;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getuName() {
            return uName;
        }

        public void setuName(String uName) {
            this.uName = uName;
        }

        public Entity setId(String id) {
            this.id = id;
            return this;
        }
    }

}

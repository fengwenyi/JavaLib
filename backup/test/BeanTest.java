package com.fengwenyi.test;

import org.junit.Test;

/**
 * <类说明>
 * <p>
 * <功能详细描述>
 *
 * @author Wenyi Feng
 * @since 2018-09-08
 */
public class BeanTest {

    @Test
    public void test() {
        B obj = new B();
        obj.a = "a";
        obj.b = "b";
        System.out.println(obj);
    }

}

class A {
    String a;
}

class B extends A {
    String b;
}

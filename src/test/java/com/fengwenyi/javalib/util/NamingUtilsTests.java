package com.fengwenyi.javalib.util;

import org.junit.Test;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-10-27
 */
public class NamingUtilsTests {

    @Test
    public void testUnderlineToHump() {
        String name = "user_name";
        name = NamingUtils.underlineToHump(name);
        PrintUtils.info(name);
    }

    @Test
    public void testHumpToUnderline() {
        String name = "userName";
        name = NamingUtils.humpToUnderline(name);
        PrintUtils.info(name);
    }

    @Test
    public void testHumpToMidline() {
        String name = "userName";
        name = NamingUtils.humpToMidline(name);
        PrintUtils.info(name);
    }

}

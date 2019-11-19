package com.fengwenyi.javalib;

import net.iutil.javalib.util.IpUtils;
import net.iutil.javalib.util.PrintUtils;
import org.junit.Test;
//import org.lionsoul.ip2region.DbMakerConfigException;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Erwin Feng
 * @since 2019-07-05 09:51
 */
public class IpUtilsTests {

    @Test
    public void testGetCityByIp() {
        try {
//            net/iutil/javalib/util/ip2region.db
//            java.net.URL url = IpUtils.class.getResource("ip2region.db");
            java.net.URL url = IpUtils.class.getResource("/net/iutil/javalib/util/FileUtils.java");
            PrintUtils.info(url);

            String fileName = this.getClass().getClassLoader().getResource("ip2region.db").getPath();//获取文件路径

            PrintUtils.info(fileName);

//            String city = IpUtils.getCityInfo(0, "218.88.94.192");
//            PrintUtils.info(city);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

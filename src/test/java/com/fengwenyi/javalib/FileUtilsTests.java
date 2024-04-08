package com.fengwenyi.javalib;

import com.fengwenyi.javalib.file.FileUtils;
import com.fengwenyi.javalib.util.PrintUtils;
import org.junit.jupiter.api.Test;

/**
 * 文件工具类测试
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/20
 */
public class FileUtilsTests {

    // 测试获取文件后缀名
    @Test
    public void testGetFileSuffix() {
        String filename = "xxx.jpg";
        String suffix = FileUtils.getSuffix(filename);
        PrintUtils.info(suffix); // jpg

        String filepath = "xxx/xxx.jpg";
        suffix = FileUtils.getSuffix(filename);
        PrintUtils.info(suffix); // jpg
    }

}

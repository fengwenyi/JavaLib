package com.fengwenyi.javalib.util;

import java.io.*;

/**
 * 文件工具类
 * @author Wenyi Feng
 */

public class FileUtils {

    /**
     * 将字符串写入到文件中
     * @param path 文件位置
     * @param str 字符串
     * @param isNewMode 写入方式（true追加; false覆盖）
     * @throws IOException 写入失败
     */
    public static void write(String path, String str, boolean isNewMode) throws IOException {
        File file = new File(path);
        // 如果文件不存在就创建
        if (!file.exists()) file.createNewFile();
        FileOutputStream out = new FileOutputStream(file, isNewMode);
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        // utf-8
        out.write(sb.toString().getBytes("utf-8"));
        out.close();
    }

    /**
     * 获取后缀名，例如文件名为xxx.jpg，则返回jpg.
     * 当然，也可以通过文件路径调用此方法获取后缀名，
     * 例如文件路径为xxx/xxx.jpg，则返回jpg
     * @param filename 文件名
     * @return 后缀名
     */
    public static String getSuffix(String filename) {
        int index = filename.lastIndexOf(".");
        if (index > 0) {
            return filename.substring(index + 1);
        }
        return "";
    }

}

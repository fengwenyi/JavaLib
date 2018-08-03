package com.fengwenyi.javalib.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 文件工具类
 * @author Wenyi Feng
 */

public class FileUtil {

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
     * 根据文件路径读取文件内容
     * [仅做参考]
     * @param path 文件路径
     * @return 文件内容
     * @throws IOException 读取失败
     */
    private String read(String path) throws IOException {
        StringBuffer sb = new StringBuffer();
        String tempstr;
        File file = new File(path);
        // 如果文件不存在，读取失败
        if (!file.exists()) throw new FileNotFoundException();
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        while ((tempstr = br.readLine()) != null) sb.append(tempstr);
        return sb.toString();
    }
}

package com.fengwenyi.javalib.file;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * IO工具类
 *
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/24
 */
public class IOUtils {

    /**
     * byte[] 转成 BufferedImage
     *
     * @param data byte[]
     * @return BufferedImage
     * @throws IOException IO异常
     */
    public static BufferedImage toImage(byte[] data) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        return ImageIO.read(byteArrayInputStream);
    }

    /**
     * 读取 InputStream，并关闭
     *
     * @param inputStream 输入流
     * @return 读取流结果
     * @throws IOException IO 异常
     */
    public static String readAndClose(InputStream inputStream) throws IOException {
        String result = read(inputStream);
        inputStream.close();
        return result;
    }

    /**
     * 读取 InputStream
     *
     * @param inputStream 输入流
     * @return 读取流结果
     * @throws IOException IO 异常
     */
    public static String read(InputStream inputStream) throws IOException {
        BufferedReader reader;
        StringBuilder resultBuffer = new StringBuilder();
        String tempLine;

        reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        while ((tempLine = reader.readLine()) != null) {
            resultBuffer.append(tempLine);
        }

        return resultBuffer.toString();
    }

}

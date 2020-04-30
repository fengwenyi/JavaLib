package com.fengwenyi.javalib.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * IO工具类
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/24
 */
public class IOUtils {

    /**
     * byte[] 转成 BufferedImage
     * @param data byte[]
     * @return BufferedImage
     * @throws IOException IO异常
     */
    public static BufferedImage toImage(byte[] data) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        return ImageIO.read(byteArrayInputStream);
    }

}

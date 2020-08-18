package com.fengwenyi.javalib.image;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 图片工具类
 *
 * <p>
 *     服务器，可转成{@link BufferedImage}，使用的时候再用输出流，返回到页面
 * </p>
 *
 * <p>
 *     本地磁盘，可直接写到文件中
 * </p>
 *
 * <p>
 *     Thumbnails，还提供了根据比例压缩，比如缩小原图的1半
 *         Thumbnails.of(source)
 *                     .scale(0.5)
 *                     .toFile(thumbnail);
 * </p>
 *
 * <ul>
 *     <li>图片压缩</li>
 * </ul>
 *
 * <br>
 *     图片压缩，是用的Google，如果需要用到图片压缩，请引入jar，如下：
 *     groupId: net.coobird
 *     artifactId: thumbnailator
 *     version: 0.4.8
 *
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/12 09:49
 */
public class ImageUtils {

    /**
     * 图片压缩
     * <p>
     *     需要注意，压缩后的图片宽高，并不等于指定的宽高，
     *     会根据图片的宽高进行等比例压缩
     * </p>
     * @param path 原图路径
     * @param width  压缩后的宽度
     * @param height 压缩后的高度
     * @return 图片 {@link BufferedImage}
     */
    public static BufferedImage compress(String path, int width, int height) {
        try {
            return Thumbnails
                    .of(path)
                    .size(width, height)
                    .asBufferedImage();
        } catch (IOException e) {
            // e.printStackTrace();
            return null;
        }
    }

    /**
     * 图片压缩
     * <p>
     *     需要注意，压缩后的图片宽高，并不等于指定的宽高，
     *     会根据图片的宽高进行等比例压缩
     * </p>
     * @param file 原图文件
     * @param width  压缩后的宽度
     * @param height 压缩后的高度
     * @return 图片 {@link BufferedImage}
     */
    public static BufferedImage compress(File file, int width, int height) {
        try {
            return Thumbnails
                    .of(file)
                    .size(width, height)
                    .asBufferedImage();
        } catch (IOException e) {
            // e.printStackTrace();
            return null;
        }
    }

    /**
     * 图片压缩
     * <p>
     *     需要注意，压缩后的图片宽高，并不等于指定的宽高，
     *     会根据图片的宽高进行等比例压缩
     * </p>
     * @param url 原图网络地址 {@link URL}
     * @param width  压缩后的宽度
     * @param height 压缩后的高度
     * @return 图片 {@link BufferedImage}
     */
    public static BufferedImage compress(URL url, int width, int height) {
        try {
            return Thumbnails
                    .of(url)
                    .size(width, height)
                    .asBufferedImage();
        } catch (IOException e) {
            // e.printStackTrace();
            return null;
        }
    }


    /**
     * 图片压缩，生成压缩图片文件
     * <p>
     *     需要注意，压缩后的图片宽高，并不等于指定的宽高，
     *     会根据图片的宽高进行等比例压缩
     * </p>
     * @param path 原图路径
     * @param thumbnail 缩略图保存位置
     * @param width  压缩后的宽度
     * @param height 压缩后的高度
     */
    public static void compress(String path, String thumbnail, int width, int height) {
        try {
            Thumbnails
                    .of(path)
                    .size(width, height)
                    .toFile(thumbnail);
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    /**
     * 图片压缩，生成压缩图片文件
     * @param url 图片URL
     * @param thumbnail 缩略图保存位置
     * @param scale 压缩比
     */
    public static void compress(URL url, String thumbnail, int scale) {
        try {
            Thumbnails
                    .of(url)
                    .scale(scale)
                    .toFile(thumbnail);
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    /**
     * 图片裁剪
     * @param filePath 原图片
     * @param width 宽
     * @param height 高
     * @return BufferedImage
     * @throws Exception 异常
     */
    public static BufferedImage clipping(String filePath, int width, int height) throws Exception {

        BufferedImage buffer = ImageIO.read(new File(filePath));

        /*
         * 核心算法，计算图片的压缩比
         */
        int w= buffer.getWidth();
        int h=buffer.getHeight();
        double ratiox = 1.0d;
        double ratioy = 1.0d;

        ratiox= w * ratiox / width;
        ratioy= h * ratioy / height;

        if( ratiox >= 1){
            if(ratioy < 1){
                ratiox = height * 1.0 / h;
            }else{
                if(ratiox > ratioy){
                    ratiox = height * 1.0 / h;
                }else{
                    ratiox = width * 1.0 / w;
                }
            }
        }else{
            if(ratioy < 1){
                if(ratiox > ratioy){
                    ratiox = height * 1.0 / h;
                }else{
                    ratiox = width * 1.0 / w;
                }
            }else{
                ratiox = width * 1.0 / w;
            }
        }
        /*
         * 对于图片的放大或缩小倍数计算完成，ratiox大于1，则表示放大，否则表示缩小
         */
        AffineTransformOp op = new AffineTransformOp(AffineTransform
                .getScaleInstance(ratiox, ratiox), null);
        buffer = op.filter(buffer, null);
        //从放大的图像中心截图
        buffer = buffer.getSubimage((buffer.getWidth()-width)/2, (buffer.getHeight() - height) / 2, width, height);

        // 将 buffer 转成 图片

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(buffer, "jpg", out);

        return buffer;
    }

}

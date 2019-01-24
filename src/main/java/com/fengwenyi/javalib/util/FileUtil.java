package com.fengwenyi.javalib.util;

import com.fengwenyi.javalib.bean.AudioBean;
import com.fengwenyi.javalib.bean.FileBean;
import com.fengwenyi.javalib.bean.ImageBean;
import com.fengwenyi.javalib.bean.VideoBean;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.Date;

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

    /**
     * 获取一个文件的属性
     * @param file 文件路径
     * @return 文件信息
     * @throws IOException IO异常
     */
    public static FileBean getFileInfo(String file) throws IOException {

        if (StringUtils.isEmpty(file)) {
            ExceptionUtil.notNull(file);
            return null;
        }

        File source = new File(file);
        if (!source.isFile()) {
            throw new IllegalArgumentException("file not a file");
        }

        Path filePathObj = Paths.get(file);
        BasicFileAttributeView basicView = Files.
                getFileAttributeView(filePathObj, BasicFileAttributeView.class);
        BasicFileAttributes basicFileAttributes = basicView.readAttributes();

        FileOwnerAttributeView ownerView = Files.getFileAttributeView(filePathObj,
                FileOwnerAttributeView.class);

        String fileName = source.getName();
        Date createTime = new Date(basicFileAttributes.creationTime().toMillis());
        Date lastSeeTime = new Date(basicFileAttributes.lastAccessTime().toMillis());
        Date lastUpdateTime = new Date(basicFileAttributes.lastModifiedTime().toMillis());
        String owner = ownerView.getOwner().toString();
        Long size = source.length();
        String contentType = Files.probeContentType(filePathObj);

        return new FileBean()
                .setName(fileName)
                .setPath(file)
                .setCreateTime(createTime)
                .setLastSeeTime(lastSeeTime)
                .setLaseUpdateTime(lastUpdateTime)
                .setOwner(owner)
                .setSize(size)
                .setContentType(contentType);
    }

    /**
     * 获取视频文件的信息
     * @param file 视频文件的路径
     * @return 文件信息
     * @throws IOException IO异常
     * @throws EncoderException 读取视频文件异常
     */
    public static VideoBean getVideoInfo(String file) throws IOException, EncoderException {

        if (StringUtils.isEmpty(file)) {
            ExceptionUtil.notNull(file, "file must not null");
            return null;
        }

        File source = new File(file);
        if (!source.isFile()) {
            throw new IllegalArgumentException("file not a file");
        }

        Encoder encoder = new Encoder();
        MultimediaInfo mediaInfo = encoder.getInfo(source);

        long duration = mediaInfo.getDuration();
        int height = mediaInfo.getVideo().getSize().getHeight();
        int width = mediaInfo.getVideo().getSize().getWidth();
        String format = mediaInfo.getFormat();

        FileBean fileBean = getFileInfo(file);
        VideoBean videoBean = new VideoBean();
        assert fileBean != null;
        BeanUtils.copyProperties(fileBean, videoBean);
        videoBean.setDuration(duration);
        videoBean.setWidth(width);
        videoBean.setHeight(height);
        videoBean.setFormat(format);

        return videoBean;
    }

    /**
     * 获取视频文件的信息
     * @param file 视频文件的路径
     * @return 文件信息
     * @throws IOException IO异常
     * @throws EncoderException 读取视频文件异常
     */
    public static AudioBean getAudioInfo(String file) throws IOException, EncoderException {

        if (StringUtils.isEmpty(file)) {
            ExceptionUtil.notNull(file, "file must not null");
            return null;
        }

        File source = new File(file);
        if (!source.isFile()) {
            throw new IllegalArgumentException("file not a file");
        }

        Encoder encoder = new Encoder();
        MultimediaInfo mediaInfo = encoder.getInfo(source);

        long duration = mediaInfo.getDuration();
        String format = mediaInfo.getFormat();

        FileBean fileBean = getFileInfo(file);
        AudioBean audioBean = new AudioBean();
        assert fileBean != null;
        BeanUtils.copyProperties(fileBean, audioBean);
        audioBean.setDuration(duration);
        audioBean.setFormat(format);

        return audioBean;
    }

    /**
     * 获取视频文件的信息
     * @param file 视频文件的路径
     * @return 文件信息
     * @throws IOException IO异常
     * @throws EncoderException 读取视频文件异常
     */
    public static ImageBean getImageInfo(String file) throws IOException, EncoderException {

        if (StringUtils.isEmpty(file)) {
            ExceptionUtil.notNull(file, "file must not null");
            return null;
        }

        File source = new File(file);
        if (!source.isFile()) {
            throw new IllegalArgumentException("file not a file");
        }

        Encoder encoder = new Encoder();
        MultimediaInfo mediaInfo = encoder.getInfo(source);

        //long duration = mediaInfo.getDuration();
        int height = mediaInfo.getVideo().getSize().getHeight();
        int width = mediaInfo.getVideo().getSize().getWidth();
        String format = mediaInfo.getFormat();

        FileBean fileBean = getFileInfo(file);
        ImageBean imageBean = new ImageBean();
        assert fileBean != null;
        BeanUtils.copyProperties(fileBean, imageBean);
        imageBean.setWidth(width);
        imageBean.setHeight(height);
        imageBean.setFormat(format);

        return imageBean;
    }

    /*public static void getWordCount(String file) throws IOException {
        File fileObj = new File(file);
        //fileObj.
        if (!fileObj.isFile())
            throw new IllegalArgumentException("file not a file");
        //获取文件对应的BufferedReader
        BufferedReader br = new BufferedReader(new FileReader(fileObj));

        String tempStr; //临时字符串
        int num_of_words = 0; //总汉字数
        int num_of_wordsAndPunctuation = 0; //汉字+标点
        int num_blank = 0; //空格字符

        Pattern pattern =  Pattern.compile("([\u4e00-\u9fa5]{1})"); //定义匹配模式:1个汉字
        Pattern pattern2 = Pattern.compile("([\u4e00-\u9fa5,，.。、/<>?？;；'‘’:\"【】{}]{1})"); //定义匹配模式：汉字或标点符号
        Pattern pattern3 = Pattern.compile("[\\s]");

        while((tempStr = br.readLine()) != null && !tempStr.equals("")){

            //汉字匹配，统计字数
            Matcher matcher = pattern.matcher(tempStr);
            while(matcher.find()) num_of_words++;

            //汉字标点匹配，统计字数
            Matcher matcher2 = pattern2.matcher(tempStr);
            while(matcher2.find()) num_of_wordsAndPunctuation++;

            //空格匹配，统计字数
            Matcher matcher3 = pattern3.matcher(tempStr);
            while(matcher3.find()) num_blank++;

            tempStr = "";
        }

        br.close(); //关闭文件

        System.out.println("总汉字数：" + num_of_words);
        System.out.println("总汉字标点数：" + num_of_wordsAndPunctuation);
        System.out.println("总空格数：" + num_blank);
    }*/

}

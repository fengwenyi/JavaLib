package com.fengwenyi.test;

import com.fengwenyi.javalib.bean.AudioBean;
import com.fengwenyi.javalib.bean.FileBean;
import com.fengwenyi.javalib.bean.ImageBean;
import com.fengwenyi.javalib.bean.VideoBean;
import com.fengwenyi.javalib.util.ExceptionUtil;
import com.fengwenyi.javalib.util.FileUtil;
import com.fengwenyi.javalib.util.StringUtil;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.Calendar;
import java.util.Date;

/**
 * <类说明>
 * <p>
 * <功能详细描述>
 *
 * @author Wenyi Feng
 * @since 2018-09-07
 */
@Slf4j
public class FileTest {

    public static void main(String[] args) {

        String filePath = "D:/Temp/";

        String fileName = "test-file-util.txt";

//        Date fileCreateTime = getCreateTime2("D:/Temp/test-file-util.txt");
//        System.out.println(fileCreateTime);

        /*try {
            test(filePath + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

//        test2(filePath + "tzd.mkv");
//        test2(filePath + "000_1124520170918170758_0035.flv");
//        test2(filePath + "0000002_00000220180827112821_0036.WAV");

        try {
//            getFileInfo(filePath + fileName);
//            getFileInfo(filePath + "tzd.mkv");
//            getFileInfo(filePath + "000_1124520170918170758_0035.flv");
//            getFileInfo(filePath + "0000002_00000220180827112821_0036.WAV");
//            VideoBean videoBean = getVideoInfo(filePath + "tzd.mkv");
//            VideoBean videoBean = getVideoInfo(filePath + "000_1124520170918170758_0035.flv");
//            System.out.println(videoBean);
            AudioBean audioBean = getAudioInfo(filePath + "0000002_00000220180827112821_0036.WAV");
            System.out.println(audioBean);

            System.out.println(getImageInfo(filePath + "test5.jpg"));
        } catch (IOException | EncoderException e) {
            e.printStackTrace();
        }
    }

    private static Date getCreateTime2(String fullFileName){
        Path path= Paths.get(fullFileName);
        BasicFileAttributeView basicview= Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS );
        BasicFileAttributes attr;
        try {
            attr = basicview.readAttributes();
            Date createDate = new Date(attr.creationTime().toMillis());
            return createDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 1, 0, 0, 0);
        return cal.getTime();
    }

    public static void test(String file) throws IOException {
        Path testPath = Paths.get(file);
        BasicFileAttributeView basicView = Files.
                getFileAttributeView(testPath, BasicFileAttributeView.class);
        BasicFileAttributes basicFileAttributes = basicView.readAttributes();

        System.out.println("创建时间：" + new Date(basicFileAttributes.creationTime()
                .toMillis()));

        System.out.println("最后访问时间：" + new Date(basicFileAttributes.
                lastAccessTime().toMillis()));

        System.out.println("最后修改时间：" + new Date(basicFileAttributes.
                lastModifiedTime().toMillis()));

        System.out.println("文件大小：" + basicFileAttributes.size());

        FileOwnerAttributeView ownerView = Files.getFileAttributeView(testPath,
                FileOwnerAttributeView.class);

        System.out.println("文件所有者：" + ownerView.getOwner());
    }

    /*private static String ReadVideoTime(File source) {
        Encoder encoder = new Encoder();
        String length = "";
        try {
            MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration()/1000;
            int hour = (int) (ls/3600);
            int minute = (int) (ls%3600)/60;
            int second = (int) (ls-hour*3600-minute*60);
            length = hour+"'"+minute+"''"+second+"'''";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }*/

    public static void test2(String file) {
        File source = new File(file);

        Encoder encoder = new Encoder();

        FileChannel fc = null;

        String size = "";

        try {

            MultimediaInfo m = encoder.getInfo(source);

            long ls = m.getDuration();

            System.out.println("此视频时长为:" + ls / 60000 + "分" + (ls) / 1000 + "秒！");

//视频帧宽高

            System.out.println("此视频高度为:" + m.getVideo().getSize().getHeight());

            System.out.println("此视频宽度为:" + m.getVideo().getSize().getWidth());

            System.out.println("此视频格式为:" + m.getFormat());

            FileInputStream fis = new FileInputStream(source);

            fc = fis.getChannel();

            BigDecimal fileSize = new BigDecimal(fc.size());

            size = fileSize.divide(new BigDecimal(1048576), 2, RoundingMode.HALF_UP) + "MB";

            System.out.println("此视频大小为" + size);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (null != fc) {

                try {

                    fc.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }
            }
        }
    }

    public static FileBean getFileInfo(String file) throws IOException {

        if (StringUtil.isEmpty(file)) {
            log.error("file不能为空");
            return null;
        }

        File source = new File(file);
        if (!source.isFile()) {
            log.error("file不是一个文件");
            return null;
        }

        Path filePathObj = Paths.get(file);
        BasicFileAttributeView basicView = Files.
                getFileAttributeView(filePathObj, BasicFileAttributeView.class);
        BasicFileAttributes basicFileAttributes = basicView.readAttributes();

        /*System.out.println("创建时间：" + new Date(basicFileAttributes.creationTime()
                .toMillis()));

        System.out.println("最后访问时间：" + new Date(basicFileAttributes.
                lastAccessTime().toMillis()));

        System.out.println("最后修改时间：" + new Date(basicFileAttributes.
                lastModifiedTime().toMillis()));

        System.out.println("文件大小：" + basicFileAttributes.size());



        System.out.println("文件所有者：" + ownerView.getOwner());*/

        FileOwnerAttributeView ownerView = Files.getFileAttributeView(filePathObj,
                FileOwnerAttributeView.class);

        String fileName = source.getName();
        Date createTime = new Date(basicFileAttributes.creationTime().toMillis());
        Date lastSeeTime = new Date(basicFileAttributes.lastAccessTime().toMillis());
        Date lastUpdateTime = new Date(basicFileAttributes.lastModifiedTime().toMillis());
        String owner = ownerView.getOwner().toString();
        Long size = source.length();
        String contentType = Files.probeContentType(filePathObj);

        FileBean fileBean = new FileBean()
                .setName(fileName)
                .setPath(file)
                .setCreateTime(createTime)
                .setLastSeeTime(lastSeeTime)
                .setLaseUpdateTime(lastUpdateTime)
                .setOwner(owner)
                .setSize(size)
                .setContentType(contentType);

        System.out.println(fileBean);

        return fileBean;
    }

    public static VideoBean getVideoInfo(String file) throws IOException {

        if (StringUtil.isEmpty(file)) {
            log.error("file不能为空");
            return null;
        }

        File source = new File(file);
        if (!source.isFile()) {
            log.error("file不是一个文件");
            return null;
        }

        Encoder encoder = new Encoder();

        FileChannel fc = null;

        try {

            MultimediaInfo m = encoder.getInfo(source);

            long duration = m.getDuration();
            int height = m.getVideo().getSize().getHeight();
            int width = m.getVideo().getSize().getWidth();
            String format = m.getFormat();

            FileBean fileBean = getFileInfo(file);
            VideoBean videoBean = new VideoBean();
            BeanUtils.copyProperties(fileBean, videoBean);
            videoBean.setDuration(duration);
            videoBean.setWidth(width);
            videoBean.setHeight(height);
            videoBean.setFormat(format);

            return videoBean;
        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (null != fc) {

                try {

                    fc.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }
            }
        }


        return null;
    }


    /**
     * 获取视频文件的信息
     * @param file 视频文件的路径
     * @return 文件信息
     * @throws IOException IO异常
     * @throws EncoderException 读取视频文件异常
     */
    public static AudioBean getAudioInfo(String file) throws IOException, EncoderException {

        if (StringUtil.isEmpty(file)) {
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

        if (StringUtil.isEmpty(file)) {
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

    @Test
    public void testWordCount() {
        /*try {
            FileUtil.getWordCount("D:\\Workspace\\Idea\\js\\AudioVideoSystem\\js-ftpserver\\src\\main\\java\\com\\js\\ftpserver\\handler/VideoHandler.java");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}

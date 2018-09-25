package com.fengwenyi.javalib.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.File;
import java.util.Arrays;

/**
 * 视频信息
 *
 * @author Wenyi Feng
 * @since 2018-09-07
 */
@Getter
@Setter
@Accessors(chain = true)
public class VideoBean extends FileBean {

    // 时长(毫秒)
    private Long duration;

    // 视频宽
    private Integer width;

    // 视频高
    private Integer height;

    // 视频图集
    private File[] images;

    // 视频格式
    private String format;

    @Override
    public String toString() {
        return "VideoBean{" +
                "duration=" + duration +
                ", width=" + width +
                ", height=" + height +
                ", images=" + Arrays.toString(images) +
                ", format='" + format + '\'' +
                "} " + super.toString();
    }
}

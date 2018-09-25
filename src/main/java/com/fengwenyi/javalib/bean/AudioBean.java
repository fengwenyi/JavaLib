package com.fengwenyi.javalib.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 音频信息
 *
 * @author Wenyi Feng
 * @since 2018-09-07
 */
@Getter
@Setter
@Accessors(chain = true)
public class AudioBean extends FileBean {

    // 时长(毫秒)
    private Long duration;

    // 格式
    private String format;

    @Override
    public String toString() {
        return "AudioBean{" +
                "duration=" + duration +
                ", format='" + format + '\'' +
                "} " + super.toString();
    }
}

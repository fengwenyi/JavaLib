package com.fengwenyi.javalib.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 图片信息
 *
 * @author Wenyi Feng
 * @since 2018-09-07
 */
@Getter
@Setter
@Accessors(chain = true)
public class ImageBean extends FileBean {

    // 宽
    private Integer width;

    // 高
    private Integer height;

    // 格式
    private String format;

    @Override
    public String toString() {
        return "ImageBean{" +
                "width=" + width +
                ", height=" + height +
                ", format='" + format + '\'' +
                "} " + super.toString();
    }
}

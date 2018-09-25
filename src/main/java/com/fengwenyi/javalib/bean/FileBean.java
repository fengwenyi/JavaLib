package com.fengwenyi.javalib.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 文件信息基础信息
 *
 * @author Wenyi Feng
 * @since 2018-09-07
 */
@Getter
@Setter
@Accessors(chain = true)
public class FileBean {

    // 文件名称
    private String name;

    // 文件路径
    private String path;

    // 文件创建时间
    private Date createTime;

    // 文件最后访问时间
    private Date lastSeeTime;

    // 文件修改时间
    private Date laseUpdateTime;

    // 文件所有者
    private String owner;

    // 文件大小(单位：b)
    private Long size;

    // 文件类型
    private String contentType;

    @Override
    public String toString() {
        return "FileBean{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", createTime=" + createTime +
                ", lastSeeTime=" + lastSeeTime +
                ", laseUpdateTime=" + laseUpdateTime +
                ", owner='" + owner + '\'' +
                ", size=" + size +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}

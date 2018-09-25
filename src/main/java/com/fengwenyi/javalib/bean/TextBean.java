package com.fengwenyi.javalib.bean;

/**
 * <类说明>
 * <p>
 * <功能详细描述>
 *
 * @author Wenyi Feng
 * @since 2018-09-07
 */
public class TextBean extends FileBean {

    /** 文件编码 */
    private String character;

    /** 文件字数 */
    private Long count;

    @Override
    public String toString() {
        return "TextBean{" +
                "character='" + character + '\'' +
                ", count=" + count +
                "} " + super.toString();
    }
}

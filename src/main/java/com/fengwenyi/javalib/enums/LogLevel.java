package com.fengwenyi.javalib.enums;

/**
 * 日志级别由低到高
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-05-09
 */
public enum LogLevel {

    ALL, /* 显示所有日志 */
    TRACE, /* 指定细粒度比DEBUG更低的信息事件 */
    DEBUG,
    INFO,
    WARN,
    ERROR,
    FATAL, /* 指定非常严重的错误事件，这可能导致应用程序中止 */
    OFF, /* 关闭所有日志 */

}

package com.fengwneyi.javalib.result.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * user vo (test)
 * @author Erwin Feng
 * @since 2019-03-31 17:19
 */
@Data
@Accessors(chain = true)
/* 过滤掉 null 的字段 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO implements Serializable {

    private static final long serialVersionUID = -6313489245766176805L;

    /* ID */
    private String id;

    /* name */
    private String name;

    /* age */
    private Integer age;

    /* register time */
    private Date registerTime;

}

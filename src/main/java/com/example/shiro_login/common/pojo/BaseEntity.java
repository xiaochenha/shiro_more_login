package com.example.shiro_login.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
    private int del; //0-正常 1-删除
}

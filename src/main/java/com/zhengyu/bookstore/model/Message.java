package com.zhengyu.bookstore.model;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private Long id;    //id

    private String msg; //消息

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    private Date sendTime;  //时间戳

}

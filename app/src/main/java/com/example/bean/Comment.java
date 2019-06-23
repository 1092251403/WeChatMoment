package com.example.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class Comment {

    @JSONField(name="content")
    private String content;

    @JSONField(name="sender")
    private Sender sender;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
}

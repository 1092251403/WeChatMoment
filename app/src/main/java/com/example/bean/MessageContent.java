package com.example.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class MessageContent {

    @JSONField(name="content")
    private String content;

    @JSONField(name="images")
    private List<String> images;

    @JSONField(name="sender")
    private Sender sender;

    @JSONField(name="comments")
    private List<Comment> comments;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

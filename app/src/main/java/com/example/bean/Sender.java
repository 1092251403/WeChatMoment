package com.example.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class Sender {

    @JSONField(name="username")
    private String username;

    @JSONField(name="nick")
    private String nick;

    @JSONField(name="avatar")
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

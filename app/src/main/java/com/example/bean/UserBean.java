package com.example.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class UserBean {

    @JSONField(name="project_image")
    private String profileImage;

    @JSONField(name="avatar")
    private String avatar;

    @JSONField(name="nick")
    private String nick;

    @JSONField(name="username")
    private String username;

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNick() {
        return nick;
    }

    public String getUsername() {
        return username;
    }
}

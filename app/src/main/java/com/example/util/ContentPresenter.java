package com.example.util;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.bean.MessageContent;
import com.example.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class ContentPresenter {
    private String TAG = "WeChatMoment.ContentPresenter";
private static ContentPresenter contentPresenter = null;

    public ContentPresenter(){

    }
    public synchronized  static ContentPresenter getInstance(){
        if (contentPresenter == null){
            contentPresenter = new ContentPresenter();
        }
        return contentPresenter;
    }
    private  static DataCallback dataCallback;
    public static void setCallback(DataCallback callback){
        dataCallback = callback;
    }

    public void loadData(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {

                String userContent = HttpUtil.getMssageContent(url);
                if (userContent != null && dataCallback!= null){
                    List<MessageContent> messageContentListTemp = HttpUtil.parseArray(userContent, MessageContent.class);
                    List<MessageContent> messageContentList = new ArrayList<MessageContent>();
                    int contentSize = messageContentListTemp.size();
                    if (contentSize > 0){
                        for(int i=0;i < contentSize;i++){
                            MessageContent messageContent = messageContentListTemp.get(i);
                            if (messageContent != null){
                                if(messageContent.getContent() == null || "".equalsIgnoreCase(messageContent.getContent())){
                                    if (messageContent.getImages() == null || messageContent.getImages().size() == 0){
                                        Log.d("liu","content and iamge is null");
                                        continue;
                                    }
                                }
                                messageContentList.add(messageContentListTemp.get(i));
                            }else {
                                Log.d(TAG,"messageContent is null");
                            }
                        }
                    }
                    Log.d(TAG,"messageContentList == size == "+messageContentList.size());
                    dataCallback.contentCallback(messageContentList);
                }
            }
        }).start();

    }

    public void loadUserData(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String userdata = HttpUtil.getMssageContent(url);
                if (userdata != null && dataCallback!= null){
                    UserBean userBean = JSON.parseObject(userdata, UserBean.class);;
                    if (userBean != null){
                        if(userBean.getUsername() != null){
                            dataCallback.userCallback(userBean);
                        }
                    }
                }
            }
        }).start();

    }

    public interface DataCallback{
        public void contentCallback(List<MessageContent> messageContentList);
        public void userCallback(UserBean userBean);
    }
}

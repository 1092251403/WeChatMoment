package com.example.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.Comment;
import com.example.util.ImageLoaderUtil;
import com.example.wechatmoment.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class CommentView extends FrameLayout {
    private Context mContext;
    protected LayoutInflater inflater;
    private ImageView avatar;
    private TextView name;
    private TextView content;

    public CommentView(Context context) {
        this(context,null);
    }

    public CommentView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CommentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init(){
        inflater = LayoutInflater.from(mContext);
        View convertView = inflater.inflate(R.layout.comment_layout, this, false);
        avatar = convertView.findViewById(R.id.avatar);
        name = convertView.findViewById(R.id.name);
        content = convertView.findViewById(R.id.content);
        this.addView(convertView);
    }

    public void setContent(Comment comment){
        if(comment.getContent() != null){
            content.setText(comment.getContent());
        }
        if(comment.getSender() != null){
            String username = comment.getSender().getUsername();
            name.setText(username);
            ImageLoader.getInstance().displayImage(comment.getSender().getAvatar(),avatar, ImageLoaderUtil.getPhotoImageOption(), new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    avatar.setImageBitmap(loadedImage);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });
        }
    }
}

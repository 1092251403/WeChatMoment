package com.example.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.util.ImageLoaderUtil;
import com.example.wechatmoment.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.HashMap;
import java.util.List;

public class NineGridTestLayout extends NineGridLayout implements ImageLoadingListener{
    private String TAG = "WeChatMoment.NineGridTestLayout";
    public NineGridTestLayout(Context context) {
        super(context);
    }

    public NineGridTestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onLoadingStarted(String imageUri, View view) {

    }

    @Override
    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
        Log.d("liu","onLoadingFailed==="+failReason.getType());
        if (hashMap.containsKey(imageUri)){
            ImageView imageView = (ImageView)hashMap.get(imageUri);
            imageView.setBackground(mContext.getResources().getDrawable(R.drawable.banner_default));
        }
    }

    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap bitmap) {
            if (hashMap.containsKey(imageUri)){
               ImageView imageView = (ImageView)hashMap.get(imageUri);
                imageView.setImageBitmap(bitmap);
            }
    }

    @Override
    public void onLoadingCancelled(String imageUri, View view) {

    }

    HashMap<String, ImageView> hashMap = new HashMap<String, ImageView>();
    @Override
    protected void displayImage(RatioImageView imageView, String url) {
        hashMap.put(url,imageView);
        ImageLoader.getInstance().displayImage(url,imageView, ImageLoaderUtil.getPhotoImageOption(),this);
    }

    @Override
    protected void onClickImage(int i, String url, List<String> urlList) {
        Toast.makeText(mContext, "点击了图片" + url, Toast.LENGTH_SHORT).show();
    }
}

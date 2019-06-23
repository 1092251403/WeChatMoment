package com.example.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.wechatmoment.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ImageLoaderUtil {

    public static ImageLoader getImageLoader(Context context) {

        return ImageLoader.getInstance();

    }

    public static DisplayImageOptions getPhotoImageOption() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageForEmptyUri(R.drawable.banner_default)
                .showImageOnFail(R.drawable.banner_default)
                .showImageOnLoading(R.drawable.banner_default)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        return options;
    }

    public static void displayImage(Context context, ImageView imageView, String url, DisplayImageOptions options) {
        getImageLoader(context).displayImage(url, imageView, options);
    }

    public static void displayImage(Context context,ImageView imageView, String url, DisplayImageOptions options, ImageLoadingListener listener) {
        getImageLoader(context).displayImage(url, imageView, options, listener);
    }

    public static void displayImage(Context context,String url, DisplayImageOptions options, ImageLoadingListener listener) {
        ImageSize imageSize = new ImageSize(480,480);
        NonViewAware imageAware = new NonViewAware(url, imageSize, ViewScaleType.CROP);
        getImageLoader(context).displayImage(url, imageAware, options, listener);
    }
}

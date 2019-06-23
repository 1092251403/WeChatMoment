package com.example.util;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpUtil {

    private static final int CONNECT_TIME_OUT = 15000;
    private static final int RED_TIME_OUT = 15000;

    public static String getMssageContent(String url){
        String result = null;
        HttpURLConnection connection = null;
        try{
            URL contenturl = new URL(url);
            Log.d("liu","contenturl === "+contenturl);
            if (contenturl != null){
                connection = (HttpURLConnection)contenturl.openConnection();
                connection.setConnectTimeout(CONNECT_TIME_OUT);
                connection.setReadTimeout(RED_TIME_OUT);
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                Log.d("liu","inputStram=="+inputStream.toString());
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer sb = new StringBuffer();
                String str = null;
                while ((str = reader.readLine()) != null){
//                    Log.d("liu","str=="+str);
                    sb.append(str);
                }
                return sb.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("liu","exception"+e.toString());
        }
        return null;
    }

    public static <T> List<T> parseArray(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}

package com.example.wechatmoment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bean.UserBean;
import com.example.util.ContentPresenter;
import com.example.bean.MessageContent;
import com.example.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;


public class MainActivity extends AppCompatActivity implements ContentPresenter.DataCallback{

    private ListView mListView;
    private RecyclerView.LayoutManager mLayoutManager;
    private WeChatAdapter mWeChatAdapter;
    private View headview;
    private TextView userName;
    private ImageView userIcon;
    private ImageView userProfile;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            List<MessageContent> messageContentList = (List) msg.obj;
            if (msg.what==1){
                mWeChatAdapter.setList(messageContentList);
                mWeChatAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentPresenter.setCallback(this);
        initView();
        getContentData();
    }

    private void initView(){
        mListView = (ListView) findViewById(R.id.recyclerView);
        mWeChatAdapter = new WeChatAdapter(this);
        mListView.setAdapter(mWeChatAdapter);
        mListView.addHeaderView(getHeadView());
    }

    private void getContentData(){
        ContentPresenter.getInstance().loadUserData("http://thoughtworks-ios.herokuapp.com/user/jsmith");
        ContentPresenter.getInstance().loadData("http://thoughtworks-ios.herokuapp.com/user/jsmith/tweets");
    }

    public void contentCallback(List<MessageContent> messageContentList){
        Message message = Message.obtain();
        message.what = 1;
        message.obj = messageContentList;
        handler.sendMessage(message);
    };
    public void userCallback(UserBean userBean){
        Log.d("liu","userBean ===================="+userBean.getUsername());
    };

    private View getHeadView() {
        headview = LayoutInflater.from(MainActivity.this).inflate(
                R.layout.moment_head, null);
        userName = headview.findViewById(R.id.user_name);
        userIcon = headview.findViewById(R.id.user_img);
        userProfile = headview.findViewById(R.id.user_profile);
        return headview;
    }
}

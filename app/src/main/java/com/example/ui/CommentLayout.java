package com.example.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.bean.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class CommentLayout extends LinearLayout {
    private List<Comment> mCommentlList = new ArrayList<>();
    private Context mContext;
    private boolean mIsFirst = true;

    public CommentLayout(Context context) {
        super(context);
        init(context);
    }

    public CommentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        this.setOrientation(LinearLayout.VERTICAL);
        if (getListSize(mCommentlList) == 0) {
            setVisibility(GONE);
        }
    }

    private int getListSize(List<Comment> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        return list.size();
    }

    public void setCommentList(List<Comment> commentList) {
        if (getListSize(commentList) == 0) {
            setVisibility(GONE);
            return;
        }
        setVisibility(VISIBLE);

        mCommentlList.clear();
        mCommentlList.addAll(commentList);

        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        post(new TimerTask() {
            @Override
            public void run() {
                refresh();
            }
        });
    }

    private void refresh() {
        removeAllViews();
        int size = getListSize(mCommentlList);
        if (size > 0) {
            setVisibility(VISIBLE);
        } else {
            setVisibility(GONE);
        }
        if(mCommentlList!=null && mCommentlList.size()>0){
            for(int i=0;i<mCommentlList.size();i++){
                CommentView commentView = new CommentView(mContext);
                commentView.setContent(mCommentlList.get(i));
                this.addView(commentView);
            }
        }
    }




}

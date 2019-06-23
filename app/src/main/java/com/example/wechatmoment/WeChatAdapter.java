package com.example.wechatmoment;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ui.CommentLayout;
import com.example.ui.NineGridTestLayout;
import com.example.bean.MessageContent;
import com.example.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class WeChatAdapter extends BaseAdapter implements ImageLoadingListener {
    private String TAG = "WeChatMoment.WeChatAdapter";
	private Context mContext;
	private List<MessageContent> mList ;
	private HashMap<String,ImageView> hashMap = new HashMap<String, ImageView>();

	public WeChatAdapter(Context _context) {
		this.mContext = _context;
	}

	public void setList(List<MessageContent> messageContentList) {
		mList = messageContentList;
	}

	@Override
	public int getCount() {
		if(mList == null)
			return 0;
		return mList.size();
	}

	@Override
	public MessageContent getItem(int position) {
		if(mList == null)
			return null;
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private int getListSize(List<MessageContent> list) {
		if (list == null || list.size() == 0) {
			return 0;
		}
		Log.d(TAG,"listSize==="+list.size());
		return list.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_content_grid, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.layout.setUrlList(mList.get(position).getImages());
		Log.d(TAG,"poaition =="+position);
		if(mList.get(position).getContent() == null || "".equalsIgnoreCase(mList.get(position).getContent())){
			holder.content.setVisibility(View.GONE);
		}else{
			holder.content.setText(mList.get(position).getContent());
		}
		if (mList.get(position).getSender().getUsername() != null){
			holder.username.setText(mList.get(position).getSender().getUsername());
		}
		Log.d(TAG,"username==="+mList.get(position).getSender().getUsername());
		hashMap.put(mList.get(position).getSender().getAvatar(),holder.avatar);
		ImageLoaderUtil.displayImage(mContext,mList.get(position).getSender().getAvatar(),ImageLoaderUtil.getPhotoImageOption(),this);
		if(mList.get(position).getComments()!=null && mList.get(position).getComments().size() > 0){
			holder.commentLayout.setCommentList(mList.get(position).getComments());
		}
		return convertView;
		}

	class ViewHolder{
		NineGridTestLayout layout;
		ImageView avatar;
		TextView username;
		TextView content;
		CommentLayout commentLayout;

		public ViewHolder(View itemView) {
			layout = (NineGridTestLayout) itemView.findViewById(R.id.layout_nine_grid);
			avatar = (ImageView)itemView.findViewById(R.id.avatar);
			username = (TextView)itemView.findViewById(R.id.username);
			content = (TextView) itemView.findViewById(R.id.content);
			commentLayout = (CommentLayout)itemView.findViewById(R.id.comment);
		}
	}

	public void onLoadingStarted(String imageUri, View view) {

	}

	@Override
	public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
		Log.d("liu","onLoadingFailed==="+failReason.getType());
	}

	@Override
	public void onLoadingComplete(String imageUri, View view, Bitmap bitmap) {
		if (hashMap.containsKey(imageUri)){
			ImageView imageView = (ImageView)hashMap.get(imageUri);
			imageView.setBackground(null);
			imageView.setImageBitmap(bitmap);
		}
	}

	@Override
	public void onLoadingCancelled(String imageUri, View view) {

	}

}

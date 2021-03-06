package com.tcl.hotplay;

import java.util.List;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcl.data.HotPlayListUtil;
import com.tcl.entity.HotPlayInfo;
import com.tcl.util.DataCallback;
import com.tcl.util.ImageViewUtil;

public class HotplayAdapter extends RecyclerView.Adapter<HotplayAdapter.FruitViewHolder> {
	
	private static final String TAG = "HotplayAdapter";
	
	private List<HotPlayInfo> mHotplayList = null;// new ArrayList<Fruit>();
	
//	public List<HotPlayInfo> getHotplayList() {
//		return mHotplayList;
//	}
	public void setHotplayList(List<HotPlayInfo> hotplayList) {
		mHotplayList = hotplayList;
		Log.d("liuwei", "setHotplayList,  len1:" + hotplayList.toArray().length);
		Log.d("liuwei", "setHotplayList,  len2:" + hotplayList.size());
	}
	@Override
	public int getItemCount() {
		int itemCount=(mHotplayList==null)?0:mHotplayList.size();
		return itemCount;
	}
	// Create new views (invoked by the layout manager)
	@Override
	public HotplayAdapter.FruitViewHolder onCreateViewHolder( final ViewGroup viewGroup, int i) {
		//这个i值有很大的问题？
		Log.d(TAG, "onCreateViewHolder, i: " + i);
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hotplay_item, null);
		//这里设置是否聚焦，已经失效。因为RecyclerView已经阻止了子项目获取焦点
		//view.setFocusable(true);
		return new FruitViewHolder(view);
	}
	
	// Replace the contents of a view (invoked by the layout manager) 
	@Override
	public void onBindViewHolder(final HotplayAdapter.FruitViewHolder fruitViewHolder, int i) {
        // - get element from your dataset at this position  
        // - replace the contents of the view with that element  
		Log.i(TAG,"onBindViewHolder(FruitAdapter.FruitViewHolder fruitViewHolder, int i),i="+i);
		if(mHotplayList.get(i)!=null){
			Log.d("liuwei", "onBindViewHolder, pos="+ i +",videopic:" +  mHotplayList.get(i).getVideoPic());
			ImageViewUtil.loadImageView(fruitViewHolder.fruitImage.getContext(), fruitViewHolder.fruitImage, mHotplayList.get(i).getVideoPic());
			fruitViewHolder.fruitImage2.setImageResource(ProvinceMapUtil.getPicId(mHotplayList.get(i).getProvinceName()));
			fruitViewHolder.fruitName.setText(mHotplayList.get(i).getVideoName()/*+"    测试滚动 增加长度 好好先生"*/);
			//细体字
			Typeface typeFace = Typeface.create("NotoSansCJKsc-Light", 0);
			fruitViewHolder.fruitName2.setTypeface(typeFace);
			fruitViewHolder.fruitName2.setIncludeFontPadding(false);
			fruitViewHolder.fruitName2.setText("观看用户数:"+mHotplayList.get(i).getCountPeople());

		}else{
			//fruitViewHolder.fruitName.setText(""+i);
		}
		//fruitViewHolder.fruitName.setEllipsize(TruncateAt.END);
		fruitViewHolder.fruitName.setSelected(false);
		//绑定点击事件
		Log.d("liuwei", "tag:" + mHotplayList.get(i).getActionUrl());
		fruitViewHolder.itemView.setTag(mHotplayList.get(i).getActionUrl());
		Log.i(TAG,"fruitViewHolder.fruitName.hashCode()="+fruitViewHolder.fruitName.hashCode());
	}

	// Provide a reference to the type of views that you are using  
    // (custom viewholder)  
	//这个ViewHolder实际上，持有了itemView和itemView内部的子View（避免频繁findViewByID）
    public static class FruitViewHolder extends RecyclerView.ViewHolder {  
		public ImageView fruitImage;
		public ImageView fruitImage2;
		public TextView fruitName;
		public TextView fruitName2;
		
        public FruitViewHolder(View itemView) {  
        	//其实，这个holder应该是持有这个itemView的。
            super(itemView);  
            Log.d(TAG,"FruitViewHolder(View itemView)");
			this.fruitImage = (ImageView) itemView.findViewById(R.id.fruit_image);
			this.fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
			this.fruitImage2 = (ImageView) itemView.findViewById(R.id.fruit_image2);
			this.fruitName2 = (TextView) itemView.findViewById(R.id.fruit_name2);
        }  
        
    }

	@Override
	public void onViewRecycled(FruitViewHolder holder) {
		// TODO Auto-generated method stub
		super.onViewRecycled(holder);
		Log.i(TAG,"onViewRecycled,holder.fruitName:"+holder.fruitName.getText());
	}

	@Override
	public void onViewAttachedToWindow(FruitViewHolder holder) {
		// TODO Auto-generated method stub
		super.onViewAttachedToWindow(holder);
		Log.i(TAG,"onViewAttachedToWindow,holder.fruitName:"+holder.fruitName.getText());
	}

	@Override
	public void onViewDetachedFromWindow(FruitViewHolder holder) {
		// TODO Auto-generated method stub
		super.onViewDetachedFromWindow(holder);
		Log.i(TAG,"onViewDetachedFromWindow,holder.fruitName:"+holder.fruitName.getText());
	} 
    

    
}

package com.tcl.hotplay;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.tclwidget.TCLToast;
import android.util.Log;

import com.tcl.data.HotPlayListUtil;
import com.tcl.entity.HotPlayInfo;
import com.tcl.util.DataCallback;
import com.tcl.vod.view.WaitDialog;

public class HotPlayGetUtil {
	private static final String TAG = "HotPlayGetUtil";
	
	public static int PAGE_SIZE = 8;
	public static int indexAdapter = -PAGE_SIZE;
	private static List<HotPlayInfo> mTotalList = null;//此数据为null，表示还未接收到网络数据。0表示，接收到网络数据为0或错误。
	public static List<HotPlayInfo> getTotalList() {
		return mTotalList;
	}
	
	public static void getVideoListData() {
		Log.i(TAG,"getVideoListData()");
		//显示等待框
		MainActivity.mHandler.sendEmptyMessage(MainActivity.WAIT_DATA_MSG);
		
		Object object = HotPlayListUtil.getSingleInstance().requestData(
				new DataCallback<List<HotPlayInfo>>() {
					@Override
					public void onSuccess(List<HotPlayInfo> data) {
						//从此接口传来的data不会为null，之前已经处理过了。
						Log.i(TAG,"getVideoListData(),onSuccess(),data="+data);
						//保存数据。
						mTotalList = data;
						for(int i=0;i<mTotalList.size();i++){
							mTotalList.get(i).setVideoPic("");
						}
						//取消等待框
						MainActivity.mHandler.sendEmptyMessage(MainActivity.SUCCESS_DATA_MSG);
					}

					@Override
					public void onFailure(String str) {
						Log.i(TAG,"getVideoListData(),onFailure(),str="+str);
						//附一个结果值，表示数据到来，但数据为0。这样oncreate里面边可以知道已经失败。
						mTotalList = new ArrayList<HotPlayInfo>();
						//显示失败提示
						MainActivity.mHandler.sendEmptyMessage(MainActivity.FAIL_DATA_MSG);

					}
				}
		);
	}

}

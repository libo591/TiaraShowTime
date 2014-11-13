package com.liboapp.year2014.tiarashowtime.fragments;

import com.liboapp.year2014.tiarashowtime.R;
import com.liboapp.year2014.tiarashowtime.adapters.AdapterImageUtil;
import com.liboapp.year2014.tiarashowtime.utils.AppConfig;
import com.liboapp.year2014.tiarashowtime.utils.ImageUtils;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class PicViewFragment extends Fragment 
	implements OnTouchListener,OnGestureListener,OnClickListener{
	private ImageView iv;
	private int currIndex;
	private String name;
	private String[] images;
	@SuppressWarnings("deprecation")
	private GestureDetector detector = new GestureDetector(this);
	private int minXdist = 20;
	private int minVel = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = this.getArguments();
		name = args.getString("name");
		String filename = args.getString("file");
		images = args.getStringArray("files");
		currIndex = findImageIndex(filename);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View root = inflater.inflate(R.layout.fragment_picview, container,false);
		iv = (ImageView)root.findViewById(R.id.iv1);
		iv.setOnTouchListener(this);
		iv.setLongClickable(true);
		detector.setIsLongpressEnabled(true);
		showImageByIndex(currIndex);
		return root;
	}
	
	private int findImageIndex(String findTarget){
		int len = images.length;
		int index = -1;
		for(int i=0;i<len;i++){
			String image = images[i];
			if(image.equals(findTarget)){
				index = i;
				break;
			}
		}
		return index;
	}
	private void showImageByIndex(int index){
		Drawable drawable = iv.getDrawable();
		if(drawable!=null){
			if(drawable instanceof BitmapDrawable){
				BitmapDrawable bd = (BitmapDrawable)drawable;
				Bitmap bitmap = bd.getBitmap();
				if(bitmap!=null){bitmap.recycle();}
			}
		}
		iv.setImageBitmap(ImageUtils.scaleImage(this.getActivity().getAssets(), this.name+"/"+images[index],AppConfig.screen_width, AppConfig.height_nobanner));
	}
	
	private void toPrevImage(){
		if(currIndex==0){
			currIndex = images.length-1;
		}else{
			currIndex--;
		}
		showImageByIndex(currIndex);
	}
	
	
	private void toNextImage(){
		if(currIndex==images.length-1){
			currIndex = 0;
		}else{
			currIndex++;
		}
		showImageByIndex(currIndex);
	}
	
	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		detector.onTouchEvent(arg1);
		return true;
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float vx,
			float vy) {
		if((e1.getX()-e2.getX())>this.minXdist
				&&Math.abs(vx)>this.minVel){
			toNextImage();
		}
		if((e2.getX()-e1.getX())>this.minXdist
				&&Math.abs(vx)>this.minVel){
			toPrevImage();
		}
		return true;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		return false;
	}
	@Override
	public void onClick(View view) {
//		WallpaperManager wm = WallpaperManager.getInstance(this);
//		try {
//			wm.setStream(getAssets().open(this.name+"/"+images[this.currIndex]));
//			Toast.makeText(this, getResources().getString(R.string.configSuccess), Toast.LENGTH_SHORT).show();
//			configll.setVisibility(View.GONE);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}

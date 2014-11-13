package com.liboapp.year2014.tiarashowtime.adapters;

import com.liboapp.year2014.tiarashowtime.R;
import com.liboapp.year2014.tiarashowtime.utils.AppConfig;
import com.liboapp.year2014.tiarashowtime.utils.ImageUtils;
import com.liboapp.year2014.tiarashowtime.views.roundimage.RoundedImageView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MainImageAdapter extends BaseAdapter {

	private Context ctx;
	private int[] imageids = new int[]{
			R.drawable.main_baolan,R.drawable.main_juli,
			R.drawable.main_suyan,R.drawable.main_enjing,
			R.drawable.main_xiaomin,R.drawable.main_zhiyan
	};
	
	private int[] desps = new int[]{
			R.string.baolan,R.string.juli,
			R.string.suyan,R.string.enjing,
			R.string.xiaomin,R.string.zhiyan
	};
	public MainImageAdapter(Context ctx){
		this.ctx = ctx;  
	}
	
	@Override
	public int getCount() {
		return imageids.length;
	}

	@Override
	public Object getItem(int arg0) {
		return desps[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return imageids[arg0];
	}

	@Override
	public View getView(int index, View view, ViewGroup vg) {
		Resources resource = this.ctx.getResources();
		int imagew = (int)resource.getDimension(R.dimen.main_item_width);
		int imageh = (int)resource.getDimension(R.dimen.main_item_height);
		if(view==null){
			view = new RoundedImageView(this.ctx);
			GridView.LayoutParams lp = new GridView.LayoutParams(imagew, imageh);
			view.setLayoutParams(lp);
			((RoundedImageView)view).setScaleType(ScaleType.CENTER_CROP);
			((RoundedImageView)view).setCornerRadius(resource.getDimension(R.dimen.main_item_round));
			((RoundedImageView)view).setBorderWidth(R.dimen.main_item_borderwidth);
			((RoundedImageView)view).setBorderColor(resource.getColor(R.color.imageBorderColor));
			((RoundedImageView)view).mutateBackground(false);//
			((RoundedImageView)view).setOval(false);//如果为true，则为椭圆。为false，按照radius来绘制
		}
		Bitmap bitmap = AdapterImageUtil.genBitmap((RoundedImageView)view,this.ctx, imageids[index], imagew, imageh);
		((RoundedImageView)view).setImageBitmap(bitmap);
		view.setContentDescription(resource.getString(desps[index]));
		return view;
	}
}

package com.yokoding.wildanafif.mediapembelajaranproject.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yokoding.wildanafif.mediapembelajaranproject.R;

/**
 * Created by wildan afif on 5/21/2017.
 */

public class ViewPagerAdapterSlider extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images={R.drawable.slide1,R.drawable.slide2,R.drawable.slide3};

    public ViewPagerAdapterSlider(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        this.layoutInflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.custom_layout_slider, null);
        ImageView imageView=(ImageView)view.findViewById(R.id.imageView);
        imageView.setImageResource(this.images[position]);
        ViewPager vp=(ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp =(ViewPager) container;
        View view=(View) object;
        vp.removeView(view);
    }
}

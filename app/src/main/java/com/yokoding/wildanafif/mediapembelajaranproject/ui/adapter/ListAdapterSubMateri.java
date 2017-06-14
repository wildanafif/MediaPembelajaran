package com.yokoding.wildanafif.mediapembelajaranproject.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yokoding.wildanafif.mediapembelajaranproject.R;
import com.yokoding.wildanafif.mediapembelajaranproject.model.DetailMateri;
import com.yokoding.wildanafif.mediapembelajaranproject.model.Materi;

import java.util.List;

/**
 * Created by wildan afif on 5/24/2017.
 */

public class ListAdapterSubMateri extends BaseAdapter {
    Context mContext;
    private List<DetailMateri> mProductList;

    public ListAdapterSubMateri(Context mContext, List<DetailMateri> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.list_sub_materi, null);
        TextView title = (TextView)v.findViewById(R.id.title_sub_materi_list);
        title.setText(mProductList.get(position).getTitle());
        return v;
    }
}

package com.yokoding.wildanafif.mediapembelajaranproject.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yokoding.wildanafif.mediapembelajaranproject.R;
import com.yokoding.wildanafif.mediapembelajaranproject.model.Bab;

import java.util.List;

/**
 * Created by wildan afif on 5/23/2017.
 */

public class ListAdapter extends BaseAdapter {
    Context mContext;
    private List<Bab> mProductList;

    public ListAdapter(Context mContext, List<Bab> mProductList) {
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
        View v = View.inflate(mContext, R.layout.list_daftar_bab, null);
        TextView tvName = (TextView)v.findViewById(R.id.tv_name);

        tvName.setText(mProductList.get(position).getJudul_bab());



        return v;
    }
}

package com.yokoding.wildanafif.mediapembelajaranproject.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.yokoding.wildanafif.mediapembelajaranproject.MainActivity;
import com.yokoding.wildanafif.mediapembelajaranproject.R;
import com.yokoding.wildanafif.mediapembelajaranproject.model.Bab;
import com.yokoding.wildanafif.mediapembelajaranproject.ui.adapter.ListAdapter;

import java.io.Serializable;
import java.util.ArrayList;


public class MateriFragment extends ListFragment implements  Serializable, OnItemClickListener {
    private MainActivity mainActivity;
    private ArrayList<Bab> mProductList=new ArrayList<>();
    private ListView lvProduct;
    private ListAdapter adapter;
    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_materi, container, false);
        lvProduct = (ListView)view.findViewById(android.R.id.list);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mProductList.add(new Bab(1,"Bab I Arsitektur dan Organisasi Komputer"));
        mProductList.add(new Bab(2,"Bab II Media Penyimpanan Data External"));
        mProductList.add(new Bab(3,"Bab III Karakteristik Memori"));
        mProductList.add(new Bab(4,"Bab IV Memori Semikonduktor"));
        adapter = new ListAdapter(getContext(), mProductList);
        lvProduct.setAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "Iki Lho "+mProductList.get(position).getJudul_bab(), Toast.LENGTH_SHORT).show();
        getActivity().finish();
        ExpandFragment materiFragment=new ExpandFragment();
        Intent i = new Intent(getContext(), MainActivity.class);
        i.putExtra("fragment", materiFragment);
        startActivity(i);


    }
}

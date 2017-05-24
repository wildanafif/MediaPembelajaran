package com.yokoding.wildanafif.mediapembelajaranproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.yokoding.wildanafif.mediapembelajaranproject.MainActivity;
import com.yokoding.wildanafif.mediapembelajaranproject.R;
import com.yokoding.wildanafif.mediapembelajaranproject.model.Materi;
import com.yokoding.wildanafif.mediapembelajaranproject.ui.adapter.ListAdapter;
import com.yokoding.wildanafif.mediapembelajaranproject.ui.adapter.ListAdapterSubMateri;

import java.io.Serializable;
import java.util.ArrayList;


public class ExpandFragment extends Fragment implements Serializable, AdapterView.OnItemClickListener {
    private ExpandableListAdapter mAdapterView;
    ExpandableListView expandableListView;
    Toolbar toolbar;
    private Toolbar supportActionBar;
    private ArrayList<Materi> mProductList=new ArrayList<>();
    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4;
    private Button btn_1;
    private View v;
    private ListView lvProduct;
    private ListAdapterSubMateri adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_expand, null);
        lvProduct = (ListView)v.findViewById(R.id.list_sub_materi_oo);
        mProductList.add(new Materi(1,"Bab 1 Sub Materi 1"));
        mProductList.add(new Materi(2,"Bab 2 Sub Materi 2"));
        mProductList.add(new Materi(3,"Bab 3 Sub Materi 3"));
        adapter = new ListAdapterSubMateri(getContext(), mProductList);
        lvProduct.setAdapter(adapter);
        lvProduct.setOnItemClickListener(this);

        btn_1=(Button)v.findViewById(R.id.expandableButton1);
        expandableLayout1 = (ExpandableRelativeLayout)v.findViewById(R.id.expandableLayout1);
        intiTogle();
        return v;
    }

    private void intiTogle() {
        this.btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableLayout1.toggle(); // toggle expand and collapse
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "Iki Lho "+mProductList.get(position).getNama_materi(), Toast.LENGTH_SHORT).show();
        DetailMateri detailMateri=new DetailMateri();
        Intent i = new Intent(getContext(), MainActivity.class);
        i.putExtra("fragment", detailMateri);
        startActivity(i);
    }
}

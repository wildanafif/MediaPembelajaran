package com.yokoding.wildanafif.mediapembelajaranproject.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yokoding.wildanafif.mediapembelajaranproject.MainActivity;
import com.yokoding.wildanafif.mediapembelajaranproject.R;
import com.yokoding.wildanafif.mediapembelajaranproject.model.Bab;
import com.yokoding.wildanafif.mediapembelajaranproject.model.User;
import com.yokoding.wildanafif.mediapembelajaranproject.model.database.UserHandler;
import com.yokoding.wildanafif.mediapembelajaranproject.ui.adapter.ListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._BAB;
import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._SLIDER;
import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._STATUS_BAB;
import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._URL_GAMBAR;


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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = _BAB;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET,url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){

                    JSONObject json_data = null;
                    try {
                        json_data = response.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        mProductList.add(new Bab(json_data.getInt("id"),json_data.getString("title"),json_data.getString("indikator"),json_data.getInt("kkm")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setList();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);



    }

    private void setList() {
        adapter = new ListAdapter(getContext(), mProductList);
        lvProduct.setAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


        UserHandler userHandler= new UserHandler(getContext());
        User user=userHandler.getUser();


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = _STATUS_BAB+user.getId()+"/"+mProductList.get(position).getId_bab();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    boolean st= response.getBoolean("status_bab");
                    if (st){
                        ExpandFragment materiFragment=new ExpandFragment();
                        materiFragment.bab=mProductList.get(position);
                        goMateri(materiFragment);
                    }else{
                        Toast.makeText(getContext(), "Untuk membaca bab ini, anda harus menyelesaikan bab sebelumnya", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);




//        i.putExtra("bab",mProductList.get(position));



    }

    private void goMateri(ExpandFragment materiFragment){
       // getActivity().finish();
        Intent i = new Intent(getContext(), MainActivity.class);
        i.putExtra("fragment", materiFragment);
        startActivity(i);
    }
}

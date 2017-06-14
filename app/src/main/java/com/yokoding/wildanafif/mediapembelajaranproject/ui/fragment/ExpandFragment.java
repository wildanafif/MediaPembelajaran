package com.yokoding.wildanafif.mediapembelajaranproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.yokoding.wildanafif.mediapembelajaranproject.MainActivity;
import com.yokoding.wildanafif.mediapembelajaranproject.R;
import com.yokoding.wildanafif.mediapembelajaranproject.model.Bab;
import com.yokoding.wildanafif.mediapembelajaranproject.model.DetailMateri;
import com.yokoding.wildanafif.mediapembelajaranproject.model.Materi;
import com.yokoding.wildanafif.mediapembelajaranproject.ui.adapter.ListAdapterSubMateri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._MATERI;


public class ExpandFragment extends Fragment implements Serializable, AdapterView.OnItemClickListener {
    private ExpandableListAdapter mAdapterView;
    ExpandableListView expandableListView;
    Toolbar toolbar;
    private Toolbar supportActionBar;
    private ArrayList<DetailMateri> mProductList=new ArrayList<>();
    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4;
    private Button btn_1;
    private View v;
    private ListView lvProduct;
    private ListAdapterSubMateri adapter;

    public Bab bab;
    private TextView et_keterangan;
    private Button evalusi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_expand, null);
        lvProduct = (ListView)v.findViewById(R.id.list_sub_materi_oo);
        this.et_keterangan=(TextView)v.findViewById(R.id.keterangan1);
        et_keterangan.setText(bab.getIndikator());

        this.evalusi=(Button)v.findViewById(R.id.ulangan);
        this.evalusi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoalFragment soal =new SoalFragment();
                soal.bab=bab;
                Intent i = new Intent(getContext(), MainActivity.class);
                i.putExtra("fragment", soal);
                startActivity(i);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = _MATERI+bab.getId_bab();

        getActivity().setTitle(bab.getJudul_bab());

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
                        DetailMateri detailMateri=new DetailMateri(
                                json_data.getInt("id"),
                                json_data.getInt("bab_id"),
                                json_data.getString("title"),
                                json_data.getString("image"),
                                json_data.getString("isi"),
                                json_data.getString("youtube"),
                                json_data.getString("rangkuman")
                        );
                        mProductList.add(detailMateri);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setList();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);



        btn_1=(Button)v.findViewById(R.id.expandableButton1);
        expandableLayout1 = (ExpandableRelativeLayout)v.findViewById(R.id.expandableLayout1);
        intiTogle();
        return v;
    }

    private void setList() {

        adapter = new ListAdapterSubMateri(getContext(), mProductList);
        lvProduct.setAdapter(adapter);
        lvProduct.setOnItemClickListener(this);
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
        //Toast.makeText(getContext(), ""+mProductList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        getActivity().finish();
        DetailMateriFragment detailMateriFragment =new DetailMateriFragment();

        Intent i = new Intent(getContext(), MainActivity.class);
        i.putExtra("fragment", detailMateriFragment);
        i.putExtra("msg",mProductList.get(position));
        startActivity(i);

    }
}

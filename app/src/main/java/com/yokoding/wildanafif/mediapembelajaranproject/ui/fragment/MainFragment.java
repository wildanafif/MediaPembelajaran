package com.yokoding.wildanafif.mediapembelajaranproject.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.yokoding.wildanafif.mediapembelajaranproject.MainActivity;
import com.yokoding.wildanafif.mediapembelajaranproject.R;
import com.yokoding.wildanafif.mediapembelajaranproject.model.Slider;
import com.yokoding.wildanafif.mediapembelajaranproject.model.User;
import com.yokoding.wildanafif.mediapembelajaranproject.model.database.UserHandler;
import com.yokoding.wildanafif.mediapembelajaranproject.model.task.SaveUserTask;
import com.yokoding.wildanafif.mediapembelajaranproject.model.task.SliderTask;
import com.yokoding.wildanafif.mediapembelajaranproject.ui.adapter.ViewPagerAdapterSlider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._SLIDER;
import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._URL_GAMBAR;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements Serializable, BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener, View.OnClickListener {


    private View rootView;
    private SliderLayout imageSlider;
    private MainActivity mainActivity;
    private LinearLayout materi;
    public HashMap<String,String> file_maps = new HashMap<String, String>();
    private Dialog dialog2;


    public MainFragment() {
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        setUser();
        imageSlider = (SliderLayout)this.rootView.findViewById(R.id.slider);

        this.materi=(LinearLayout)this.rootView.findViewById(R.id.materi);
        this.materi.setOnClickListener(this);
        getActivity().setTitle("Media Pembelajaran");
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = _SLIDER;
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
                        System.out.println(json_data.getString("gambar"));

                        file_maps.put(json_data.getString("judul"), _URL_GAMBAR+json_data.getString("gambar"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setSlider();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);




        return rootView;
    }

    private void setUser() {
        UserHandler userHandler= new UserHandler(getContext());
        User user=userHandler.getUser();
        if (user==null){
            dialog2 = new Dialog(getContext());
            dialog2.setContentView(R.layout.dialog_input_user);
            dialog2.setTitle("Masukkan data diri anda");
            dialog2.show();

            final EditText et_kode_absen = (EditText) dialog2.findViewById(R.id.et_kode_absen);
            final EditText et_nama = (EditText) dialog2.findViewById(R.id.et_nama);
            Button btn_simpan = (Button) dialog2.findViewById(R.id.btn_simpan_user);

            btn_simpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message="";
                    if (et_kode_absen.getText().toString().matches("")){
                        message="Kode Absen Tidak Boleh Kosong";
                    }else if (et_nama.getText().toString().matches("")){
                        message="Nama Tidak Boleh Kosong";
                    }

                    if (message.matches("")){

                        User user1=new User(et_nama.getText().toString(),Integer.valueOf(et_kode_absen.getText().toString()));
                        save(user1);
                    }else{
                        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                        alertDialog.setTitle("");
                        alertDialog.setMessage(message);
                        alertDialog.show();
                    }
                }
            });
        }

    }

    private void save(User user1){
        SaveUserTask saveUserTask=new SaveUserTask(getContext(),user1,this);
        saveUserTask.execute("register");
    }

    public void close(User user){
        dialog2.dismiss();
        UserHandler userHandler= new UserHandler(getContext());
        userHandler.create(user);
        Toast.makeText(getContext(),"Selamat Datang "+user.getNama() , Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        imageSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
    @Override
    public void onPageSelected(int position) {
        Log.e("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.materi:
                //getActivity().finish();
                MateriFragment materiFragment=new MateriFragment();
                Intent i = new Intent(getContext(), MainActivity.class);
                i.putExtra("fragment", materiFragment);
                startActivity(i);
                break;
        }
    }

    public void setSlider(){

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            imageSlider.addSlider(textSliderView);
        }
        imageSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        imageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        imageSlider.setCustomAnimation(new DescriptionAnimation());
        imageSlider.setDuration(2000);
        imageSlider.addOnPageChangeListener(this);

    }
}
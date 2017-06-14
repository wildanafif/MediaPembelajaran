package com.yokoding.wildanafif.mediapembelajaranproject.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.yokoding.wildanafif.mediapembelajaranproject.MainActivity;
import com.yokoding.wildanafif.mediapembelajaranproject.R;
import com.yokoding.wildanafif.mediapembelajaranproject.model.Bab;
import com.yokoding.wildanafif.mediapembelajaranproject.model.JawabanSoal;
import com.yokoding.wildanafif.mediapembelajaranproject.model.Soal;
import com.yokoding.wildanafif.mediapembelajaranproject.model.User;
import com.yokoding.wildanafif.mediapembelajaranproject.model.database.UserHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

import worker8.com.github.radiogroupplus.RadioGroupPlus;

import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._BAB;
import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._GET_SOAL;
import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._SET_NILAI;
import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._STATUS_BAB;

/**
 * Created by wildan afif on 6/9/2017.
 */

public class SoalFragment extends Fragment implements Serializable {
    private View view;
    public Bab bab;
    private TextView title_soal;
    private TextView text_prepare;
    private Button mulai;
    private ArrayList<Soal> daftar_soal=new ArrayList<>();
    private ArrayList<JawabanSoal> status_jawaban=new ArrayList<>();
    private int jumlah_soal=0;
    private int position=0;
    private int total_salah=0;
    private User user;
    private LinearLayout status_skor;
    private TextView skor;
    private TextView pesan_skor;
    private int nilai;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.prepare_evaluasi, container, false);

        this.title_soal=(TextView)view.findViewById(R.id.title_soal);
        this.text_prepare=(TextView)view.findViewById(R.id.text_prepare);
        this.mulai=(Button)view.findViewById(R.id.mualai);
        this.status_skor=(LinearLayout)view.findViewById(R.id.status_skor);
        this.skor=(TextView)view.findViewById(R.id.skor);
        this.pesan_skor=(TextView)view.findViewById(R.id.pesan_skor);
        this.status_skor.setVisibility(View.INVISIBLE);
        this.mulai.setVisibility(View.INVISIBLE);
        this.title_soal.setText("Evaluasi "+bab.getJudul_bab());
        getActivity().setTitle("Evaluasi "+bab.getJudul_bab());
        this.text_prepare.setText("Anda akan mulai mengerjakan soal ujian untuk materi "+bab.getJudul_bab()+", untuk mulai mengerjakan soal silahkan klik tombol start dobawah ini");
        requestSoal();

        return view;
    }

    private void mulai_megerjakan() {
        this.mulai.setVisibility(View.INVISIBLE);
        if (position<jumlah_soal){


            //necessary code here
            final Soal s=daftar_soal.get(position);

            final Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.fragment_soal);
            TextView text1= (TextView)dialog.findViewById(R.id.text1);
            TextView text2= (TextView)dialog.findViewById(R.id.text2);
            TextView text3= (TextView)dialog.findViewById(R.id.text3);
            TextView text4= (TextView)dialog.findViewById(R.id.text4);
            TextView text5= (TextView)dialog.findViewById(R.id.text5);
            text1.setText(s.getA());
            text2.setText(s.getB());
            text3.setText(s.getC());
            text4.setText(s.getD());
            text5.setText(s.getE());
            int no_soal=position+1;
            dialog.setTitle("Soal No "+no_soal);
            Button btn_jawab=(Button)dialog.findViewById(R.id.jawab_soal);
            TextView text_soal=(TextView)dialog.findViewById(R.id.text_soal);
            text_soal.setText(Html.fromHtml(s.getSoal()));



            //set jawaban
            final RadioGroupPlus mRadioGroupPlus = (RadioGroupPlus) dialog.findViewById(R.id.radio_group_plus);







            dialog.show();
            dialog.setCancelable(false);
            btn_jawab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jawaban_pilih="0";

                    if (R.id.jawaban1 == mRadioGroupPlus.getCheckedRadioButtonId()) {
                        jawaban_pilih="1";
                    } else if (R.id.jawaban2 == mRadioGroupPlus.getCheckedRadioButtonId()) {
                        jawaban_pilih="2";
                    } else if (R.id.jawaban3 == mRadioGroupPlus.getCheckedRadioButtonId()) {
                        jawaban_pilih="3";
                    } else if (R.id.jawaban4 == mRadioGroupPlus.getCheckedRadioButtonId()) {
                        jawaban_pilih="4";
                    } else if (R.id.jawaban5 == mRadioGroupPlus.getCheckedRadioButtonId()) {
                        jawaban_pilih="5";
                    } else {
                        jawaban_pilih="0";
                    }

                    if (jawaban_pilih.matches(s.getBenar())){
                        status_jawaban.add(new JawabanSoal(true,jawaban_pilih));
                        Toast.makeText(getContext(), "Jawaban Benar", Toast.LENGTH_SHORT).show();
                    }else{
                        status_jawaban.add(new JawabanSoal(false,jawaban_pilih));
                        Toast.makeText(getContext(), "Jawaban Salah", Toast.LENGTH_SHORT).show();
                        total_salah++;
                    }
                    position++;
                    dialog.dismiss();
                    mulai_megerjakan();
                }
            });

        }else{

            nilai=100/3*(jumlah_soal-total_salah);
            Toast.makeText(getContext(), "Anda Salah "+ total_salah + " dan Mendapat nilai "+nilai, Toast.LENGTH_SHORT).show();

            String status_pesan_skor="";
            if (nilai<bab.getKkm()){
                this.mulai.setVisibility(View.VISIBLE);
                status_pesan_skor="Nilai anda belum memenuhi, silahkan ulangi ujian anda dengan menekan tombol Start diatas";
            }else{
                this.mulai.setVisibility(View.INVISIBLE);
                status_pesan_skor="Selamat anda berhasil menyelesaikan evaluasi "+bab.getJudul_bab()+" dan anda dapat melanjutkan materi ke bab berikutnya";
                kirim_server();
            }
            this.skor.setText(""+nilai);
            this.pesan_skor.setText(status_pesan_skor);
            this.status_skor.setVisibility(View.VISIBLE);

            total_salah=0;
            position=0;

        }




    }

    private void kirim_server() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = _SET_NILAI+user.getId()+"/"+bab.getId_bab()+"/"+nilai;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET,url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
        getActivity().finish();
        SoalFragment soal =new SoalFragment();
        soal.bab=bab;
        Intent i = new Intent(getContext(), MainActivity.class);
        i.putExtra("fragment", soal);
        startActivity(i);
    }

    private void requestSoal() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = _GET_SOAL+bab.getId_bab();
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET,url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i<response.length();i++){

                    JSONObject json_data = null;

                    try {

                        json_data = response.getJSONObject(i);
                        daftar_soal.add(new Soal(json_data.getString("id"), String.valueOf(bab.getId_bab()), json_data.getString("soal"), json_data.getString("a"),json_data.getString("b"),json_data.getString("c"),json_data.getString("d"),json_data.getString("e"),json_data.getString("benar")));
                        jumlah_soal++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mulai.setVisibility(View.VISIBLE);
                mulai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mulai_megerjakan();
                    }
                });
                getStatusBab();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    private void getStatusBab(){
        UserHandler userHandler= new UserHandler(getContext());
        user=userHandler.getUser();


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = _STATUS_BAB+user.getId()+"/"+bab.getId_bab();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    boolean st= response.getBoolean("status_bab");
                    if (response.getString("nilai").matches("")){
                        mulai.setVisibility(View.VISIBLE);
                    }else{
                        skor.setText(response.getString("nilai"));
                        pesan_skor.setText("Anda Sudah mengerjakan evaluasi");
                        status_skor.setVisibility(View.VISIBLE);
                        mulai.setVisibility(View.INVISIBLE);
                        text_prepare.setVisibility(View.GONE);
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
    }
}

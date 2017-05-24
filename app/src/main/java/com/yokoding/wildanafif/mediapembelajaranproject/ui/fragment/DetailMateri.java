package com.yokoding.wildanafif.mediapembelajaranproject.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;

import com.yokoding.wildanafif.mediapembelajaranproject.R;

import java.io.Serializable;

/**
 * Created by wildan afif on 5/21/2017.
 */

public class DetailMateri extends Fragment implements Serializable, View.OnClickListener {
    private View view;
    private WebView myWebView;
    private View btn_tugas;
    private View btn_rangkuman;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view=inflater.inflate(R.layout.fragment_sub_materi, container, false);
        myWebView = (WebView) this.view.findViewById( R.id.frame_video );
        this.btn_tugas=this.view.findViewById(R.id.btn_tugas);
        this.btn_rangkuman=this.view.findViewById(R.id.btn_rangkuman);
        this.btn_tugas.setOnClickListener(this);
        this.btn_rangkuman.setOnClickListener(this);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("http://10.212.253.77/yokoding/pembelajaran/video.html");
        return this.view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tugas:
                showPopUp();
                break;
        }

    }

    private void showPopUp() {


        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setTitle("Tugas Sub Materi 1");

        builder.setMessage("Apa pengertian dari System Interconection?");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });

        builder.show();
    }
}

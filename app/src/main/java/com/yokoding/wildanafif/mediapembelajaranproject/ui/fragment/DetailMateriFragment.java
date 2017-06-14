package com.yokoding.wildanafif.mediapembelajaranproject.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.yokoding.wildanafif.mediapembelajaranproject.R;
import com.yokoding.wildanafif.mediapembelajaranproject.model.Bab;
import com.yokoding.wildanafif.mediapembelajaranproject.model.DetailMateri;
import com.yokoding.wildanafif.mediapembelajaranproject.model.Materi;
import com.yokoding.wildanafif.mediapembelajaranproject.model.task.Image;

import org.w3c.dom.Text;

import java.io.Serializable;

import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._URLMATERI;
import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._URL_GAMBAR;
import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._VIDEO;

/**
 * Created by wildan afif on 5/21/2017.
 */

public class DetailMateriFragment extends Fragment implements Serializable, View.OnClickListener {
    private View view;
    private WebView myWebView;
    private View btn_tugas;
    private View btn_rangkuman;

    public DetailMateri materi;
    private TextView title_materi;
    private TextView text_sub_materi;
    private TextView text_title_gambar_sub_materi;
    private NetworkImageView image_sub_materi;
    private TextView title_video;
    private LinearLayout layout_video;
    private LinearLayout layout_gamabr;
    private WebView web_isi_mater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view=inflater.inflate(R.layout.fragment_sub_materi, container, false);
        materi= (DetailMateri) getArguments().getSerializable("detailmateri");
        this.layout_video=(LinearLayout)view.findViewById(R.id.layout_video);

        myWebView = (WebView) this.view.findViewById( R.id.frame_video );
        this.btn_tugas=this.view.findViewById(R.id.btn_tugas);
        this.btn_rangkuman=this.view.findViewById(R.id.btn_rangkuman);
        this.btn_tugas.setOnClickListener(this);
        this.btn_rangkuman.setOnClickListener(this);

        this.title_materi=(TextView)view.findViewById(R.id.title_sub_materi);
        this.text_sub_materi=(TextView)view.findViewById(R.id.text_sub_materi);
        this.text_title_gambar_sub_materi=(TextView)view.findViewById(R.id.title_gambar_sub_materi);
        this.image_sub_materi=(NetworkImageView)view.findViewById(R.id.image_sub_materi);
        this.title_video=(TextView)view.findViewById(R.id.title_video_sub_materi);
        this.layout_gamabr=(LinearLayout)view.findViewById(R.id.layout_gambar);
        this.web_isi_mater=(WebView)view.findViewById(R.id.web_isi_materi);

        myWebView.getSettings().setJavaScriptEnabled(true);
        web_isi_mater.getSettings().setJavaScriptEnabled(true);
        setUi();
        return this.view;
    }

    private void setUi() {

        if (materi!=null){
            if (materi.getYoutube().matches("")){
                layout_video.setVisibility(View.GONE);
                myWebView.setVisibility(View.GONE);
            }
            Toast.makeText(getContext(), ""+materi.getImage(), Toast.LENGTH_SHORT).show();
            if (materi.getImage().matches("")){
                this.layout_gamabr.setVisibility(View.GONE);
                this.image_sub_materi.setVisibility(View.GONE);
            }
            Image image=new Image(getContext(),_URL_GAMBAR+materi.getImage(),this.image_sub_materi);
            image.showImage();
            myWebView.loadUrl(_VIDEO+this.materi.getYoutube());
            web_isi_mater.loadUrl(_URLMATERI+materi.getId());
            title_materi.setText(materi.getTitle());
            text_sub_materi.setText(Html.fromHtml(materi.getIsi()));
            text_title_gambar_sub_materi.setText("Gambar "+materi.getTitle());

            this.title_video.setText("Video Pembelajaran "+materi.getTitle());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tugas:
                showPopUp();
                break;
            case R.id.btn_rangkuman:
                showRangkuman();
                break;
        }

    }

    private void showRangkuman() {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setTitle("Tugas "+materi.getTitle());

        builder.setMessage(materi.getRangkuman());

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

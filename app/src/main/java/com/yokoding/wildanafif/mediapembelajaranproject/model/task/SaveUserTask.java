package com.yokoding.wildanafif.mediapembelajaranproject.model.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.yokoding.wildanafif.mediapembelajaranproject.model.User;
import com.yokoding.wildanafif.mediapembelajaranproject.ui.fragment.MainFragment;
import com.yokoding.wildanafif.mediapembelajaranproject.ui.fragment.MateriFragment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._SAVE_USER;

/**
 * Created by wildan afif on 6/2/2017.
 */

public class SaveUserTask extends AsyncTask<String, Void, String> {
    private Context context;
    private User user;
    private MainFragment materiFragment;

    public SaveUserTask(Context context, User user, MainFragment materiFragment) {
        this.context = context;
        this.user = user;
        this.materiFragment = materiFragment;
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url= _SAVE_USER;
        String method=params[0];
        if (method.equals("register")){

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data= URLEncoder.encode("kode_absen","UTF-8")+"="+URLEncoder.encode(String.valueOf(this.user.getKode_absen()),"UTF-8")+"&"+
                        URLEncoder.encode("nama","UTF-8")+"="+URLEncoder.encode(this.user.getNama(),"UTF-8")+"&";
                bufferedWriter.write(data);
                bufferedWriter.flush();
                //bufferedWriter.close();
                //OS.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                //IS.close();

                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String respone="";
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    respone+=line;
                }


                return respone;



            }catch (MalformedURLException e){

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        this.user.setId(Integer.valueOf(s));
        this.materiFragment.close(this.user);
    }
}

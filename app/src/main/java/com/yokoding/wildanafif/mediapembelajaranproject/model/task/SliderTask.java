package com.yokoding.wildanafif.mediapembelajaranproject.model.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.yokoding.wildanafif.mediapembelajaranproject.model.Slider;
import com.yokoding.wildanafif.mediapembelajaranproject.ui.fragment.MainFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._HOST;
import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._SLIDER;
import static com.yokoding.wildanafif.mediapembelajaranproject.model.Request_addr._URL_GAMBAR;

/**
 * Created by wildan afif on 5/31/2017.
 */

public class SliderTask extends AsyncTask<String, String, String> {
    private Context context;
    private Slider slider;
    MainFragment mainFragment;
    private URL url;
    private HttpURLConnection conn;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    public SliderTask(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            //Toast.makeText(context, _HOST, Toast.LENGTH_SHORT).show();
            //this.url=new URL(_HOST+"iklan/get?key=1234&provinsi=Jawa%20Timur");
            this.url=new URL("http://developper.otomotifstore.com/iklan/get?provinsi=Jawa%20Timur&key=1234");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e.toString();
        }
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return e1.toString();
        }
        try {
            int response_code=conn.getResponseCode();
            if (response_code==HttpURLConnection.HTTP_OK){
                InputStream input= this.conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result= new StringBuilder();
                String line;
                while ((line= reader.readLine())!=null){
                    result.append(line);
                }
                return result.toString();
            }else{
                return "Unsuccessfull "+response_code;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }finally {
            this.conn.disconnect();
        }

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(mainFragment.getContext(), ""+s, Toast.LENGTH_LONG).show();
        try {
            JSONArray jsonArray= new JSONArray(s);
                for(int i=0;i<jsonArray.length();i++){

                    JSONObject json_data = jsonArray.getJSONObject(i);
                    System.out.println(json_data.getString("judul"));
                      this.mainFragment.file_maps.put(json_data.getString("judul"), _URL_GAMBAR+json_data.getString("gambar"));
                }

                this.mainFragment.setSlider();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

package com.yokoding.wildanafif.mediapembelajaranproject.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yokoding.wildanafif.mediapembelajaranproject.model.User;

import static android.provider.BaseColumns._ID;
import static com.yokoding.wildanafif.mediapembelajaranproject.model.database.DatabaseContract._DATABASE_VERSION;
import static com.yokoding.wildanafif.mediapembelajaranproject.model.database.DatabaseContract._DB_NAME;
import static com.yokoding.wildanafif.mediapembelajaranproject.model.database.DatabaseContract._TABLE_USER;

/**
 * Created by wildan afif on 6/2/2017.
 */

public class UserHandler extends SQLiteOpenHelper {
    private static final String CREATE_TABLE_USER="CREATE TABLE "+_TABLE_USER+"("+_ID+" INTEGER PRIMARY KEY , nama VARCHAR(255), kode_absen  INTEGER(11));";
    private static final String DROP_TABLE_MEBER="DROP TABLE  IF EXISTS "+_TABLE_USER;
    public UserHandler(Context context) {
        super(context, _DB_NAME, null, _DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(this.DROP_TABLE_MEBER);
        onCreate(db);
    }
    public void create(User user){

        SQLiteDatabase db = this.getWritableDatabase();
        this.onUpgrade(db,1,2);

        ContentValues values = new ContentValues();


        values.put(_ID, user.getId());
        values.put("nama",user.getNama());
        values.put("kode_absen",user.getKode_absen());



        // Inserting Row
        db.insert(_TABLE_USER, null, values);
        db.close(); // Closing database connection
    }
    public User getUser(){
        User m=null;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+_TABLE_USER,null);
        if (cursor.moveToFirst()){
            m=new User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2)

            );

        }
        return m;
    }
    public void deleteall() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(_TABLE_USER,null,null);
        db.close();
    }


}

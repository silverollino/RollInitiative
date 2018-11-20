package com.example.dopaminastudios.myapplication.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dopaminastudios.myapplication.model.Campaign;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context){
        super(context, Constants.DATABASE_NAME,null, Constants.DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Constants.CREATE_CAMPAIGN);
            db.execSQL(Constants.CREATE_ENCOUNTER);
            db.execSQL(Constants.CREATE_PLAYER);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(Constants.DROP_CAMPAIGN_TABLE);
            onCreate(db);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addCampaign(Campaign campaign){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_CAMPAIGN_NAME, campaign.getCampaing());

        db.insert(Constants.TABLE_CAMPAIGN,null,values);
        db.close();
    }

    public boolean chkCampaign(String campaign){
        String[] columns ={
                Constants.COLUMN_CAMPAIGN_NAME
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = Constants.COLUMN_CAMPAIGN_NAME + " = ?";
        String[] selectionArgs = { campaign };

        Cursor cursor = db.query(Constants.TABLE_CAMPAIGN,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0){
            return true;
        }
        return false;
    }

    public Cursor viewAllPlayer(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + Constants.TABLE_PLAYER;
        Cursor cursor = database.rawQuery(query,null);

        return cursor;
    }
}

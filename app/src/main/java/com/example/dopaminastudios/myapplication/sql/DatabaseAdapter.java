package com.example.dopaminastudios.myapplication.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.dopaminastudios.myapplication.model.Encounter;
import com.example.dopaminastudios.myapplication.model.Player;

import java.util.ArrayList;

public class DatabaseAdapter {

    Context context;
    SQLiteDatabase db;
    DatabaseHelper databaseHelper;


    //INICAR EL HELPER Y PASARLO AL CONTEXTO
    public DatabaseAdapter(Context context){
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    //Salvar Datos
    public boolean savePlayer(Player player){
        try {
            db =databaseHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.COLUMN_NAME_PLAYER,player.getPlayerName());
            contentValues.put(Constants.COLUMN_CATEGORY_PLAYER,player.getCategory());

            long result = db.insert(Constants.TABLE_PLAYER,null, contentValues);
            //long result = db.insert(Constants.TABLA, Constants.COLUMNA, VALORES);

            if(result > 0){
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            databaseHelper.close();
        }
        return false;
    }

    //Sacar los valores de la BD y guardarlos en un arraylist
    //Regresar la lista

    public ArrayList<Player> retrievePlayer(String category){
        ArrayList<Player> players = new ArrayList<>();

        try {
            db = databaseHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM "
            + Constants.TABLE_PLAYER
            + " WHERE "
            + Constants.COLUMN_CATEGORY_PLAYER
            + " = '"
            + category
            + "'",null);


            Player player;
            players.clear();

            while (cursor.moveToNext()){
                String player_name = cursor.getString(1);
                String player_category = cursor.getString(2);
                //String campaign_class = cursor.getString(2);

                player = new Player();
                player.setPlayerName(player_name);
                player.setCategory(player_category);
                //modelo.setAtributo(modelo_atributo);

                players.add(player);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            databaseHelper.close();
        }
        return players;
    }

    public ArrayList<Player> retrieveAll(){
        ArrayList<Player> players = new ArrayList<>();

        try {
            db = databaseHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM "+ Constants.TABLE_PLAYER,null);


            Player player;
            players.clear();

            while (cursor.moveToNext()){
                String player_name = cursor.getString(1);
                String player_category = cursor.getString(2);
                //String campaign_class = cursor.getString(2);

                player = new Player();
                player.setPlayerName(player_name);
                player.setCategory(player_category);
                //modelo.setAtributo(modelo_atributo);

                players.add(player);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            databaseHelper.close();
        }
        return players;
    }
}

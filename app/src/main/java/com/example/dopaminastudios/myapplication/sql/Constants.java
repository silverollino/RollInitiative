package com.example.dopaminastudios.myapplication.sql;

public class Constants {


    //PROPIEDADES DE LA BD
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "RollInitiative.db";

    //TABLAS
     static final String TABLE_CAMPAIGN = "CAMPAIGN";
     static final String TABLE_ENCOUNTER = "ENCOUNTER";
     static final String TABLE_ENEMY = "ENEMY";
     static final String TABLE_PLAYER = "PLAYER";
     static final String TABLE_ENEMY_HAS_ENCOUNTER = "ENEMY_HAS_ENCOUNTER";
     static final String TABLE_PLAYER_HAS_ENCOUNTER = "PLAYER_HAS_ENCOUNTER";

    //COLUMNAS

    //CAMPAÑA
     static final String COLUMN_CAMPAIGN_ID = "idCampaign";
     static final String COLUMN_CAMPAIGN_NAME = "Campaign_name";

    //ENCUENTRO
     static final String COLUMN_ID_ENCOUNTER ="idEncounter";
     static final String COLUMN_NAME_ENCOUNTER ="Name";
     static final String COLUMN_ENCOUNTER_CAMPAIGN ="Campaign_idCampaign";

    //ENEMIGO
     static final String COLUMN_ID_ENEMY ="idEnemy";
     static final String COLUMN_NAME_ENEMY ="Name";

    //JUGADOR
     static final String COLUMN_ID_PLAYER ="idPlayers";
     static final String COLUMN_NAME_PLAYER ="Name";
    static final String COLUMN_CATEGORY_PLAYER ="Category";

    //M-M JUGADO-ENCUENTRO
     static final String COLUMN_PLAYER_HAS_ENCOUNTER = "players_idplayers";
     static final String COLUMN_ENCOUNTER_HAS_PLAYER = "Encounter_idEncounter";

    //M-M ENEMIGO-ENCUENTRO
     static final String COLUMN_ENEMY_HAS_ENCOUNTER = "Enemy_idEnemy";
     static final String COLUMN_ENCOUNTER_HAS_ENEMY = "Encounter_idEncounter";

    //BORRADO DE TABLAS

    static final String DROP_CAMPAIGN_TABLE = "DROP TABLE IF EXISTS "+ TABLE_CAMPAIGN;
    static final String DROP_ENCOUNTER_TABLE = "DROP TABLE IF EXISTS " + TABLE_ENCOUNTER;
    static final String DROP_PLAYER_TABLE = "DROP TABLE IF EXISTS " + TABLE_PLAYER;
    static final String DROP_ENEMY_TABLE = "DROP TABLE IF EXISTS "+ TABLE_ENEMY;
    static final String DROP_P_HAS_ENC_TABLE = "DROP TABLE IF EXISTS "+ TABLE_PLAYER_HAS_ENCOUNTER;
    static final String DROP_E_HAS_ENC_TABLE= "DROP TABLE IF EXISTS "+ TABLE_ENEMY_HAS_ENCOUNTER;


    //CREACION DE LAS TABLAS

    static final String CREATE_CAMPAIGN = "CREATE TABLE IF NOT EXISTS "
            + TABLE_CAMPAIGN
            + "("
            + COLUMN_CAMPAIGN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CAMPAIGN_NAME + " TEXT NOT NULL);";

    static final String CREATE_ENCOUNTER = "CREATE TABLE IF NOT EXISTS "
            +TABLE_ENCOUNTER
            +"("
            + COLUMN_ID_ENCOUNTER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME_ENCOUNTER + " TEXT);";

    static final String CREATE_PLAYER = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PLAYER
            + "("
            + COLUMN_ID_PLAYER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME_PLAYER + " TEXT NOT NULL, "
            + COLUMN_CATEGORY_PLAYER + " TEXT NOT NULL);";
}

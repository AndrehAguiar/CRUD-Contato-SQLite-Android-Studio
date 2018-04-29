package com.example.topartes.crudcontato.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "CRUDContato.DB";

    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS contato" +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT," +
                " email TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Configura BACKUP AQUI

        String sql = "DROP TABLE IF EXISTS contato";
        db.execSQL(sql);
        onCreate(db);

    }
}

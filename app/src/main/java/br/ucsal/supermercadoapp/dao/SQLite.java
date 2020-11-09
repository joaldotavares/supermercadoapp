package br.ucsal.supermercadoapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SQLite extends SQLiteOpenHelper {

    public SQLite(Context context) {
        super(context, "bancoSupermercado.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PRODUTOS(ID INT, QUANTIDADE INT, PRODUTO STRING, VALOR REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      /*  switch (oldVersion){
            case 1:
                db.execSQL();
        }*/
    }
}

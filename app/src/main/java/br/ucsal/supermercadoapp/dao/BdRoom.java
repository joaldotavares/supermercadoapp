package br.ucsal.supermercadoapp.dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import br.ucsal.supermercadoapp.model.Produto;

@Database(entities = {Produto.class},version = 1, exportSchema = false)
public abstract class BdRoom extends RoomDatabase {

    public abstract ProdutoRoomDAO getProdutoRoomDAO();

    public static BdRoom getInstance(Context contexto){
        return Room.databaseBuilder(contexto,BdRoom.class, "supermercado.db").allowMainThreadQueries().build();

    }
}

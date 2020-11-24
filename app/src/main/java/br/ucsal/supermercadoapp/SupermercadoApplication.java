package br.ucsal.supermercadoapp;

import android.app.Application;

import androidx.room.Room;

import br.ucsal.supermercadoapp.dao.BdRoom;
import br.ucsal.supermercadoapp.model.Produto;

public class SupermercadoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BdRoom bdRoom = Room.databaseBuilder(this,BdRoom.class, "supermercado.db").build();
        Produto p = new Produto(2, "Produto 2", 4.9);
        //p.setId("4");
        //BdRoom.getInstance(this).getProdutoRoomDAO().adicionar(p);

    }
}

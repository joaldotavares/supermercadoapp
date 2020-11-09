package br.ucsal.supermercadoapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.ucsal.supermercadoapp.model.Produto;

@Dao
public interface ProdutoRoomDAO {

    @Insert
    public void adicionar(Produto tarefa);

    @Query("SELECT * FROM PRODUTOS")
    public List<Produto> lista();

    //public List<Produto> listarPorNome();

    //public List<Produto> listarPorPreco();

    @Delete
    public void remove(Produto tarefa);


//    public Double somaTotalItens();
}

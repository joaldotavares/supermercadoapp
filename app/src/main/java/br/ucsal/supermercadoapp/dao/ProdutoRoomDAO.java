package br.ucsal.supermercadoapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.ucsal.supermercadoapp.model.Produto;

@Dao
public interface ProdutoRoomDAO {

    @Insert
    public void adicionar(Produto produto);

    @Query("SELECT * FROM PRODUTOS")
    public List<Produto> lista();

    @Query("SELECT * FROM PRODUTOS ORDER BY PRODUTO")
    public List<Produto> listarPorNome();

    @Query("SELECT * FROM PRODUTOS ORDER BY VALOR")
    public List<Produto> listarPorPreco();

    @Delete
    public void remove(Produto produto);


    @Update
    public void editar(Produto produto);


//    public Double somaTotalItens();
}

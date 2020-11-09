package br.ucsal.supermercadoapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import br.ucsal.supermercadoapp.model.Produto;


public class ProdutoDAO {

    private static List<Produto> tarefas = new ArrayList<>();
    private static Double somaTotal = 0.0;
    SQLite bd;

    public ProdutoDAO(Context context) {
        this.bd = new SQLite(context);
    }

    public ProdutoDAO() {

    }

    public void adicionar(Produto tarefa){
        ContentValues values= new ContentValues();
        values.put("id", tarefa.getId());
        values.put("produto", tarefa.getProduto());
        values.put("valor", tarefa.getValor());
        values.put("quantidade", tarefa.getQuantidade());



        if(tarefa.getId() == null) {
            tarefa.setId(UUID.randomUUID().toString());
            //tarefas.add(tarefa);
            bd.getWritableDatabase().insert("produtos", null, values);
            somaTotal = somaTotal+ tarefa.getValor()*tarefa.getQuantidade();
        }else{
            String[] param = {String.valueOf(tarefa.getId())};
            bd.getWritableDatabase().update("produtos", values, "id=?", param);
            /*for (Produto t : tarefas) {
                if(t.getId().equals(tarefa.getId())){
                    somaTotal = somaTotal- t.getValor()*t.getQuantidade();
                    t.setQuantidade(tarefa.getQuantidade());
                    t.setProduto(tarefa.getProduto());
                    t.setValor(tarefa.getValor());
                    somaTotal = somaTotal + t.getValor()*t.getQuantidade();
                }
            }*/
        }
    }

    public List<Produto> lista(){

        Cursor cursor =bd.getReadableDatabase().rawQuery("select * from produtos", new String[] {"1"});
        while(cursor.moveToNext()){
            Produto p = new Produto();
            p.setId(cursor.getString(cursor.getColumnIndex("id")));
            p.setProduto(cursor.getString(cursor.getColumnIndex("produto")));
            p.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
            p.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
        }
        return new ArrayList<>(tarefas);
    }

    public List<Produto> listarPorNome(){
        Collections.sort(tarefas);
        return new ArrayList<>(tarefas);
    }

    public List<Produto> listarPorPreco(){
        Comparator<Produto> comparatorPreco = new Comparator<Produto>() {
            @Override
            public int compare(Produto t1, Produto t2) {
                return t1.getValor().compareTo(t2.getValor());
            }
        };
        Collections.sort(tarefas, comparatorPreco);
        return new ArrayList<>(tarefas);
    }

    public void remove(Produto tarefa) {
        String[] param = {String.valueOf(tarefa.getId())};
        bd.getWritableDatabase().delete("produtos", "id=?", param);
       /* if(tarefa != null) {
            somaTotal = somaTotal- tarefa.getValor()*tarefa.getQuantidade();
            tarefas.remove(tarefa);
        }*/
    }

    public Double somaTotalItens(){
        return somaTotal;
    }
}
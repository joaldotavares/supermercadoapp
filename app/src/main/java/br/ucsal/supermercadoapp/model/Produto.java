package br.ucsal.supermercadoapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "PRODUTOS")
public class Produto implements Serializable, Comparable<Produto> {

    @NonNull
    @PrimaryKey
    private String id;

    private Integer quantidade;
    private String produto;
    private Double valor;



    public Produto(){

    }

    @Ignore
    public Produto(Integer quantidade, String produto, double valor){
        this.quantidade = quantidade;
        this.produto = produto;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Quantidade: " + quantidade +
                "  |  Produto: " + produto +
                "  |  Valor: " + valor;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getProduto() {
        return produto;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Produto p) {
        return produto.compareTo(p.produto);
    }
}

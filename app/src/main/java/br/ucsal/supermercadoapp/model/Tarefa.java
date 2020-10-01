package br.ucsal.supermercadoapp.model;

import java.io.Serializable;
import java.util.UUID;

public class Tarefa implements Serializable {

    private Integer quantidade;
    private String produto;
    private double valor;

    private String id;

    public Tarefa(Integer quantidade, String produto, double valor){
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

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

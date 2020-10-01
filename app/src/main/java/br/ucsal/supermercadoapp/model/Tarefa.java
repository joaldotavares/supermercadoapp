package br.ucsal.supermercadoapp.model;

public class Tarefa {

    private Integer quantidade;
    private String produto;
    private Double valor;

    public Tarefa(Integer quantidade, String produto, Double valor){
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
}

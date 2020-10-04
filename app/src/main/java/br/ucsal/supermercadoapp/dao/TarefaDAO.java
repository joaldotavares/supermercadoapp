package br.ucsal.supermercadoapp.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import br.ucsal.supermercadoapp.model.Tarefa;

public class TarefaDAO {

    private static List<Tarefa> tarefas = new ArrayList<>();
    private static Double somaTotal = 0.0;

    public void adicionar(Tarefa tarefa){
        if(tarefa.getId() == null) {
            tarefa.setId(UUID.randomUUID().toString());
            tarefas.add(tarefa);
            somaTotal = somaTotal+ tarefa.getValor()*tarefa.getQuantidade();
        }else{
            for (Tarefa t : tarefas) {
                if(t.getId().equals(tarefa.getId())){
                    somaTotal = somaTotal- t.getValor()*t.getQuantidade();
                    t.setQuantidade(tarefa.getQuantidade());
                    t.setProduto(tarefa.getProduto());
                    t.setValor(tarefa.getValor());
                    somaTotal = somaTotal + t.getValor()*t.getQuantidade();
                }
            }
        }
    }

    public List<Tarefa> lista(){
        return new ArrayList<>(tarefas);
    }

    public List<Tarefa> listarPorNome(){
        Collections.sort(tarefas);
        return new ArrayList<>(tarefas);
    }

    public List<Tarefa> listarPorPreco(){
        Comparator<Tarefa> comparatorPreco = new Comparator<Tarefa>() {
            @Override
            public int compare(Tarefa t1, Tarefa t2) {
                return t1.getValor().compareTo(t2.getValor());
            }
        };
        Collections.sort(tarefas, comparatorPreco);
        return new ArrayList<>(tarefas);
    }

    public void remove(Tarefa tarefa) {
        if(tarefa != null) {
            somaTotal = somaTotal- tarefa.getValor()*tarefa.getQuantidade();
            tarefas.remove(tarefa);
        }
    }

    public Double somaTotalItens(){
        return somaTotal;
    }
}
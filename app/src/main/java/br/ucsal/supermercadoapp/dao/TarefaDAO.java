package br.ucsal.supermercadoapp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.ucsal.supermercadoapp.model.Tarefa;

public class TarefaDAO {

    private static List<Tarefa> tarefas = new ArrayList<>();

    public void adicionar(Tarefa tarefa){
        if(tarefa.getId() == null) {
            tarefa.setId(UUID.randomUUID().toString());
            tarefas.add(tarefa);
        }else{
            for (Tarefa t : tarefas) {
                if(t.getId().equals(tarefa.getId())){
                    t.setQuantidade(tarefa.getQuantidade());
                    t.setProduto(tarefa.getProduto());
                    t.setValor(tarefa.getValor());
                }
            }
        }
    }

    public List<Tarefa> lista(){
        return new ArrayList<>(tarefas);
    }

    public void remove(Tarefa tarefa) {
        if(tarefa != null) {
            tarefas.remove(tarefa);
        }
    }
}

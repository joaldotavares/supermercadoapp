package br.ucsal.supermercadoapp.dao;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.supermercadoapp.model.Tarefa;

public class TarefaDAO {

    private static List<Tarefa> tarefas = new ArrayList<>();

    public void adicionar(Tarefa tarefa){
        tarefas.add(tarefa);
    }

    public List<Tarefa> lista(){
        return new ArrayList<>(tarefas);
    }

}

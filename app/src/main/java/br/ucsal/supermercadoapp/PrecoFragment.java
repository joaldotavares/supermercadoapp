package br.ucsal.supermercadoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.List;

import br.ucsal.supermercadoapp.dao.TarefaDAO;
import br.ucsal.supermercadoapp.model.Tarefa;

public class PrecoFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<Tarefa> listAdapter;
    private final TarefaDAO dao = new TarefaDAO();
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       /* listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView = listView.findViewById(R.id.lista);
        listView.setAdapter(listAdapter);*/
        view = inflater.inflate(R.layout.fragment_preco, container, false);

        // Aqui você instancia sua ListView
        ListView list = (ListView) view.findViewById(R.id.lista);
        List<Tarefa> tarefas = dao.listarPorPreco(); // Obtenha sua lista de objetos aqui

       // CarsAdapter carsAdapter = new carsAdapter(getActivity(), cars);
        listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tarefas);
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefa = (Tarefa) parent.getItemAtPosition(position);
                editar(tarefa);
            }
        });

        return view;
        //return inflater.inflate(R.layout.fragment_preco, listView, false);
    }
    public void editar(Tarefa tarefa){
        Intent intent = new Intent(getActivity(), TarefaActivity.class);
        intent.putExtra("Tarefa", tarefa);
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();

        ListView list = (ListView) view.findViewById(R.id.lista);
        List<Tarefa> tarefas = dao.listarPorPreco(); // Obtenha sua lista de objetos aqui

        // CarsAdapter carsAdapter = new carsAdapter(getActivity(), cars);
        listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tarefas);
        list.setAdapter(listAdapter);


    }

    public static PrecoFragment newInstance() {
        return new PrecoFragment();
    }

}

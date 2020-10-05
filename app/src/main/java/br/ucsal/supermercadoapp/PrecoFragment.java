package br.ucsal.supermercadoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        return view;
        //return inflater.inflate(R.layout.fragment_preco, listView, false);
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

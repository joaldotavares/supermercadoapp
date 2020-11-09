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


import br.ucsal.supermercadoapp.dao.BdRoom;
import br.ucsal.supermercadoapp.dao.ProdutoDAO;
import br.ucsal.supermercadoapp.model.Produto;


public class PrecoFragment extends Fragment {

    private ArrayAdapter<Produto> listAdapter;
    private final ProdutoDAO dao = new ProdutoDAO();
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_preco, container, false);

         ListView list = (ListView) view.findViewById(R.id.lista);
        //List<Produto> tarefas = dao.listarPorPreco(); // Obtenha sua lista de objetos aqui
        List<Produto> tarefas = BdRoom.getInstance(getContext()).getProdutoRoomDAO().lista();
        listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tarefas);
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto tarefa = (Produto) parent.getItemAtPosition(position);
                editar(tarefa);
            }
        });

        return view;
    }

    public void editar(Produto tarefa){
        Intent intent = new Intent(getActivity(), ProdutoActivity.class);
        intent.putExtra("Tarefa", tarefa);
        startActivity(intent);
    }



    @Override
    public void onResume() {
        super.onResume();

        ListView list = (ListView) view.findViewById(R.id.lista);
        List<Produto> tarefas = BdRoom.getInstance(getContext()).getProdutoRoomDAO().lista();

        listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tarefas);
        list.setAdapter(listAdapter);


    }


    public static PrecoFragment newInstance() {
        return new PrecoFragment();
    }

}

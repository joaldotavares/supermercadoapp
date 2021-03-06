package br.ucsal.supermercadoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.view.ContextMenu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.List;

import br.ucsal.supermercadoapp.dao.ProdutoDAO;
import br.ucsal.supermercadoapp.model.Produto;


public class ProdutoFragment extends Fragment  {
    private ListView list;
    private ArrayAdapter<Produto> listAdapter;
    private final ProdutoDAO dao = new ProdutoDAO();
    private View view;

    private BaseAdapter baseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_nome, container, false);
        list =  view.findViewById(R.id.lista);

        baseAdapter = new ProdutoAdapter(getActivity());
        list.setAdapter(baseAdapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto tarefa = (Produto) parent.getItemAtPosition(position);
                editar(tarefa);
            }
        });
        registerForContextMenu(list);

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //getMenuInflater().inflate(R.menu.activity_main_menu_context, menu);
    }

    private void populateList() {
        ListView saveListView = (ListView)getActivity().findViewById(R.id.lista);
        baseAdapter = new ProdutoAdapter(getContext());
        saveListView.setAdapter(baseAdapter);
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
        List<Produto> tarefas = dao.listarPorNome(); // Obtenha sua lista de objetos aqui
        registerForContextMenu(list);
        listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tarefas);
        list.setAdapter(listAdapter);

    }


    public static ProdutoFragment newInstance() {
        return new ProdutoFragment();
    }

}

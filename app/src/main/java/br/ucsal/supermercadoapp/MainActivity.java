package br.ucsal.supermercadoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.ucsal.supermercadoapp.dao.TarefaDAO;
import br.ucsal.supermercadoapp.model.Tarefa;

public class MainActivity extends AppCompatActivity {

    private final TarefaDAO dao = new TarefaDAO();
    private ListView listView;
    private FloatingActionButton fba;

    private ArrayAdapter<Tarefa> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        setTitle("Lista de compras");

        fba = findViewById(R.id.fba);

        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView = findViewById(R.id.lista);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefa = (Tarefa) parent.getItemAtPosition(position);
                editar(tarefa);
            }
        });
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Remover");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Tarefa tarefa = (Tarefa) listAdapter.getItem(menuInfo.position);
        dao.remove(tarefa);
        listAdapter.remove(tarefa);

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listAdapter.clear();
        listAdapter.addAll(dao.lista());
    }

    public void editar(Tarefa tarefa){
        Intent intent = new Intent(MainActivity.this, TarefaActivity.class);
        intent.putExtra("Tarefa", tarefa);
        startActivity(intent);
    }

    public void click(View view){
        Intent intent = new Intent(this, TarefaActivity.class);
        startActivity(intent);
    }
}
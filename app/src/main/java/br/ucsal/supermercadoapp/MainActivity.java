package br.ucsal.supermercadoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ucsal.supermercadoapp.dao.TarefaDAO;
import br.ucsal.supermercadoapp.model.Tarefa;

public class MainActivity extends AppCompatActivity {

    private final TarefaDAO dao = new TarefaDAO();
    private ListView listView;
    private FloatingActionButton fba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        setTitle("Lista de compras");

        fba = findViewById(R.id.fba);
        listView = findViewById(R.id.lista);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.lista());

        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefa = (Tarefa) parent.getItemAtPosition(position);
                editar(tarefa);
                Log.i("Tarefa", "Posição " + position);
            }
        });

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
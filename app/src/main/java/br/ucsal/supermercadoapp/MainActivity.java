package br.ucsal.supermercadoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ucsal.supermercadoapp.dao.TarefaDAO;

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

    }

    public void click(View view){
        Intent intent = new Intent(this, TarefaActivity.class);
        startActivity(intent);
    }
}
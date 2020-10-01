package br.ucsal.supermercadoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ucsal.supermercadoapp.dao.TarefaDAO;
import br.ucsal.supermercadoapp.model.Tarefa;

public class TarefaActivity extends AppCompatActivity {

    private EditText quantidade;
    private EditText produto;
    private EditText valor;
    private final TarefaDAO dao = new TarefaDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);
        setTitle("Novo produto");
        quantidade = findViewById(R.id.tarefa_activity_quantidade);
        produto = findViewById(R.id.tarefa_activity_produto);
        valor = findViewById(R.id.tarefa_activity_valor);
    }


    public void salvar(View view){
        Tarefa tarefa = new Tarefa(Integer.parseInt(quantidade.getText().toString()), produto.getText().toString(), Double.parseDouble(valor.getText().toString()));
        Log.i("Tarefa", tarefa.toString());
        dao.adicionar(tarefa);
        finish();
    }
}
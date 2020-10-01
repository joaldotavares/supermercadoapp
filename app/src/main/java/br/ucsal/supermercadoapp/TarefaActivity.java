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
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);
        quantidade = findViewById(R.id.tarefa_activity_quantidade);
        produto = findViewById(R.id.tarefa_activity_produto);
        valor = findViewById(R.id.tarefa_activity_valor);

        if(getIntent().hasExtra("Tarefa")) {
            setTitle("Editar produto");
            tarefa = (Tarefa) getIntent().getSerializableExtra("Tarefa");
            //Log.i("Tarefa", tarefa.toString());
            quantidade.setText(tarefa.getQuantidade() +"");
            produto.setText(tarefa.getProduto());
            valor.setText(tarefa.getValor() + "");
        }else{
            setTitle("Novo produto");
        }
    }

    public void salvar(View view){
        if(tarefa == null) {
            tarefa = new Tarefa(Integer.parseInt(quantidade.getText().toString()), produto.getText().toString(), Double.parseDouble(valor.getText().toString()));
            //Log.i("Tarefa", tarefa.toString());
        }else{
            tarefa.setQuantidade(Integer.parseInt(quantidade.getText().toString()));
            tarefa.setProduto(produto.getText().toString());
            tarefa.setValor(Double.parseDouble(valor.getText().toString()));
        }
        dao.adicionar(tarefa);
        finish();
    }
}
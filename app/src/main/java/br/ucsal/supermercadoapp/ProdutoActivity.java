package br.ucsal.supermercadoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ucsal.supermercadoapp.dao.BdRoom;
import br.ucsal.supermercadoapp.dao.ProdutoDAO;
import br.ucsal.supermercadoapp.model.Produto;


public class ProdutoActivity extends AppCompatActivity {

    private EditText quantidade;
    private EditText produto;
    private EditText valor;
    private final ProdutoDAO dao = new ProdutoDAO();
    private Produto tarefa;
    private Button btnRemover;
    boolean show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        quantidade = findViewById(R.id.tarefa_activity_quantidade);
        produto = findViewById(R.id.tarefa_activity_produto);
        valor = findViewById(R.id.tarefa_activity_valor);
        btnRemover = findViewById(R.id.tarefa_activity_remover);


        tarefa = (Produto) getIntent().getSerializableExtra("Tarefa");
        if(getIntent().hasExtra("Tarefa")) {
            setTitle("Editar produto");

            //Log.i("Tarefa", tarefa.toString());
            quantidade.setText(tarefa.getQuantidade() +"");
            produto.setText(tarefa.getProduto());
            valor.setText(tarefa.getValor() + "");
            btnRemover.setVisibility(View.VISIBLE);
            show = true;
        }else{
            setTitle("Novo produto");
            btnRemover.setVisibility(View.INVISIBLE);
            show = false;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getIntent().hasExtra("Tarefa")) {
            btnRemover.setVisibility(View.VISIBLE);
            show = true;
        }else{
            setTitle("Novo produto");
            btnRemover.setVisibility(View.INVISIBLE);
            show = false;
        }
    }

    public void salvar(View view){
        if(tarefa == null) {
            tarefa = new Produto(Integer.parseInt(quantidade.getText().toString()), produto.getText().toString(), Double.parseDouble(valor.getText().toString()));
            BdRoom.getInstance(this).getProdutoRoomDAO().adicionar(tarefa);
            //Log.i("Tarefa", tarefa.toString());
        }else{
            tarefa.setQuantidade(Integer.parseInt(quantidade.getText().toString()));
            tarefa.setProduto(produto.getText().toString());
            tarefa.setValor(Double.parseDouble(valor.getText().toString()));
        }
        //dao.adicionar(tarefa);
        BdRoom.getInstance(this).getProdutoRoomDAO().adicionar(tarefa);
        finish();
    }

    public void remover(View view){
        //dao.remove(tarefa);
        BdRoom.getInstance(this).getProdutoRoomDAO().remove(tarefa);
        finish();
    }
}
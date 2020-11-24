package br.ucsal.supermercadoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

import br.ucsal.supermercadoapp.dao.BdRoom;
import br.ucsal.supermercadoapp.dao.ProdutoDAO;
import br.ucsal.supermercadoapp.model.Produto;


public class ProdutoActivity extends AppCompatActivity {

    private EditText quantidade;
    private EditText produto;
    private EditText valor;
    private final ProdutoDAO dao = new ProdutoDAO();
    private Produto produto2;
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


        produto2 = (Produto) getIntent().getSerializableExtra("Tarefa");
        if(getIntent().hasExtra("Tarefa")) {
            setTitle("Editar produto");

            //Log.i("Tarefa", tarefa.toString());
            quantidade.setText(produto2.getQuantidade() +"");
            produto.setText(produto2.getProduto());
            valor.setText(produto2.getValor() + "");
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
        if(produto2 == null) {
            produto2 = new Produto(Integer.parseInt(quantidade.getText().toString()), produto.getText().toString(), Double.parseDouble(valor.getText().toString()));
            produto2.setId(UUID.randomUUID().toString());
            Log.i("Tarefa", produto2.toString());
            BdRoom.getInstance(this).getProdutoRoomDAO().adicionar(produto2);

        }else{
            produto2.setQuantidade(Integer.parseInt(quantidade.getText().toString()));
            produto2.setProduto(produto.getText().toString());
            produto2.setValor(Double.parseDouble(valor.getText().toString()));
            BdRoom.getInstance(this).getProdutoRoomDAO().editar(produto2);
        }
        //dao.adicionar(tarefa);
        //BdRoom.getInstance(this).getProdutoRoomDAO().adicionar(produto2);
        finish();
    }

    public void remover(View view){
        //dao.remove(tarefa);
        BdRoom.getInstance(this).getProdutoRoomDAO().remove(produto2);
        finish();
    }
}
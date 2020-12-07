package br.ucsal.supermercadoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.ucsal.supermercadoapp.model.ProdutoApi;
import br.ucsal.supermercadoapp.util.ServicoDeProduto;

public class BuscarProdutoActivity extends AppCompatActivity {

    private Button btnBuscar;
    private EditText codigoDeBarrasText;
    private TextView valorMinimo;
    private TextView valorMaximo;
    private String stCodigoBarras;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscarproduto);
        setTitle("Buscar código de barras");

        btnBuscar = findViewById(R.id.btnBuscar);
        codigoDeBarrasText = findViewById(R.id.codigoDeBarras);
        valorMaximo = findViewById(R.id.valor_maximo);
        valorMinimo = findViewById(R.id.valor_minimo);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void buscarPorCodigo(View view){
        stCodigoBarras= codigoDeBarrasText.getText().toString();
        //String cod = "7898422747237";
        ProdutoApi produto;
        try {
            produto = ServicoDeProduto.buscarProdutoPeloCodigoBarras(stCodigoBarras);
            /*System.out.println("Descrição: " + produto.getDescription());
            System.out.println("Preço Mánimo: " + produto.getMin_price());
            System.out.println("Preço Míximo: " + produto.getMax_price());
            System.out.println("Imagem: " + produto.getAvg_price());*/
            Log.i("Produto",produto.getDescription());
            valorMaximo.setText("Preço Mánimo: " + produto.getMax_price());
            valorMinimo.setText("Preço Míximo: " + produto.getMin_price());

        } catch (Exception e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

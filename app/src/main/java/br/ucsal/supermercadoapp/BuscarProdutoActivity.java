package br.ucsal.supermercadoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;




public class BuscarProdutoActivity extends AppCompatActivity {

    private Button btnBuscar;
    private EditText codigoDeBarrasText;
    private TextView valorMinimo;
    private TextView valorMaximo;
    private String stCodigoBarras;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnBuscar = findViewById(R.id.btnBuscar);
        codigoDeBarrasText = findViewById(R.id.codigoDeBarras);
        valorMaximo = findViewById(R.id.valor_maximo);
        valorMinimo = findViewById(R.id.valor_minimo);


    }

    public void confirmar(View view){
        stCodigoBarras= codigoDeBarrasText.getText().toString();
    }
}

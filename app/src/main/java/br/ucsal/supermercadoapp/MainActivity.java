package br.ucsal.supermercadoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.ucsal.supermercadoapp.dao.ProdutoDAO;

import br.ucsal.supermercadoapp.model.Produto;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private final ProdutoDAO dao = new ProdutoDAO();
    //private ListView listView;
    private FloatingActionButton fba;
    private BottomNavigationView navigationView;

    private ArrayAdapter<Produto> listAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        setTitle("Lista de compras");

        fba = findViewById(R.id.fba);

     /*   listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView = findViewById(R.id.lista);
        listView.setAdapter(listAdapter);*/

        navigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(this);

      /*  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefa = (Tarefa) parent.getItemAtPosition(position);
                editar(tarefa);
            }
        });*/
        //registerForContextMenu(listView);

        Button calculoTotal = findViewById(R.id.calculoTotal);
        calculoTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Valor total");
                alerta.setIcon(R.mipmap.ic_launcher);
                String mensagem = Double.toString(dao.somaTotalItens());
                alerta.setMessage("Preço total da compra: "+mensagem+"R$");
                alerta.setCancelable(false);
                alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Cancelar escolhido", Toast.LENGTH_SHORT).show();
                    }
                });
                alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Ok escolhido", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = alerta.create();
                alertDialog.show();
            }
        });
        Fragment produtosFragment = ProdutoFragment.newInstance();
        openFragment(produtosFragment);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_main_menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.main_context_remove){
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Produto tarefa = (Produto) listAdapter.getItem(menuInfo.position);
            dao.remove(tarefa);
            listAdapter.remove(tarefa);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Fragment produtosFragment = ProdutoFragment.newInstance();
        openFragment(produtosFragment);

    }

    public void editar(Produto tarefa){
        Intent intent = new Intent(MainActivity.this, ProdutoActivity.class);
        intent.putExtra("Tarefa", tarefa);
        startActivity(intent);
    }

    public void click(View view){
        Intent intent = new Intent(this, ProdutoFragment.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_nome: {
                getSupportActionBar().setTitle("Lista de compras");
                Fragment produtosFragment = ProdutoFragment.newInstance();
                openFragment(produtosFragment);
                break;
            }
            case R.id.navigation_preco: {
                getSupportActionBar().setTitle("Lista de compras");
                Fragment produtosFragment = PrecoFragment.newInstance();
                openFragment(produtosFragment);
                break;
            }

        }

        return true;
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
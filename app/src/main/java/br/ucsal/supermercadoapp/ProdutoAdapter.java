package br.ucsal.supermercadoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.room.Room;

import java.util.List;

import br.ucsal.supermercadoapp.dao.BdRoom;
import br.ucsal.supermercadoapp.dao.ProdutoDAO;
import br.ucsal.supermercadoapp.model.Produto;

public class ProdutoAdapter extends BaseAdapter {
    //private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final List<Produto> produtos;
    private final Context context;

    public ProdutoAdapter(Context contexto){
        this.produtos = BdRoom.getInstance(contexto).getProdutoRoomDAO().lista();
        this.context = contexto;
    }

    @Override
    public int getCount() {

        return BdRoom.getInstance(context).getProdutoRoomDAO().lista().size();
    }

    @Override
    public Produto getItem(int i) {
        return BdRoom.getInstance(context).getProdutoRoomDAO().lista().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View nova = LayoutInflater.from(context).inflate(R.layout.produto_list_item,parent, false);
        Produto p = Room.databaseBuilder(context,BdRoom.class, "supermercado.db").build().getProdutoRoomDAO().lista().get(i);
        TextView quant = nova.findViewById(R.id.item_quantidade);
        quant.setText(p.getQuantidade());
        TextView preco =nova.findViewById(R.id.item_preco);
        preco.setText("R$ "+p.getValor());
        TextView produto =nova.findViewById(R.id.item_produto);
        produto.setText(p.getProduto());
        return nova;
    }
}

package br.com.fatecpg.brasileirao2016pdm;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tony on 27/11/16.
 */

public class ListBrAdapter extends BaseAdapter {

    private final List<ListBr> l;
    private final Activity act;


    public ListBrAdapter(List<ListBr> l, Activity act) {
        this.l = l;
        this.act = act;
    }

    //métodos

    @Override
    public int getCount() {
        return l.size();
    }

    @Override
    public Object getItem(int position) {
        return l.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = act.getLayoutInflater()
                .inflate(R.layout.layout_listbr, parent, false);


        ListBr l = this.l.get(position);


        //pegando as referências das Views
        TextView nome = (TextView)
                view.findViewById(R.id.txtTitle);
        TextView descricao = (TextView)
                view.findViewById(R.id.txtText);

        //populando as Views
        nome.setText(l.getTitle());
        descricao.setText(l.getText());
        //imagem.setImageResource(R.drawable.java);

        return view;
    }

}

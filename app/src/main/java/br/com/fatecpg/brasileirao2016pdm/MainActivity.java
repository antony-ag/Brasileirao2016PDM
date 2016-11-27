package br.com.fatecpg.brasileirao2016pdm;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<ListBr> list = new ArrayList<>();

        for(int i = 0; i< 20 ; i++){
            ListBr l = new ListBr();

            l.setTitle("Título "+i);
            l.setText("Texto "+i);

            list.add(l);
        }


        ListView listv = (ListView) findViewById(R.id.listView);

        //chamada da implementaçao do android:
        //ArrayAdapter<Curso> adapter = new ArrayAdapter<Curso>(this,
        //android.R.layout.simple_list_item_1, cursos);

        //chamada da nossa implementação
        ListBrAdapter adapter =
                new ListBrAdapter(list, this);

        listv.setAdapter(adapter);

    }

    public void forceUpdate(View view){

        ArrayList l = new ArrayList(){};

        for(int i = 0; i< 20; ++i) l.add("Texto "+ i);

        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, l);
        ListView list = (ListView) findViewById(R.id.listView);
        list.setDrawingCacheBackgroundColor(Color.RED);
        list.setAdapter(aa);
        //To DO
    }
}

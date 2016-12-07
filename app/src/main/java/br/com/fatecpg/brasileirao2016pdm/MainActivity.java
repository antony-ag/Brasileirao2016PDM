package br.com.fatecpg.brasileirao2016pdm;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class
MainActivity extends AppCompatActivity {

    private ArrayList<String> data = new ArrayList<>();
    private ArrayAdapter<String> listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = (ListView) findViewById(R.id.listView);
        data = new ArrayList<>();
        int listLayout = android.R.layout.simple_list_item_1;
        listViewAdapter = new ArrayAdapter<String>(this, listLayout, data);
        listView.setAdapter(listViewAdapter);

        updateList();

    }


    public void forceUpdate(View view){
        updateList();
    }

    public void updateList(){
        data.clear();
        data.add("Carregando...");
        listViewAdapter.notifyDataSetChanged();
        new DataReader(new AsyncResult() {
            @Override
            public void onResult(String response) {
                processData(response);
            }

            @Override
            public void onException(Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }

    public void processData (String UrlResponse){
        try{
            JSONObject jsonObj = new JSONObject(UrlResponse);
            JSONArray rows = jsonObj.getJSONArray("rows");
            data.clear();
            for(int r = 0; r < rows.length(); r++){
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");
                String value = columns.getJSONObject(0).getString("v");
                value += " " + columns.getJSONObject(1).getString("v");
                value += " X " + columns.getJSONObject(2).getString("v");
                value += " " + columns.getJSONObject(3).getString("v");
                data.add(value);
            }
            listViewAdapter.notifyDataSetChanged();
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}


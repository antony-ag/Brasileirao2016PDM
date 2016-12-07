package br.com.fatecpg.brasileirao2016pdm;

import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nathalie on 07/12/2016.
 */

public class DataReader extends AsyncTask<String, Void, String> {
    AsyncResult callback;
    String url = "https://spreadsheets.google.com/tq?key=1h6A1-VxKNQlIwIsaShHZo_2XmXP4bZxoICygPMAuOj8";

    private Exception e = null;

    public DataReader (AsyncResult callback){
        this.callback = callback;
    }

    protected String doInBackground(String... params){
        this.e = null;
        try{
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line).append('\n');
            }
            int start = response.indexOf("{", response.indexOf("{") + 1);
            int end = response.lastIndexOf("}");
            return response.substring(start,end);
        }catch(Exception e){
            this.e = e;
            return e.getLocalizedMessage();
        }

    }

    protected void onPostExecute(String result){
        if (this.e != null){
            callback.onException(this.e);
        }else{
            callback.onResult(result);
        }

    }
}




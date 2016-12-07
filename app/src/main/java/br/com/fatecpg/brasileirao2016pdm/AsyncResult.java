package br.com.fatecpg.brasileirao2016pdm;

/**
 * Created by Nathalie on 07/12/2016.
 */

public interface AsyncResult {

    void onResult(String response);
    void onException(Exception e);
}
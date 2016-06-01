package com.araujodeveloper.convertecsvparasqlite;

import android.content.ContentValues;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager assetManager = getResources().getAssets();
        InputStream stream;
        DataSource ds = new DataSource(MainActivity.this);

        try {
            stream = assetManager.open("cruzamentos_referenciados_novo.txt");
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(streamReader);

            String registro;

            for(int i = 0; i< 480; i++) {
                if((reader.readLine()) != null) {
                    registro = reader.readLine().toUpperCase();
                    String[] campos = registro.split(";");
                    ContentValues cv = new ContentValues();
                   /* cv.put("nome", campos[0]);
                    cv.put("latitude", campos[1]);
                    cv.put("longitude", campos[2]);*/

                    cv.put("cruzamento",campos[1]);
                    cv.put("latitude",campos[2]);
                    cv.put("longitude",campos[3]);
                    ds.inserir(cv, "cruzamentos");
                    Log.i("CampoInserido", registro);
                    Log.i("Campo = ",campos[1]);
                }
            }
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("Erro","Erro",e);
        }

        /// medidores
       /* try {
            stream = assetManager.open("medidores.txt");
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(streamReader);

            String registro;

            for(int i = 0; i< 800; i++) {
                if((reader.readLine()) != null) {
                    registro = reader.readLine().toUpperCase();
                    String[] campos = registro.split(";");
                    ContentValues cv = new ContentValues();
                    cv.put("nome", campos[0]);
                    cv.put("latitude", campos[1]);
                    cv.put("longitude", campos[2]);

                    cv.put("cruzamento",campos[0]);
                    cv.put("un_consumidora",campos[1]);
                    cv.put("num_medidor", campos[2]);
                    ds.inserir(cv, "medidor");
                    Log.i("CampoInserido", registro);
                }
            }
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("Erro","Erro",e);
        }*/








      /*  //Pontos De Onibus
        try {
            stream = assetManager.open("pontos_arrumados.txt");
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(streamReader);

            String registro;

            for(int i = 0; i< 800; i++) {
                if((reader.readLine()) != null) {
                    registro = reader.readLine().toUpperCase();
                    String[] campos = registro.split(";");
                    ContentValues cv = new ContentValues();
                    cv.put("nome", campos[0]);
                    cv.put("latitude", campos[1]);
                    cv.put("longitude", campos[2]);

                   *//* cv.put("cruzamento",campos[0]);
                    cv.put("un_consumidora",campos[1]);
                    cv.put("num_medidor",campos[2]);*//*
                    ds.inserir(cv, "pontos");
                    Log.i("CampoInserido", registro);
                }
            }
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("Erro","Erro",e);
        }
*/





    }


}

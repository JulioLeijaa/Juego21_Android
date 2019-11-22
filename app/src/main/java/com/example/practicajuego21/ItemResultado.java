package com.example.practicajuego21;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ItemResultado extends AppCompatActivity{

    String url = "http://ramiro174.com/api/obtener/numero";
    private VolleyS volley;
    protected RequestQueue fRequestQueue;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_resultado);

        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();

        obtenerDatos();
    }

    private void obtenerDatos(){
        JsonObjectRequest request =  new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    ArrayList<Persona> lista;
                    Type tPersona = new TypeToken<ArrayList<Persona>>(){}.getType();
                    String p = response.getString("resultados");
                    lista = new Gson().fromJson(p,tPersona);

                    recyclerView = findViewById(R.id.milista);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    AdaptadorPersona adapter= new AdaptadorPersona(lista);
                    recyclerView.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse: ", error.toString());
            }
        });

        fRequestQueue.add(request);

    }
}

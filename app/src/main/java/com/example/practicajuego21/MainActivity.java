package com.example.practicajuego21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected RequestQueue fRequestQueue;
    private VolleyS volley;

    Integer suma = 0;
    String url = "http://ramiro174.com/api/numero";
    String url2 = "http://ramiro174.com/api/enviar/numero";
    String url3 = "http://ramiro174.com/api/borrar/numero";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.boton:
                obtenerNumero();
                break;
            case R.id.enviar:
                if(suma == 0){
                    Toast.makeText(MainActivity.this, "Suma puntos!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        enviarSuma();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.mostrardatos:
                abrirActivity();
                break;
            case R.id.borrar:
                borraRegistros();
                break;
        }
    }

    private void obtenerNumero() {
        JsonObjectRequest request = new JsonObjectRequest( url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TextView text = findViewById(R.id.puntos);
                try {
                    Integer num = response.getInt("numero");
                    suma += num;
                    Toast.makeText(MainActivity.this, suma.toString(), Toast.LENGTH_SHORT).show();
                    if (suma > 21) {
                        Toast.makeText(MainActivity.this, "Perdiste!", Toast.LENGTH_SHORT).show();
                        text.setText("0");
                        suma = 0;
                        return;
                    }

                    text.setText(suma.toString());

                } catch (JSONException e) {
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

    private void enviarSuma() throws JSONException {

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("nombre", "Julio");
            jsonBody.put("numero", suma);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url2, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TextView text = findViewById(R.id.datos);
                Toast.makeText(MainActivity.this, "Enviandooo", Toast.LENGTH_SHORT).show();

                try {
                    JSONObject respuesta = new JSONObject(String.valueOf(response));

                    /*String nombreRespuesta = respuesta.getString("nombre");
                    /String numeroRespuesta = respuesta.getString("numero");
                    /String createdRespuesta = respuesta.getString("created_at");
                    /text.setText("Tus datos son: "+response);
                    text.setText("Tus datos son: "+nombreRespuesta+" "+numeroRespuesta+" "+createdRespuesta);*/

                } catch (JSONException e) {
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

    private void abrirActivity(){
        Intent i = new Intent(getApplicationContext(),ItemResultado.class);
        startActivity(i);
    }

    private  void borraRegistros(){
        JsonObjectRequest request =  new JsonObjectRequest(Request.Method.GET, url3, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this, " Registros Borrados", Toast.LENGTH_SHORT).show();
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
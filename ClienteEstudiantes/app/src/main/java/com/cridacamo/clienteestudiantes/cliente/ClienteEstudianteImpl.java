package com.cridacamo.clienteestudiantes.cliente;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cridacamo.clienteestudiantes.R;
import com.cridacamo.clienteestudiantes.cliente.util.Parametro;
import com.cridacamo.clienteestudiantes.entidad.Estudiante;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteEstudianteImpl {

    private Context context;

    public ClienteEstudianteImpl(Context context){
        this.context = context;
    }

    private final OkHttpClient simpleClient= new OkHttpClient.Builder()
            .readTimeout(Parametro.CONNECTION_TIMEOUT_RETROFIT, TimeUnit.SECONDS)
            .connectTimeout(Parametro.CONNECTION_TIMEOUT_RETROFIT, TimeUnit.SECONDS)
            .build();

    protected Retrofit getInstanceApi(){
        return new Retrofit.Builder().baseUrl(Parametro.URL_BASE).addConverterFactory(GsonConverterFactory.create()).client(simpleClient).build();
    }

    public void obtenerEstudiantes(final ListView listView){
        Retrofit retrofit = getInstanceApi();
        ClienteEstudiante clienteEstudiante = retrofit.create(ClienteEstudiante.class);
        Call<List<Estudiante>> respuesta = clienteEstudiante.obtenerEstudiantes();
        respuesta.enqueue(new Callback<List<Estudiante>>() {
            @Override
            public void onResponse(Call<List<Estudiante>> call, Response<List<Estudiante>> response) {
                List<Estudiante> estudiantes = response.body();
                String[] arrayEstudiantes = new String[estudiantes.size()];
                int i=0;
                for(Estudiante estudiante: estudiantes){
                    arrayEstudiantes[i] = estudiante.getCodigo() + " - " +estudiante.getNombre() + " " + estudiante.getApellido();
                    i++;
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, arrayEstudiantes);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<Estudiante>> call, Throwable t) {
                Log.e("Error Conexion", t.getMessage());
            }
        });

    }
}

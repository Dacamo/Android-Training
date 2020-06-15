package com.cridacamo.clienteestudiantes.cliente;

import com.cridacamo.clienteestudiantes.cliente.util.Parametro;
import com.cridacamo.clienteestudiantes.entidad.Estudiante;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClienteEstudiante {

    @GET(Parametro.ENDPOINT_ESTUDIANTES)
    Call<List<Estudiante>> obtenerEstudiantes();
}

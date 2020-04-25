package com.example.parqueaderouco.persistencia.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.parqueaderouco.entidades.Movimiento;

@Dao
public interface MovimientoDAO {

    @Query("SELECT * FROM MOVIMIENTO WHERE placa=:placa AND finalizaMovimiento = 0")
    Movimiento findByPlaca(String placa);

    @Insert
    void insert(Movimiento movimiento);
}

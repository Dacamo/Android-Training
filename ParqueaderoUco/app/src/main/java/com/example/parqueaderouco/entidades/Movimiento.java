package com.example.parqueaderouco.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.parqueaderouco.persistencia.Tabla;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity(tableName = Tabla.MOVIMIENTO)
@NoArgsConstructor
public class Movimiento {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idMovimiento")
    private Integer idMovimiento;
    @ColumnInfo(name = "idTarifa")
    private Integer idTarifa;
    @ColumnInfo(name = "placa")
    private String placa;
    @ColumnInfo(name = "fechaEntrada")
    private String fechaEntrada;
    @ColumnInfo(name = "fechaSalida")
    private String fechaSalida;
    @ColumnInfo(name = "finalizaMovimiento")
    private boolean finalizaMovimiento;

}

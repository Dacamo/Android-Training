package com.example.parqueaderouco.view.movimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.parqueaderouco.R;
import com.example.parqueaderouco.adapter.MovimientoAdapter;
import com.example.parqueaderouco.entidades.Movimiento;
import com.example.parqueaderouco.entidades.Tarifa;
import com.example.parqueaderouco.persistencia.room.DataBaseHelper;
import com.example.parqueaderouco.utilities.ActionBarUtil;
import com.example.parqueaderouco.view.ActualizacionTarifaActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovimientosActivity extends AppCompatActivity {

    @BindView(R.id.listViewMovimientos)
    public ListView listViewMovimientos;
    public List<Movimiento> listaMovimientos;
    DataBaseHelper db;
    private MovimientoAdapter movimientoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos);
        ButterKnife.bind(this);
        initComponents();
        loadTarifas();
    }

    private void initComponents() {
        db = DataBaseHelper.getDBMainThread(this);
        onItemClickListener();
    }

    private void loadTarifas() {
        listaMovimientos = db.getMovimientoDAO().listar();
        movimientoAdapter = new MovimientoAdapter(this, listaMovimientos);
        listViewMovimientos.setAdapter(movimientoAdapter);

    }


    private void onItemClickListener() {
        listViewMovimientos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),getMovimientoId(position), Toast.LENGTH_SHORT).show();
            }

            private String getMovimientoId(int position) {
                return String.valueOf(listaMovimientos.get(position).getIdMovimiento());
            }
        });
    }
}

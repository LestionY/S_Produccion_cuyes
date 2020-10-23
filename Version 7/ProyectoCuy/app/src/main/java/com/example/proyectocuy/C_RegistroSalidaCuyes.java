package com.example.proyectocuy;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectocuy.BD_ProduccionCuyes;
import com.example.proyectocuy.ModeloDatos.Cuy;
import com.example.proyectocuy.ModeloDatos.Transaccion;
import com.example.proyectocuy.R;
import com.example.proyectocuy.Recursos_Adicionales.Fechas;
import com.example.proyectocuy.Transacciones;

public class C_RegistroSalidaCuyes extends AppCompatActivity {

    EditText txtID,txtIdPozaDestino;
    TextView txtIdPoza, txtCantidadMadres,txtCantidadPadrillo, txtCantidadLactantes,txtEdad;
    Spinner cmbTipoSalida, cmbCategoria,cmbGenero;

    //AUTOCOMPLETADOS DEL ACTIVITY ANTERIOR
    String usuarioID="72941896";
    String idPoza="A1";

    //df
    Transaccion transaccion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_salida_cuyes);

        //Declarado de textos
        txtID=(EditText)findViewById(R.id.txtCodigoCuy);
        txtEdad=(TextView) findViewById(R.id.txtEdad);
        txtIdPoza=(TextView)findViewById(R.id.txtIdPoza);
        txtCantidadMadres=(TextView)findViewById(R.id.txtCantidadMadres);
        txtCantidadPadrillo=(TextView)findViewById(R.id.txtCantidadPadres);
        txtCantidadLactantes=(TextView)findViewById(R.id.txtCantidadLactantes);
        txtIdPozaDestino=(EditText)findViewById(R.id.txtIdPozaDestino);

        //Declarado y llenado de spiners

        cmbTipoSalida=(Spinner)findViewById(R.id.cmbTipoSalida);
        ArrayAdapter<CharSequence> adapterTipoSalida=ArrayAdapter.createFromResource(this,R.array.tipoSalidaCuy,android.R.layout.simple_spinner_item);
        cmbTipoSalida.setAdapter(adapterTipoSalida);

        cmbCategoria=(Spinner)findViewById(R.id.cmbCategoriaCuy);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.categoriasCuy, android.R.layout.simple_spinner_item);
        cmbCategoria.setAdapter(adapter);

        //////////////////////////////////////////////////////////////////////////////////////

        //Mostrar datos de cuyes en la poza
        cargarDatos(idPoza);

        //Genera transaccion para ese instante
        transaccion= Transacciones.generarTransaccion(usuarioID);

        //
        txtID.addTextChangedListener(txtIdWatcher);

    }

    private TextWatcher txtIdWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Cuy cuy=new Cuy();
            cuy=BD_ProduccionCuyes.consultarCuy("bb");
            //txtEdad.setText(Fechas.calcularEdad(cuy.getFechaNaci()));
            txtIdPozaDestino.setText(cuy.getGenero());

        }
    };

    public void registrarClick(View view)
    {
        Transacciones.RegistrarEntradaCuyes(capturarCuy(),transaccion);
        restablecerCampos();
    }


    //Registro de ingreso de cuy
    private Cuy capturarCuy()
    {
        Cuy cuy=new Cuy();
        cuy.setCuyId(txtID.getText().toString());
        cuy.setIdPoza(txtIdPoza.getText().toString());
        switch (cmbCategoria.getSelectedItem().toString())
        {
            case "Madre madura":cuy.setCategoria("MM");break;
            case "Madre primeriza":cuy.setCategoria("MP");break;
            case "Padrillo":cuy.setCategoria("PD");break;
            case "Engorde":cuy.setCategoria("EG");break;
            case "Recria":cuy.setCategoria("EC");break;
            case "Lactante":cuy.setCategoria("LC");break;
        }
        cuy.setGenero(cmbGenero.getSelectedItem().toString());
        cuy.setFechaNaci(Fechas.calcularFechaNacimiento(Integer.parseInt(txtEdad.getText().toString())));

        return cuy;
    }

    //Carga datos sobre la cantidad de cuyes de la BD
    private void cargarDatos(String idPoza)
    {
        txtCantidadMadres.setText(String.valueOf(BD_ProduccionCuyes.consultarCantiTipoCuyPoza(idPoza,"MM")));
        txtCantidadLactantes.setText(String.valueOf(BD_ProduccionCuyes.consultarCantiTipoCuyPoza(idPoza,"LC")));
        txtCantidadPadrillo.setText(String.valueOf(BD_ProduccionCuyes.consultarCantiTipoCuyPoza(idPoza,"PD")));
    }

    //Restablece las entradas de texto
    public void restablecerCampos()
    {
        txtID.setText("");
        txtEdad.setText("");
    }



}

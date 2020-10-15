package com.example.proyectocuy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ControlPozas.Conexion;

public class Usuario extends AppCompatActivity implements View.OnClickListener {
    EditText id;
    Button consul;
    TextView nom,cel,correo,contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        id=findViewById(R.id.edtCUid);
        nom=findViewById(R.id.txtNombre);
        cel=findViewById(R.id.txtCelular);
        correo=findViewById(R.id.txtCorreo);
        contra=findViewById(R.id.txtContraseña);
        consul=findViewById(R.id.btnCUconsultar);
        consul.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        consultapersona();
    }

    public void consultapersona() {
        try {
            Statement stm= Conexion.conectarBD().createStatement();
            ResultSet rs=stm.executeQuery("SELECT * FROM tblUsuario WHERE ID_Usuario ='"+id.getText().toString()+"'");

            if (rs.next()){
                nom.setText(rs.getString(2));
                cel.setText(rs.getString(3));
                correo.setText(rs.getString(4));
                contra.setText(rs.getString(5));
            }
            id.setText("");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
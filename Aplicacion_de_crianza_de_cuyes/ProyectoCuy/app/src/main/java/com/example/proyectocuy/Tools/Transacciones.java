package com.example.proyectocuy.Tools;


import android.content.Context;
import android.widget.Toast;

import com.example.proyectocuy.AccesoBD.BD_AccesoDatos;
import com.example.proyectocuy.ModeloDatos.Cuy;
import com.example.proyectocuy.ModeloDatos.Transaccion;
import com.example.proyectocuy.Recursos_Adicionales.Fechas;

public class Transacciones {

    public static Transaccion generarTransaccion(String usuarioID)
    {
        Transaccion transac=new Transaccion();
        try {
            transac.setIdUsuario(usuarioID);
            transac.setFecha(Fechas.mostrarFechaHoy());
            if (BD_AccesoDatos.registrarTransaccion(transac)==false)
                throw new Exception("Ocurrio un error");
            else return transac;
        }catch (Exception e)
        {
            return null;
        }
    }

    public static void RegistrarEntradaCuyes(Cuy cuy, Transaccion transaccion, String tipoMovi,Context context)
    {

        String movimiento="";
        switch (tipoMovi)
        {
            case "Compra":movimiento="IC";break;
            case "Nacimiento":movimiento="IN";break;
            case "Rotacion":movimiento="IR";break;
            case "Otros":movimiento="IO";break;
        }
        Toast.makeText(context,"idCuy:"+cuy.getIdPoza(),Toast.LENGTH_LONG).show();
        BD_AccesoDatos.registrarCuy(cuy);
        BD_AccesoDatos.registrarDetalle(transaccion.getIdTransaccion(),cuy.getCuyId(),movimiento,context);
    }

    public static  void RegistrarSalidaCuyes(Cuy cuy, Transaccion transaccion, String idPozaDestino, String tipoMovi, Context context)
    {
        try {

            String movimiento="";
            String estado="Activo";
            switch (tipoMovi)
            {
                case "Muerte":movimiento="SM";estado="muerto";break;
                case "Venta":movimiento="SV";estado="vendido";break;
                case "Consumo":movimiento="SC";estado="consumido";break;
                case "Rotación":movimiento="SR";estado="Activo";break;
                case "Otros":movimiento="SO";estado="No habido";break;
            }

            if (tipoMovi!="Rotación")
            {
                BD_AccesoDatos.salidaRotacion(cuy.getCuyId(),idPozaDestino,context);
            }else {BD_AccesoDatos.salidaCuy(cuy.getCuyId(),estado,context);}
            BD_AccesoDatos.registrarDetalle(transaccion.getIdTransaccion(),cuy.getCuyId(),movimiento,context);

        }catch (Exception e)
        {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }

    }
    public static void destruirTransaccion(int idtransaccion)
    {
            BD_AccesoDatos.validartransaccion(idtransaccion);
    }



}

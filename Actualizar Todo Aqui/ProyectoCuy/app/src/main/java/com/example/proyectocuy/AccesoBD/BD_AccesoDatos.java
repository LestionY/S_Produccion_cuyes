package com.example.proyectocuy.AccesoBD;


import android.content.Context;
import android.widget.Toast;


import com.example.proyectocuy.ModeloDatos.FilaMovEntradaSalida_Reporte;
import com.example.proyectocuy.ModeloDatos.FilaMovPoblacional_reporte;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BD_AccesoDatos {


    //Reportes
    public static List<FilaMovEntradaSalida_Reporte> reporte(String tipoReporte, Context context)
    {
        List<FilaMovEntradaSalida_Reporte> report=new ArrayList<>();
        try {
            Statement stm=ConexionSQLServer.conectarBD().createStatement();
            ResultSet rs=stm.executeQuery("EXEC SP_ConsultarReporte "+tipoReporte);
            while (rs.next()){
                report.add(new FilaMovEntradaSalida_Reporte(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
            }
        }catch (Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            report=null;
        }
        return report;
    }

    public static List<FilaMovPoblacional_reporte> reporteMovPoblacional(Context context,String motivo, String tipoIngreso, String filaNombre)
    {
        List<FilaMovPoblacional_reporte> report=new ArrayList<>();

        String v1=filaNombre;
        int v2=0,v3=0,v4=0,v5=0,v6=0,v7=0,v8=0,v9=0;

        ResultSet rs;
            try {
                Statement stm=ConexionSQLServer.conectarBD().createStatement();

                rs=stm.executeQuery("EXEC SP_CANTIDAD_MP '"+motivo+"','"+tipoIngreso+"','LC','HEMBRA'");
                if (rs.next()){
                    v2=rs.getInt(1);
                }

                rs=stm.executeQuery("SP_CANTIDAD_MP  "+motivo+",'"+tipoIngreso+"','LC','MACHO'");
                if (rs.next()){
                    v3=rs.getInt(1);
                }

                rs=stm.executeQuery("EXEC SP_CANTIDAD_MP "+motivo+",'"+tipoIngreso+"','RC','HEMBRA'");
                if (rs.next()){
                    v4=rs.getInt(1);
                }

                rs=stm.executeQuery("EXEC SP_CANTIDAD_MP "+motivo+",'"+tipoIngreso+"','RC','MACHO'");
                if (rs.next()){
                    v5=rs.getInt(1);
                }

                rs=stm.executeQuery("EXEC SP_CANTIDAD_MP "+motivo+",'"+tipoIngreso+"','EG','HEMBRA'");
                if (rs.next()){
                    v6=rs.getInt(1);
                }

                rs=stm.executeQuery("EXEC SP_CANTIDAD_MP "+motivo+",'"+tipoIngreso+"','EG','MACHO'");
                if (rs.next()){
                    v7=rs.getInt(1);
                }

                rs=stm.executeQuery("EXEC SP_CANTIDAD_MP "+motivo+",'"+tipoIngreso+"','PD','MACHO'");
                if (rs.next()){
                    v8=rs.getInt(1);
                }

                rs=stm.executeQuery("EXEC SP_CANTIDAD_MP "+motivo+",'"+tipoIngreso+"','MM','HEMBRA'");
                if (rs.next()){
                    v9=rs.getInt(1);
                }

                rs=stm.executeQuery("EXEC SP_CANTIDAD_MP "+motivo+",'"+tipoIngreso+"','MP','HEMBRA'");
                if (rs.next()){
                    v9=v9+rs.getInt(1);
                }

                report.add(new FilaMovPoblacional_reporte(v1,v2,v3,v4,v5,v6,v7,v8,v9));
            }catch (Exception e){
                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                report=null;
            }


        return report;
    }


}




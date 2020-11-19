package com.example.proyectocuy.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectocuy.BD_ProduccionCuyes;
import com.example.proyectocuy.ModeloDatos.Poza;
import com.example.proyectocuy.R;

import java.util.ArrayList;
import java.util.Map;

public class ExpPCAdapter extends BaseExpandableListAdapter {
    private ArrayList<String> listCategoria;
    private Map<String,ArrayList<String>> mapChild;
    private Context context;
    private ArrayList<Poza> mList;


    public ExpPCAdapter(ArrayList<String> listCategoria, Map<String, ArrayList<String>> mapChild, Context context) {
        this.listCategoria = listCategoria;
        this.mapChild = mapChild;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return listCategoria.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mapChild.get(listCategoria.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listCategoria.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mapChild.get(listCategoria.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String tituloCategoria=(String) getGroup(groupPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.elv_group,null);
        TextView tvGroup=convertView.findViewById(R.id.tvGroup);
        tvGroup.setText(tituloCategoria);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String item=(String) getChild(groupPosition,childPosition);
        convertView=LayoutInflater.from(context).inflate(R.layout.elv_child,null);
        TextView tvCantMaA=(TextView) convertView.findViewById(R.id.tvCantiMaA);
        TextView tvCantMaP=(TextView) convertView.findViewById(R.id.tvCantiMaP);
        TextView tvCantP=(TextView) convertView.findViewById(R.id.tvCantiPa);
        TextView tvCantL=(TextView) convertView.findViewById(R.id.tvCantiLa);
        Button ingreso=convertView.findViewById(R.id.btnIngreso);
        Button salida=convertView.findViewById(R.id.btnSalida);
        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listCategoria.get(groupPosition), Toast.LENGTH_SHORT).show();
            }
        });
        salida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listCategoria.get(groupPosition), Toast.LENGTH_SHORT).show();
            }
        });

        tvCantMaA.setText(String.valueOf(BD_ProduccionCuyes.consultarCantidadCuy("1",listCategoria.get(groupPosition))));
        tvCantMaP.setText(String.valueOf(BD_ProduccionCuyes.consultarCantidadCuy("2",listCategoria.get(groupPosition))));
        tvCantP.setText(String.valueOf(BD_ProduccionCuyes.consultarCantidadCuy("3",listCategoria.get(groupPosition))));
        tvCantL.setText(String.valueOf(BD_ProduccionCuyes.consultarCantidadCuy("8",listCategoria.get(groupPosition))));


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}

package com.example.dopaminastudios.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dopaminastudios.myapplication.R;
import com.example.dopaminastudios.myapplication.model.Encounter;

import java.util.ArrayList;

public class encountersAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Encounter> listItems;

    public encountersAdapter(Context context, ArrayList<Encounter> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() { //Cuantos datos se van a cargar
        return listItems.size();
    }

    @Override
    public Object getItem(int position) { //Devolvera de listItem la posicion
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //Aqui se crea cada item y donde asignamos valores de cada elemento

        Encounter encounter = (Encounter)getItem(position);
        String xp;
        convertView = LayoutInflater.from(context).inflate(R.layout.encounters,null);
        TextView encounterTitle = (TextView)convertView.findViewById(R.id.encounterTitle);
        TextView encounterXp = (TextView)convertView.findViewById(R.id.encounterXp);

        xp = String.valueOf(encounter.getXp());

        encounterTitle.setText(encounter.getTitle());
        encounterXp.setText(xp);

        return convertView;
    }
}

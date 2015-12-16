package com.grupo03.hermes.adaptors;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.grupo03.hermes.Pictograma;

import java.util.List;

/**
 * Created by federico on 16/12/15.
 */
public class GridAdaptor extends BaseAdapter {
    List<Pictograma> pictogramas;

    public GridAdaptor(List<Pictograma> lista){
        pictogramas = lista;
    }

    @Override
    public int getCount() {
        return pictogramas.size();
    }

    @Override
    public Object getItem(int position) {
        return pictogramas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return pictogramas.get(position).getView(parent.getContext());
    }
}

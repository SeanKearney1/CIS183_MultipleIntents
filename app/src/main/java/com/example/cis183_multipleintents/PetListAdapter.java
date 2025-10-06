package com.example.cis183_multipleintents;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PetListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Pet> listOfPets;
    public PetListAdapter(Context c, ArrayList<Pet> ls) {
        context = c;
        listOfPets = ls;
    }
    @Override
    public int getCount() {
        return listOfPets.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfPets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater mInflator = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflator.inflate(R.layout.pet_cell,null);
        }

        TextView petName = view.findViewById(R.id.tv_v_cell_name);
        TextView petType = view.findViewById(R.id.tv_v_cell_type);
        TextView petAge = view.findViewById(R.id.tv_v_cell_age);

        Pet pet = listOfPets.get(position);

        petName.setText("Name: "+pet.getName());
        petType.setText("Type: "+pet.getType());
        petAge.setText("Age:   "+Integer.toString(pet.getAge()));

        return view;
    }
}

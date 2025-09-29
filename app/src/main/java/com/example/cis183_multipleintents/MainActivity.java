package com.example.cis183_multipleintents;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Pet> listOfPets = new ArrayList<>();

    PetListAdapter plAdapter;

    //String[] test = { "Hello", "hi", "Hola" };

    ListView lv_v_listOfPets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv_v_listOfPets = findViewById(R.id.lv_v_listOfPets);

        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,test);
        //lv_v_listOfPets.setAdapter(adapter);

        addDummyDataToArrayList();
        displayAllPetData();
        fillListView();
    }

    private void addDummyDataToArrayList() {
        listOfPets.add(new Pet("Frank",4,Pet.PetType.petAt(0)));
        listOfPets.add(new Pet("Hank",8,Pet.PetType.petAt(0)));
        listOfPets.add(new Pet("Steven",1,Pet.PetType.petAt(1)));
        listOfPets.add(new Pet("Halberd",5,Pet.PetType.petAt(2)));
    }
    private void displayAllPetData() {
        for(int i = 0; i < listOfPets.size();i++) {
            Log.d("Pet Info",listOfPets.get(i).getName());
        }
    }

    private void fillListView() {
        plAdapter = new PetListAdapter(this,listOfPets);
        lv_v_listOfPets.setAdapter(plAdapter);
    }
}
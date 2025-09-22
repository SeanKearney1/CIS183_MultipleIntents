package com.example.cis183_multipleintents;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Pet> listOfPets;
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


        addDummyDataToArrayList();

    }

    private void addDummyDataToArrayList() {
        listOfPets.add(new Pet("Frank",4,Pet.PetType.petAt(0)));
        listOfPets.add(new Pet("Hank",8,Pet.PetType.petAt(0)));
        listOfPets.add(new Pet("Steven",1,Pet.PetType.petAt(1)));
        listOfPets.add(new Pet("Halberd",5,Pet.PetType.petAt(2)));
    }

}
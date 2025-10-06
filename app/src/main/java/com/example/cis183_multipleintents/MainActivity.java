package com.example.cis183_multipleintents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Pet> listOfPets = new ArrayList<>();

    static int numberTestingLoad = 50;
    PetListAdapter plAdapter;

    //String[] test = { "Hello", "hi", "Hola" };

    Button btn_v_addPet;
    ListView lv_v_listOfPets;

    static boolean FirstLoad = true;
    Intent intent_j_addNewPet;
    Intent intent_j_displayUpdate;

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

        Intent cameFrom = getIntent();

        if (cameFrom.getSerializableExtra("PetData") != null) {
            Pet petData = (Pet) cameFrom.getSerializableExtra("PetData");
            listOfPets.add(petData);
        }
        if (FirstLoad) {
            addDummyDataToArrayList();
            FirstLoad = false;
        }



        lv_v_listOfPets = findViewById(R.id.lv_v_listOfPets);
        intent_j_displayUpdate = new Intent(MainActivity.this, PetDisplayUpdate.class);
        intent_j_addNewPet = new Intent(MainActivity.this,AddPet.class);
        btn_v_addPet = findViewById(R.id.btn_v_addPet);

        addPetOnClickListener();
        setOnClickListenerForListView();
        displayAllPetData();
        fillListView();
    }













    private void addPetOnClickListener() {
        btn_v_addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTestingLoad = 64;
                intent_j_addNewPet.putExtra("InfoPassed","Hello from main.");
                startActivity(intent_j_addNewPet);
            }
        });
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

    private void setOnClickListenerForListView() {
        lv_v_listOfPets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pet petSelected = listOfPets.get(position);
                goToPetDisplayUpdate(petSelected);
            }
        });
    }

    public void goToPetDisplayUpdate(Pet pet) {
        intent_j_displayUpdate.putExtra("PetData", pet);
        startActivity(intent_j_displayUpdate);
    }


    public void addPet(Pet p) {
        listOfPets.add(p);
    }
}
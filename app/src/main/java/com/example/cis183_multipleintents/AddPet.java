package com.example.cis183_multipleintents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPet extends AppCompatActivity {

    Button btn_addPet_back;
    Button btn_addPet_addPet;
    EditText et_addPet_name;
    EditText et_addPet_age;
    Spinner spin_addPet_type;
    ArrayAdapter<String> PetTypes;
    TextView tv_addPet_newPetType;

    Intent intent_j_main;
    Intent intent_j_newPetType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_pet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent cameFrom = getIntent();

        Bundle infoPassedToMe = cameFrom.getExtras();

        if (infoPassedToMe != null) {
            String data = infoPassedToMe.getString("InfoPassed");
            Log.d("INFO PASSED FROM MAIN",data);
        }

        btn_addPet_back = findViewById(R.id.btn_addPet_back);
        btn_addPet_addPet = findViewById(R.id.btn_addPet_addPet);

        et_addPet_name = findViewById(R.id.et_addPet_name);
        et_addPet_age = findViewById(R.id.et_addPet_age);
        spin_addPet_type = findViewById(R.id.spin_addPet_type);

        tv_addPet_newPetType = findViewById(R.id.tv_addPet_newPetType);

        intent_j_main = new Intent(AddPet.this,MainActivity.class);
        intent_j_newPetType = new Intent(AddPet.this, AddPetType.class);

        PetTypes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,Pet.PetType.getAllPetTypes());

        spin_addPet_type.setAdapter(PetTypes);

        setOnClickListenerBackButton();
        setOnClickListenerUpdatePetButton();
        setOnClickListenerAddNewPetType();
    }

    private void setOnClickListenerAddNewPetType() {
        tv_addPet_newPetType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Hi","Add new Pet");
                startActivity(intent_j_newPetType);
            }
        });
    }

    private void setOnClickListenerBackButton() {
        btn_addPet_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_j_main);
            }
        });
    }

    private void setOnClickListenerUpdatePetButton() {
        btn_addPet_addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pet p = getPetDataFromTextBoxes();
                intent_j_main.putExtra("PetData",p);
                startActivity(intent_j_main);
            }
        });
    }
    private Pet getPetDataFromTextBoxes() {

        Pet new_pet = new Pet();

        new_pet.setName(String.valueOf(et_addPet_name.getText()));
        if (!String.valueOf(et_addPet_age.getText()).isEmpty()) {
            new_pet.setAge(Integer.parseInt(String.valueOf(et_addPet_age.getText())));
        }
        new_pet.setType(Pet.PetType.petAt(spin_addPet_type.getSelectedItemPosition()));

        //little extra
        if (new_pet.getName().isEmpty()) { new_pet.setName("John"); }
        if (new_pet.getType().isEmpty()) { new_pet.setType(Pet.PetType.petAt(0)); }
        if (new_pet.getAge() <= 0) { new_pet.setAge(1); }

        return new_pet;
    }
}
package com.example.cis183_multipleintents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PetDisplayUpdate extends AppCompatActivity {

    Button btn_v_update_back;
    Button btn_v_update_updatePetData;
    Intent main_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pet_display_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_v_update_back = findViewById(R.id.btn_v_update_back);
        btn_v_update_updatePetData = findViewById(R.id.btn_v_update_updatePetData);
        main_intent = new Intent(PetDisplayUpdate.this,MainActivity.class);

        Intent loadedFrom = getIntent();

        Pet petPassed = (Pet) loadedFrom.getSerializableExtra("PetData");


        setOnClickListenerButtonBack();
        setOnClickListenerButtonAddPet();
        
    }


    private void setOnClickListenerButtonBack() {
        btn_v_update_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(main_intent);
            }
        });
    }

    private void setOnClickListenerButtonAddPet() {
        btn_v_update_updatePetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(main_intent);
            }
        });
    }
}
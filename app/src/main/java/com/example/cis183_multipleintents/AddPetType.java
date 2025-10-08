package com.example.cis183_multipleintents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPetType extends AppCompatActivity {

    EditText et_addPetType_petType;

    Button btn_addPetType_addPetType;

    TextView tv_addPetType_error1;
    TextView tv_addPetType_error2;
    TextView tv_addPetType_error3;
    Button btn_addPetType_back;

    Intent intent_j_addPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_pet_type);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        et_addPetType_petType = findViewById(R.id.et_addPetType_petType);
        btn_addPetType_addPetType = findViewById(R.id.btn_addPetType_addPetType);
        btn_addPetType_back = findViewById(R.id.btn_addPetType_back);
        tv_addPetType_error1 = findViewById(R.id.tv_addPetType_error1);
        tv_addPetType_error2 = findViewById(R.id.tv_addPetType_error2);
        tv_addPetType_error3 = findViewById(R.id.tv_addPetType_error3);
        intent_j_addPet = new Intent(AddPetType.this, AddPet.class);

        setVariousListeners();
    }










    private void setVariousListeners() {
        btn_addPetType_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_j_addPet);
            }
        });


        btn_addPetType_addPetType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean newPetTypeValid = true;

                int error = checkUserInput();

                if (error == 1) {
                    newPetTypeValid = false;
                    tv_addPetType_error1.setVisibility(View.VISIBLE);
                    tv_addPetType_error2.setVisibility(View.INVISIBLE);
                    tv_addPetType_error3.setVisibility(View.INVISIBLE);
                }
                else if (error == 2) {
                    newPetTypeValid = false;
                    tv_addPetType_error1.setVisibility(View.INVISIBLE);
                    tv_addPetType_error2.setVisibility(View.INVISIBLE);
                    tv_addPetType_error3.setVisibility(View.VISIBLE);
                }
                else if (error == 3) {
                    newPetTypeValid = false;
                    tv_addPetType_error1.setVisibility(View.INVISIBLE);
                    tv_addPetType_error2.setVisibility(View.VISIBLE);
                    tv_addPetType_error3.setVisibility(View.INVISIBLE);
                }

                if (newPetTypeValid) {
                    tv_addPetType_error1.setVisibility(View.INVISIBLE);
                    tv_addPetType_error2.setVisibility(View.INVISIBLE);
                    tv_addPetType_error3.setVisibility(View.INVISIBLE);
                    Pet.PetType.addPet(String.valueOf(et_addPetType_petType.getText()));
                    startActivity(intent_j_addPet);
                }
            }
        });
    }

    private int checkUserInput() {
        String input = String.valueOf(et_addPetType_petType.getText());

        if (input.isEmpty()) {
            return 1;
        }
        for (char c: input.toCharArray()) {
            if (Character.isDigit(c)) {
                return 2;
            }
        }

        for (int i = 0; i < Pet.PetType.getAllPetTypes().size();i++) {
            if (Pet.PetType.petAt(i).equals(input)) {
                return 3;
            }
        }

        return 0;
    }
}
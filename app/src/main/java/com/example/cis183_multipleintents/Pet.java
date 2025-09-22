package com.example.cis183_multipleintents;

import java.util.ArrayList;
import java.util.Arrays;

public class Pet {
    private String name;
    private int age;
    private String type;

    public Pet() {
        name = "";
        age = 0;
        type = "";
    }
    public Pet(String str_name, int int_age, String str_type) {
        name = str_name;
        age = int_age;
        type = str_type;
    }
    public String getName() { return name; }
    public void setName(String str) { name = str; }
    public int getAge() { return age; }
    public void setAge(int i_age) { age = i_age; }
    public String getType() { return type; }
    public void setType(String str) { type = str; }


    static class PetType {
        static ArrayList<String> typeOfPet = new ArrayList<>(Arrays.asList("Dog","Cat","Snake","Chicken","Hamster"));

        public static String petAt(int i) {
            return typeOfPet.get(i);
        }
        public static ArrayList<String> getAllPetTypes() {
            return typeOfPet;
        }
        public static void addPet(String t) {
            typeOfPet.add(t);
        }
    }
}

package com.example.gymapplication.Models;

public class NutritionModel {

    private String name;
    private String type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }





    public NutritionModel(String name, String type) {
        this.name = name;
        this.type = type;

    }

    public NutritionModel() {

    }




}

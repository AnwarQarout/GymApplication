package com.example.gymapplication.Models;

import java.text.DecimalFormat;

public class MemberModel {

    private String username;
    private String password;
    private String name;
    private String phone;
    private double height;
    private double weight;
    private String image;

    @Override
    public String toString() {
        return "MemberModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", image='" + image + '\'' +
                '}';
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public MemberModel(String username, String password, String name, String phone,String image, double height, double weight) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.height = height;
        this.weight = weight;
    }

    public MemberModel() {
    }

    public double calculateBMI(){
        double realHeight = this.height/100;
        System.out.println("--------+++"+realHeight);
        double BMI = this.weight / (double)Math.pow(realHeight,2);
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(BMI));
    }
}

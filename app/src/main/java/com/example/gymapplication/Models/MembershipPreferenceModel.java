package com.example.gymapplication.Models;

public class MembershipPreferenceModel {

    private boolean weightGain;
    private boolean weightLoss;
    private boolean massGain;


    public MembershipPreferenceModel() {
    }

    public MembershipPreferenceModel(boolean weightGain, boolean weightLoss, boolean massGain) {
        this.weightGain = weightGain;
        this.weightLoss = weightLoss;
        this.massGain = massGain;
    }

    public boolean isWeightGain() {
        return weightGain;
    }

    public void setWeightGain(boolean weightGain) {
        this.weightGain = weightGain;
    }

    public boolean isWeightLoss() {
        return weightLoss;
    }

    public void setWeightLoss(boolean weightLoss) {
        this.weightLoss = weightLoss;
    }

    public boolean isMassGain() {
        return massGain;
    }

    public void setMassGain(boolean massGain) {
        this.massGain = massGain;
    }

    @Override
    public String toString() {
        return "MembershipPreferenceModel{" +
                "weightGain=" + weightGain +
                ", weightLoss=" + weightLoss +
                ", massGain=" + massGain +
                '}';
    }

}

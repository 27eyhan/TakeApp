package com.example.takeapp;

public class HabitHolderRecycle {

    String habit;
    int imageHabit;

    public HabitHolderRecycle(String habit, int imageHabit) {
        this.habit = habit;
        this.imageHabit = imageHabit;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public int getImageHabit() {
        return imageHabit;
    }

    public void setImageHabit(int imageHabit) {
        this.imageHabit = imageHabit;
    }
}

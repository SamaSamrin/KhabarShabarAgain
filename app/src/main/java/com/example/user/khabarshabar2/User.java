package com.example.user.khabarshabar2;

/**
 * Created by User on 30-Apr-17.
 */

//User model class26

class User {
    long id = 0;
    String username;
    String password;
    String email;
    String gender;
    double height;
    double initialWeight;
    double goalWeight;
    double currentWeight;

    User(){
    }

    public User(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, String gender, double height, double initialWeight) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.height = height;
        this.initialWeight = initialWeight;
    }

    void setId(long id) {
        this.id = id;
    }

    long getId() {
        return id;
    }

    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }
}



package com.leon.dagger2demo;


import javax.inject.Inject;

public class User {

    String name;

    @Inject
    public User() {
        this.name  = "Leon";
    }

}

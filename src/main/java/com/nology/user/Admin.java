package com.nology.user;



public class Admin extends User{

    public Admin(String name, String email, String password, boolean isAdmin) {
        super(name, email, password, true);
    }


}

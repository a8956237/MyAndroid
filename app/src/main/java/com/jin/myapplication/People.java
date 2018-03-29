package com.jin.myapplication;

/**
 * Created by jinx9 on 2018-03-27.
 */

public class People {
    private String name;
    private String phone;
    private int picture;

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

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }


    public People(String name, String phone, int picture) {
        this.name = name;
        this.phone = phone;
        this.picture = picture;


    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", picture=" + picture +
                '}';
    }
}

package com.example.labTest2;


public class Contacts {
    int id;
    String name;
    String mobileNo;
    String email;
    String address;

    public Contacts() {

    }


    public Contacts(String name, String mobileNo, String email, String address) {
        this.name = name;
        this.mobileNo = mobileNo;
        this.email = email;
        this.address = address;
    }

    public Contacts(int anInt, String name, String mobileNo, String email, String address) {
        this.id = anInt;
        this.name = name;
        this.mobileNo = mobileNo;
        this.email = email;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "name='" + name + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

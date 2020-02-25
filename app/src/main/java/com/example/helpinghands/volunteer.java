package com.example.helpinghands;

class volunteer {
    private String name,mobileno,altnumber,email,address,districts;
    public volunteer() {


    }

    public volunteer(String name, String mobileno, String altnumber, String email, String address, String districts) {
        this.name = name;
        this.mobileno = mobileno;
        this.altnumber = altnumber;
        this.email = email;
        this.address = address;
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getAltnumber() {
        return altnumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getDistricts() {
        return districts;
    }
}

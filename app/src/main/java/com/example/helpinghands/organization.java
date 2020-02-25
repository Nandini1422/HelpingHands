package com.example.helpinghands;

class organization {
    public organization(String name, String mobileno, String email, String address, String districts, String from_time, String to_time) {
        this.name = name;
        this.mobileno = mobileno;
        this.email = email;
        this.address = address;
        this.districts = districts;
        this.from_time = from_time;
        this.to_time = to_time;
    }

    private String name,mobileno,email,address,districts,from_time,to_time;

    public String getName() {
        return name;
    }

    public String getMobileno() {
        return mobileno;
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

    public String getFrom_time() {
        return from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public organization() {


    }
}


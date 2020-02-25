package com.example.helpinghands;

class donor {

    private String name,mobileno,email,address,districts,organizations,typeOfDonation;
    public donor() {


    }

    public donor(String name, String mobileno, String email, String address, String districts, String organizations, String typeOfDonation) {
        this.name = name;
        this.mobileno = mobileno;
        this.email = email;
        this.address = address;
        this.districts = districts;
        this.organizations = organizations;
        this.typeOfDonation = typeOfDonation;
    }

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

    public String getOrganizations() {
        return organizations;
    }

    public String getTypeOfDonation() {
        return typeOfDonation;
    }


}

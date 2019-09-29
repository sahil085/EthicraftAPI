package com.iskcon.EthicraftAPI.co;

import javax.validation.constraints.NotNull;

public class AddressCO {

    @NotNull(message = "Address cannot be null")
    private String address;
    @NotNull(message = "City cannot be null")
    private String city;
    @NotNull(message = "State cannot be null")
    private String state;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

package com.iskcon.EthicraftAPI.co;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class AddressCO {

    @NotNull
    private String address;
    @NotNull
    private String city;
    @NotNull
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

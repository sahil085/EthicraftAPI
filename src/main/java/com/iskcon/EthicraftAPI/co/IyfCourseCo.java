package com.iskcon.EthicraftAPI.co;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class IyfCourseCo {

    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Mobile number cannot be null")
    private Long mobileNumber;

}

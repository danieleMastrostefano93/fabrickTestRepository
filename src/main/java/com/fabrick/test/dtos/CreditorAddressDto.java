package com.fabrick.test.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreditorAddressDto {
    @Size(max = 40)
    private String address;
    private String city;
    private String countryCode;
}

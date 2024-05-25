package com.fabrick.test.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreditorDto {

    @NotBlank(message = "creditor name is required")
    @Size(max = 70)
    private String name;
    @NotNull(message = "creditor account is required")
    @JsonProperty(value = "account")
    private CreditorAccountDto creditorAccount;
    @JsonProperty(value = "address")
    private CreditorAddressDto creditorAddress;
}

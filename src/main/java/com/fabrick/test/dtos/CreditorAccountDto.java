package com.fabrick.test.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreditorAccountDto {
    @NotBlank(message = "creditor account code is required")
    private String accountCode;
    private String bicCode;
}

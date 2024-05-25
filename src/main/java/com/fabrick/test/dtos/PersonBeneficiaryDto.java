package com.fabrick.test.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonBeneficiaryDto {
    @NotBlank(message = "fiscalCode1 is required")
    private String fiscalCode1;
    private String fiscalCode2;
    private String fiscalCode3;
    private String fiscalCode4;
    private String fiscalCode5;
}

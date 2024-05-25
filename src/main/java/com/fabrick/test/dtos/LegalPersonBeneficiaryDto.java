package com.fabrick.test.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LegalPersonBeneficiaryDto {
    @NotBlank(message = "fiscal code is required")
    private String fiscalCode;
    private String legalRepresentativeFiscalCode;
}

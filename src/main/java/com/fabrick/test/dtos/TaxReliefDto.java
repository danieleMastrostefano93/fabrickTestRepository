package com.fabrick.test.dtos;

import com.fabrick.test.enums.BeneficiaryType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaxReliefDto {

    private String taxReliefId;
    @NotNull
    private Boolean isCondoUpgrade;
    @NotBlank (message = "creditor fiscal code is required")
    private String creditorFiscalCode;
    @NotBlank (message = "beneficiary type is required")
    private BeneficiaryType beneficiaryType;
    @JsonProperty(value = "naturalPersonBeneficiary")
    private PersonBeneficiaryDto personBeneficiary;
    private LegalPersonBeneficiaryDto legalPersonBeneficiary;
}

package com.fabrick.test.dtos;

import com.fabrick.test.enums.FeeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BonificoDto {

    @NotNull(message = "creditor is required")
    private CreditorDto creditor;
    private String executionDate;
    private String uri;
    @NotBlank(message = "description is required")
    @Size(max = 140)
    private String description;
    @NotNull(message = "amount is required")
    private Long amount;
    @NotBlank(message = "currency is required")
    private String currency;
    private Boolean isUrgent;
    private Boolean isInstant;
    private FeeType feeType;
    private String feeAccountId;
    private TaxReliefDto taxRelief;
}

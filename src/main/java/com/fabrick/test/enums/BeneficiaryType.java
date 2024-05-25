package com.fabrick.test.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum BeneficiaryType {
    @JsonProperty(value = "NATURAL_PERSON")
    NATURAL_PERSON,
    @JsonProperty(value = "LEGAL_PERSON")
    LEGAL_PERSON
}

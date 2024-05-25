package com.fabrick.test.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
public enum FeeType {
    @JsonProperty(value = "SHA")
    SHA,
    @JsonProperty(value = "OUR")
    OUR,
    @JsonProperty(value = "BEN")
    BEN
}

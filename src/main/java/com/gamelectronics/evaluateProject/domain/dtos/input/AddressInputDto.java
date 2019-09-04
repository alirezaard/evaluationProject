package com.gamelectronics.evaluateProject.domain.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressInputDto {
    private String state;
    private String city;
    private String district;
    private String postalCode;
    private String addressDetail;
}

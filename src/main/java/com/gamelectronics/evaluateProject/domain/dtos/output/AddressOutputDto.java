package com.gamelectronics.evaluateProject.domain.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressOutputDto {
    private String city;
    private String uuid;
    private String state;
    private String district;
    private String postalCode;
    private String addressDetail;
}
package com.gamelectronics.evaluateProject.domain.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonOutputDto {
    private String uuid;
    private String firstName;
    private String lastName;
    private String mobile;
    private String phone;
}
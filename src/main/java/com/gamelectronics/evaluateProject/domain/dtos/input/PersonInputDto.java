package com.gamelectronics.evaluateProject.domain.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInputDto {
    private String firstName;
    private String lastName;
    private String mobile;
    private String phone;
}

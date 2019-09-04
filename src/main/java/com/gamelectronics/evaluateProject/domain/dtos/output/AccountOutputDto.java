package com.gamelectronics.evaluateProject.domain.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOutputDto {
    private String uuid;
    private String bankName;
    private String account;
    private String card;
}
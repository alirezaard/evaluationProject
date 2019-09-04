package com.gamelectronics.evaluateProject.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto implements Serializable {
    private String message;
    private int errorCode;
}

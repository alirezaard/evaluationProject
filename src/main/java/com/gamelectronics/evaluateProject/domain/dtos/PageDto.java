package com.gamelectronics.evaluateProject.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
    int pageNumber;
    int pageSize;
    Long totalElement;
    String sortBy;
    boolean decs;//decs or ase
}
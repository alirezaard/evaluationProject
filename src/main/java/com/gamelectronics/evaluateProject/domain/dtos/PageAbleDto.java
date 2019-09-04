package com.gamelectronics.evaluateProject.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageAbleDto<T> {
    private List<T> dto = new ArrayList<>();
    private PageDto page = new PageDto();
}

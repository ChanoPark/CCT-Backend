package com.example.hackerton.domain.item.dto;

import com.example.hackerton.domain.item.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SelectItemResDto {
    private String companyName;
    private String name;
    private Long price;
    private Category category;
}

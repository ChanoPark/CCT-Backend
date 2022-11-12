package com.example.hackerton.domain.item.dto;

import com.example.hackerton.domain.item.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SelectItemDetailResDto {
    private String companyName;
    private String name;
    private Long price;
    private Category category;
}

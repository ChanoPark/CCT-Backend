package com.example.hackerton.domain.item.dto;

import com.example.hackerton.domain.item.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CreateItemReqDto {

    private String companyName;
    private String name;
    private Long price;
    private Long count; //재고
    private String content;
    private Category category;

}

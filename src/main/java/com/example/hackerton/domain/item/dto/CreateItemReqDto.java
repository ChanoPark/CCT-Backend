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
    private String imageName;
    private String imageUrl;
    private Category category;
    private Long count; //재고
    private String content;

}

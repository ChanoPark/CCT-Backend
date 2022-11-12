package com.example.hackerton.domain.item.service;

import com.example.hackerton.domain.item.Category;
import com.example.hackerton.domain.item.Item;
import com.example.hackerton.domain.item.dto.CreateItemReqDto;
import com.example.hackerton.domain.item.dto.SelectItemResDto;
import com.example.hackerton.global.config.ResponseAbs;

import java.util.List;

public interface ItemService {
    Boolean saveItem(CreateItemReqDto dto);
    List<SelectItemResDto>selectAllItems();
    List<SelectItemResDto> selectCategorylItems(Category category);


}

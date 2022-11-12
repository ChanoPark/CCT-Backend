package com.example.hackerton.domain.item.service;

import com.example.hackerton.domain.item.Category;
import com.example.hackerton.domain.item.Item;
import com.example.hackerton.domain.item.dto.CreateItemReqDto;
import com.example.hackerton.domain.item.dto.SelectItemResDto;
import com.example.hackerton.domain.item.repository.ItemRepository;
import com.example.hackerton.global.common.CodeSet;
import com.example.hackerton.global.config.RejectResponse;
import com.example.hackerton.global.config.ResponseAbs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    @Transactional
    @Override
    public Boolean saveItem(CreateItemReqDto dto) {
        Item item = Item.builder()
                .companyName(dto.getCompanyName())
                .name(dto.getName())
                .price(dto.getPrice())
                .count(dto.getCount())
                .category(dto.getCategory())
                .content(dto.getContent())
                .createDate(LocalDate.now())
                .updateDate(LocalDate.now())
                .build();
        Item result = itemRepository.save(item);

        return result.getId() != null;
    }

    @Override
    public List<SelectItemResDto> selectAllItems() {
        return itemRepository.findAllItem();
    }

    @Override
    public List<SelectItemResDto> selectCategorylItems(Category category) {
        return itemRepository.findAllByCategoryItem(category);
    }





}

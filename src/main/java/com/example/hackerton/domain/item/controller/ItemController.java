package com.example.hackerton.domain.item.controller;

import com.example.hackerton.domain.item.Category;
import com.example.hackerton.domain.item.Item;
import com.example.hackerton.domain.item.dto.CreateItemReqDto;
import com.example.hackerton.domain.item.service.ItemService;
import com.example.hackerton.domain.user.User;
import com.example.hackerton.domain.user.dto.LoginDto;
import com.example.hackerton.domain.user.dto.UserSignUpReqDto;
import com.example.hackerton.global.common.CodeSet;
import com.example.hackerton.global.common.EndPoint;
import com.example.hackerton.global.config.LoginTokenResponse;
import com.example.hackerton.global.config.RejectResponse;
import com.example.hackerton.global.config.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @PostMapping(value = EndPoint.ITEM, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createItem(@RequestPart(value = "image", required = false) MultipartFile image, @RequestPart(value = "createDto") CreateItemReqDto dto) {
        if(itemService.saveItem(dto)) {
            return ResponseEntity.ok()
                    .body(CodeSet.ITEM_SAVE_SUCCESS);
        } else {
            return ResponseEntity.ok()
                    .body(CodeSet.ITEM_SAVE_FAIL);
        }
    }

    @GetMapping(EndPoint.ITEM)
    public ResponseEntity<?> selectAllItems() {
        return ResponseEntity.ok()
               .body(itemService.selectAllItems());
    }

    @GetMapping(EndPoint.ITEM + "/{category}")
    public ResponseEntity<?> selectAllByCategoryItems(@PathVariable Category category) {
        return ResponseEntity.ok()
                .body(itemService.selectCategorylItems(category));
    }
}

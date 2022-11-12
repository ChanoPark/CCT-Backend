package com.example.hackerton.domain.item.repository;

import com.example.hackerton.domain.item.Category;
import com.example.hackerton.domain.item.Item;
import com.example.hackerton.domain.item.dto.SelectItemResDto;
import com.example.hackerton.domain.user.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long > { // 이미지 나와야함
    @Query("select new com.example.hackerton.domain.item.dto.SelectItemResDto(i.companyName, i.name, i.price, i.category) " +
            "from Item i " +
            "where i.count > 0" +
            "order by i.createDate DESC")
    List<SelectItemResDto> findAllItem();
    @Query("select new com.example.hackerton.domain.item.dto.SelectItemResDto(i.companyName, i.name, i.price, i.category) " +
            "from Item i " +
            "where i.category= :category and i.count > 0 " +
            "order by i.createDate DESC")
    List<SelectItemResDto> findAllByCategoryItem(Category category);

}

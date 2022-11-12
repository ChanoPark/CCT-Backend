package com.example.hackerton.domain.item;

import com.example.hackerton.domain.user.UserPermission;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name="item")
@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String companyName;
    private Long price;
    private String imageUrl;
    private Long count; //재고
    private String content;
    private LocalDate createDate;
    private LocalDate updateDate;
}

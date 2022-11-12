package com.example.hackerton.domain.item;

import com.example.hackerton.domain.review.Review;
import com.example.hackerton.domain.user.UserPermission;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name="item")
@Entity
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String companyName;
    private String name;
    private Long price;
    private String imageName;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Category category;
    private Long count; //재고
    private String content;

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<Review> reviewList = new ArrayList<>();

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}

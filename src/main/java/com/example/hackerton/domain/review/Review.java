package com.example.hackerton.domain.review;

import com.example.hackerton.domain.item.Category;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name="review")
@Entity
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private String nickName;
    private String content;
    private int star;
    private LocalDate createDate;
}

package com.company.models;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String img;
    private String title;
    private Integer countOfRooms;
    private String price;
    private String category;
}

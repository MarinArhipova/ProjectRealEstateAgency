package com.company.dto;

import com.company.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String img;
    private String title;
    private String price;
    private String category;


    public static ProductDto from(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .img(product.getImg())
                .title(product.getTitle())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }

    public static List<ProductDto> from(List<Product> product) {
        return product.stream().map(ProductDto::from).collect(Collectors.toList());
    }
}
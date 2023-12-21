package com.example.carculator.Shop.Model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private String cartCode;
    private Long productCode;
    private String productName;
    private String productRmg;
    private Long billCount;
    private Long productAmount;
    private String memberCode;
    private Long productCount;
}

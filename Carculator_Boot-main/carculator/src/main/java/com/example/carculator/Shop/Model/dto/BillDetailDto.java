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
public class BillDetailDto {
    private Long BD_NO;

    private String billNo;

    private String memberCode;

    private Long productAmount;

    private String productName;

    private Long billCount;

    private Long productCode;


}

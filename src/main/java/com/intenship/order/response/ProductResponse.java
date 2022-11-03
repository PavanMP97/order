package com.intenship.order.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponse {
    private Long id;
    private String name;
    private int stock;
    private int price;
}
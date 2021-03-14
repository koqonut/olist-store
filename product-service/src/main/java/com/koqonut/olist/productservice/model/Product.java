package com.koqonut.olist.productservice.model;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    @NonNull
    @CsvBindByName
    private String product_id;

    @CsvBindByName
    private String product_category_name;

    @CsvBindByName
    private int product_name_length;

    @CsvBindByName
    private int product_weight_g;

    @CsvBindByName
    private int product_length_cm;

    @CsvBindByName
    private int product_height_cm;
    
    @CsvBindByName
    private int product_width_cm;

}

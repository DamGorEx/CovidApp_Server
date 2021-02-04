package mcs.Model.dto;

import lombok.Data;

import java.util.List;

public @Data
class ProductDTO {
    private String name, desc, imgId;
    private Double price;
    private Integer qty;
    private List<String> categories;

}

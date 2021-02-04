package mcs.Model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.util.List;


public @Data class OrderDTO {
    @JsonAlias("products")
    private List<ProductDTO> products;
    @JsonAlias("user")
    private UserDTO user;
    private String shpMethod;
}
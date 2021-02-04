package mcs.Model.dto;

import lombok.Data;

public @Data
class UserDTO {
    private String name, surname, email, number, street, zipcode, city;

    public String getFullAdress () {
        return street + "/" + zipcode + "/" + city;
    }
}

package mcs.Model.dto;

import lombok.Data;

public @Data
class UserDTOLogin {
    private String name, lastName;
    private CredentialDTO credentials;
}




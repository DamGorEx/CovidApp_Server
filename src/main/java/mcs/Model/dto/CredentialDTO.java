package mcs.Model.dto;

import lombok.Data;

public @Data
class CredentialDTO {
    private String login, password;
    private RoleDTO role;

}


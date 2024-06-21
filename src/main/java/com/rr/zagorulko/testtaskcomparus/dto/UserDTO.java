package com.rr.zagorulko.testtaskcomparus.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String id;
    private String username;
    private String name;
    private String surname;
}

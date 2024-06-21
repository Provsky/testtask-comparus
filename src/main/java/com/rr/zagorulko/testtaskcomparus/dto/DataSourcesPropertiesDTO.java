package com.rr.zagorulko.testtaskcomparus.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataSourcesPropertiesDTO {
    private String name;
    private String strategy;
    private String url;
    private String table;
    private String user;
    private String password;
    private UserDTO mapping;
}

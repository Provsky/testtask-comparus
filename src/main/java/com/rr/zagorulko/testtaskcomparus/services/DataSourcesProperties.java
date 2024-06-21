package com.rr.zagorulko.testtaskcomparus.services;


import com.rr.zagorulko.testtaskcomparus.dto.DataSourcesPropertiesDTO;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties
@Data
public class DataSourcesProperties {

    private List<DataSourcesPropertiesDTO> dataSources;
}

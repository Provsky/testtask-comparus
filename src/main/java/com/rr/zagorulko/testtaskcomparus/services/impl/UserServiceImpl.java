package com.rr.zagorulko.testtaskcomparus.services.impl;

import com.rr.zagorulko.testtaskcomparus.services.DataSourcesProperties;
import com.rr.zagorulko.testtaskcomparus.dto.DataSourcesPropertiesDTO;
import com.rr.zagorulko.testtaskcomparus.dto.UserDTO;
import com.rr.zagorulko.testtaskcomparus.services.UserService;
import com.rr.zagorulko.testtaskcomparus.services.exceptions.DataSourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private DataSourcesProperties dataSourceConfig;

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        for (DataSourcesPropertiesDTO properties : dataSourceConfig.getDataSources()) {
            users.addAll(fetchUsersFromDataSource(properties));
        }
        return users;
    }

    private List<UserDTO> fetchUsersFromDataSource(DataSourcesPropertiesDTO properties) {
        List<UserDTO> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword())) {
            String query = "SELECT * FROM " + properties.getTable();
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    UserDTO user = UserDTO.builder()
                            .id(resultSet.getString(properties.getMapping().getId()))
                            .username(resultSet.getString(properties.getMapping().getUsername()))
                            .name(resultSet.getString(properties.getMapping().getName()))
                            .surname(resultSet.getString(properties.getMapping().getSurname()))
                            .build();
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DataSourceException("Data source error while fetching users", e);
        }
        return users;
    }
}

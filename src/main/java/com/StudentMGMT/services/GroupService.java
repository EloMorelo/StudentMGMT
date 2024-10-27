package com.StudentMGMT.services;

import com.StudentMGMT.entities.Group;
import com.StudentMGMT.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GroupService {
    public List<Group> getAllGroups() throws Exception {
        List<Group> groups = new ArrayList<>();
        String query = "SELECT group_id, name, year FROM groups";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getObject("group_id", UUID.class));
                group.setName(rs.getString("name"));
                group.setYear(rs.getInt("year"));
                groups.add(group);
            }
        }
        return groups;
    }
}

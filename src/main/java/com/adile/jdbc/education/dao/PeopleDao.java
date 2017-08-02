package com.adile.jdbc.education.dao;

import com.adile.jdbc.education.domain.People;

import java.sql.SQLException;
import java.util.List;

public interface PeopleDao {
    public void insertPeople(People people) throws SQLException;
    public void deletePeople(Integer id) throws SQLException;
    public People selectPeople(Integer id) throws SQLException;
    public void updatePeople(Integer id, People people) throws SQLException;
    public List<People> selectAll() throws SQLException;
}

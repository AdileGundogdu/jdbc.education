package com.adile.jdbc.education.dao;

import com.adile.jdbc.education.domain.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDao {
    public  void  insertPerson(Person person)  throws SQLException;
    public  void  deletePerson(Integer id) throws SQLException;
    public  Person selectPerson(Integer id) throws SQLException;
    public  void uptadePerson(Integer id,Person person) throws SQLException;
    public List<Person> selectAll() throws SQLException;
}

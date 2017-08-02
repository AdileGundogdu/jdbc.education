package com.adile.jdbc.education.dao;

import com.adile.jdbc.education.domain.Person;
import com.adile.jdbc.education.util.DatabaseOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    public List<Person> selectAll() throws SQLException {
        List<Person> list = new ArrayList<Person>();
        databaseOperations.connect();
        String sql = "SELECT * FROM person" ;
        PreparedStatement preparedStatement= databaseOperations.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            Integer dbId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Integer age = resultSet.getInt("age");

            Person person = new Person();
            person.setId(dbId);
            person.setName(name);
            person.setAge(age);

            list.add(person);
        }

        resultSet.close();
        preparedStatement.close();
        databaseOperations.disconnect();

        return list;

    }

    private DatabaseOperations databaseOperations;
    private PreparedStatement preparedStatement;

    public PersonDaoImpl() {
        databaseOperations= new DatabaseOperations();
    }



    public void insertPerson(Person person) throws SQLException {
        databaseOperations.connect();

        String sql = "INSERT INTO person(name , age) VALUES (?,?)";
        PreparedStatement preparedStatement = databaseOperations.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,person.getName());
        preparedStatement.setInt(2,person.getAge());

        preparedStatement.execute();
        preparedStatement.close();

        databaseOperations.disconnect();

    }

    public void deletePerson(Integer id) throws SQLException {
        databaseOperations.connect();

        String sql = "DELETE FROM person WHERE id=?";
        PreparedStatement preparedStatement = databaseOperations.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);

        preparedStatement.execute();
        preparedStatement.close();

        databaseOperations.disconnect();

    }

    public Person selectPerson(Integer id) throws SQLException {
        databaseOperations.connect();
        String sql = "SELECT * FROM person WHERE id = ?" ;
        PreparedStatement preparedStatement= databaseOperations.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            Integer dbId =resultSet.getInt("id");
            String name = resultSet.getString("name");
            Integer age = resultSet.getInt("age");

            Person person= new Person();
            person.setId(dbId);
            person.setName(name);
            person.setAge(age);

            resultSet.close();
            preparedStatement.close();
            databaseOperations.disconnect();

            return person;
        }
        resultSet.close();
        preparedStatement.close();
        databaseOperations.disconnect();

        return null;
    }

    public void uptadePerson(Integer id, Person person) throws SQLException {
        databaseOperations.connect();
        String sql ="UPDATE person SET name =?,age=? WHERE id=?";
        PreparedStatement preparedStatement= databaseOperations.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,person.getName());
        preparedStatement.setInt(2,person.getAge());
        preparedStatement.setInt(3,id);

        preparedStatement.execute();
        preparedStatement.close();
        databaseOperations.disconnect();

    }
}

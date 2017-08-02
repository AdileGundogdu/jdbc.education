package com.adile.jdbc.education.dao;

import com.adile.jdbc.education.domain.People;
import com.adile.jdbc.education.domain.Person;
import com.adile.jdbc.education.util.DatabaseOperations;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeopleDaoImpl implements PeopleDao {
    private DatabaseOperations databaseOperations;
    private PreparedStatement preparedStatement;

    public PeopleDaoImpl() {
        databaseOperations = new DatabaseOperations();
    }

    public PeopleDaoImpl(String message) {
        databaseOperations = new DatabaseOperations();
        System.out.println(message);
    }



    public void insertPeople(People people) throws SQLException {
        databaseOperations.connect();
        String sql = "INSERT INTO people(name ,surname, age, email) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement=databaseOperations.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, people.getName());
        preparedStatement.setString(2, people.getSurname());
        preparedStatement.setInt(3, people.getAge());
        preparedStatement.setString(4, people.getEmail());

        preparedStatement.execute();
        preparedStatement.close();

        databaseOperations.disconnect();
    }

    public void deletePeople(Integer id) throws SQLException {
        databaseOperations.connect();

        String sql ="DELETE INTO people WHERE id=?";
        PreparedStatement reparedStatement= databaseOperations.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);

        preparedStatement.execute();
        preparedStatement.close();


        databaseOperations.disconnect();
    }

    public People selectPeople(Integer id) throws SQLException {
        databaseOperations.connect();

        String sql="SELECT * FROM people WHERE id=?";
        preparedStatement =databaseOperations.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);

        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()) {
            Integer dbId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            Integer age = resultSet.getInt("age");
            String email= resultSet.getString("email");

            People people = new People() ;
            people.setId(dbId);
            people.setName(name);
            people.setSurname(surname);
            people.setAge(age);
            people.setEmail(email);

        }
        resultSet.close();
        preparedStatement.close();
        return null;
    }

    public void updatePeople(Integer id, People people) throws SQLException {
        databaseOperations.connect();
        String sql="UPDATE person SET name =?,surname =?,age=?, email=? WHERE id=? ";
        PreparedStatement preparedStatement = databaseOperations.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,people.getName());
        preparedStatement.setString(2, people.getSurname());
        preparedStatement.setInt(3, people.getAge());
        preparedStatement.setString(4, people.getEmail());
        preparedStatement.setInt(5, id);

        preparedStatement.execute();
        preparedStatement.close();

        databaseOperations.disconnect();
    }


    public List<People> selectAll() throws SQLException {
        List<People> list = new ArrayList<People>();

        databaseOperations.connect();
        String sql = "SELECT * FROM people";

        preparedStatement = databaseOperations.getConnection().prepareStatement(sql);
        ResultSet resultSet= preparedStatement.executeQuery();

        while(resultSet.next()) {
            Integer dbId =resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            Integer age= resultSet.getInt("age");
            String email = resultSet.getString("email");

            People people = new People();
            people.setId(dbId);
            people.setName(name);
            people.setSurname(surname);
            people.setAge(age);
            people.setEmail(email);

            list.add(people);

        }

        resultSet.close();
        preparedStatement.close();
        databaseOperations.disconnect();
        return list;
    }
}

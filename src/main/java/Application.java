import com.adile.jdbc.education.dao.PeopleDao;
import com.adile.jdbc.education.dao.PeopleDaoImpl;
import com.adile.jdbc.education.domain.People;

import java.sql.*;
import java.util.List;


public class Application {

    public static void main(String[] args) throws SQLException {

        PeopleDao peopleDao= new PeopleDaoImpl("hello World!");



    }
}

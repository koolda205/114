package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Util.getConnection();
        UserDao userDao;
        try {
            userDao = new UserDaoJDBCImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        userDao.createUsersTable();

        userDao.saveUser("Name1", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 38);

        int i = 1;
        while (i < 100) {
            userDao.saveUser("Name" + i, "LastName" + i, (byte) (i));
            i++;
        }

        userDao.getAllUsers();
        userDao.removeUserById(100);

        userDao.cleanUsersTable();
        userDao.dropUsersTable();

        try {
            Util.connection.close();
            System.out.println("Соединение закрыто");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

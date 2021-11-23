/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;

/**
 * Web application lifecycle listener.
 *
 * @author arthu
 */
public class ArthurAndradeDbListener implements ServletContextListener {
    public static final String CLASS_NAME = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:to-do.db";
    
    public static Exception exception = null;
    
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL);
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName(CLASS_NAME);
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.execute("create table if not exists ArthurAndrade_tasks ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "title text NOT NULL,"
                    + "user text NOT NULL)");
            stmt.close();
            con.close();
        }
        catch(Exception ex) {
            exception = ex;
        }
    }
    

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}

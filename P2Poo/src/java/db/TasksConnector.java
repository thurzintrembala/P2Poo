/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author arthu
 */
public class TasksConnector {
    
    public static ArrayList<String> getTasks() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        Connection con = ArthurAndradeDbListener.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from ArthurAndrade_tasks");
        
        while(rs.next()) {
            list.add(rs.getString("title"));
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
    
     public static void addTask(String title, String user) throws Exception {       
        Connection con = ArthurAndradeDbListener.getConnection();
        Statement stmt = con.createStatement();
        
        stmt.execute("insert into ArthurAndrade_tasks values ('"+title+"','"+user+"')");        
        
        stmt.close();
        con.close();
    }
     
     public static void removeTask(Integer id) throws Exception {       
        Connection con = ArthurAndradeDbListener.getConnection();
        Statement stmt = con.createStatement();
        
        stmt.execute("delete from ArthurAndrade_tasks where id = '"+id+"'");        
        
        stmt.close();
        con.close();
    }
    
}
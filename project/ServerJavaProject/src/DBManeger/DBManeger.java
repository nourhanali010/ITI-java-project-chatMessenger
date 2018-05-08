/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManeger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author asmaa
 */
public class DBManeger {

    /**
     *
     */
    public DBManeger() {
    }

    Connection connection = null;

    /**
     *
     */
    public ResultSet rs = null;

    /**
     *
     */
    public Statement statment;

    /**
     *
     * @param connection .
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     * @return .
     */
    public Connection getConnection() {
        return connection;
    }

     /**
     * Start database connection
     */
    public void DBStartConnection() {
        try {


            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "100100100");//("jdbc:mysql://localhost/JavaProject","root","100100100");
//connection = DriverManager.getConnection("jdbc:mysql://localhost/javaproject","root","");

            statment = connection.createStatement();
            System.out.println("connect to db is sucsseful");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage().toString());
        } 
    }

     /**
     * Stop database connection
     */
    public void DBStopConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage().toString());
        }
    }

}

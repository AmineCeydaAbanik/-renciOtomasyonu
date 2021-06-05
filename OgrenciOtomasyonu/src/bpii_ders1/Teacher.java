/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpii_ders1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Teacher {
    
    int id;
    String teacherId;
    String username;
    String name;
    String surname;
    String password;
    
    public Teacher(int id, String teacherId, String username, String name, String surname, String password) {
        this.id = id;
        this.teacherId = teacherId;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }
    
    public Teacher(String teacherId, String username, String name, String surname, String password) {
        this.teacherId = teacherId;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }
    public Teacher(String username, String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Teacher login(){
           
        Connection conn = null;
        
        try {
            conn =
               DriverManager.getConnection("jdbc:mysql://localhost/project?" +
                                           "user=root&password=");
            Statement stmt=conn.createStatement(); 
            // check teacher in the db
            ResultSet rs = stmt.executeQuery("SELECT * FROM teacher WHERE username='" + username + "' AND password='" + password + "'");  

            if( rs.next()){
                this.teacherId = rs.getString("teacherId");
                this.name = rs.getString("name");
                this.surname = rs.getString("surname");
                this.id = rs.getInt("id");
                return this;
            }
            else{
                return null;
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }
    
    public int addToDb(){
        
        Connection conn = null;
        
        try {
            conn =
               DriverManager.getConnection("jdbc:mysql://localhost/project?" +
                                           "user=root&password=");
            Statement stmt=conn.createStatement();  
            stmt.executeUpdate("INSERT INTO teacher(teacherId, username, name, surname, password) VALUES('12345', 'teacher1', 'name1', 'surname1', '1')");  

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return 0;
    }
    
}

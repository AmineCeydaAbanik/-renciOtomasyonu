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
import java.util.ArrayList;

public class Student {

    int id;
    String studentId;
    String name;
    String surname;
    String password;
    
    public Student(int id, String studentId, String name, String surname, String password) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public Student(String studentId, String name, String surname, String password) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }
    
    public Student(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }
    public Student() {
    }
    
    public int getId() {
        return id;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getStudentId() {
        return studentId;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public Student login(){
        
        Connection conn = null;
        
        try {
            // connect to the db.
            conn =
               DriverManager.getConnection("jdbc:mysql://localhost/project?" +
                                           "user=root&password=");
            Statement stmt=conn.createStatement();  
            // run the query 
            ResultSet rs = stmt.executeQuery("SELECT * FROM student WHERE studentId='" + studentId + "' AND password='" + password + "'");  

            if( rs.next()){
                // get info from the db.
                this.id = rs.getInt("id");
                this.name = rs.getString("name");
                this.surname = rs.getString("surname");
                                
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
    
    public ArrayList<Student> getAllStudents(){
        
        Connection conn = null;
        ArrayList<Student> studentList = new ArrayList<Student>();
        try {
            // connect to the db.
            conn =
               DriverManager.getConnection("jdbc:mysql://localhost/project?" +
                                           "user=root&password=");
            Statement stmt=conn.createStatement();  
            // run the query 
            ResultSet rs = stmt.executeQuery("SELECT * FROM student");  

            while( rs.next()){
                
                // get info from the db.
                int id = rs.getInt("id");
                String studentId = rs.getString("studentId");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String password = rs.getString("password");
                // create a student object
                Student newStudent = new Student(id, studentId, name, surname, password);
                
                // add the students to the list.
                studentList.add(newStudent);
                
            }
            return studentList;

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }
    
    public void addToDb(){
        
        Connection conn = null;
        
        try {
            // connect to the db.
            conn =
               DriverManager.getConnection("jdbc:mysql://localhost/project?" +
                                           "user=root&password=");
            Statement stmt=conn.createStatement();  
            // run the query 
            String sql = "INSERT INTO student(studentId, name, surname, password) " +
                       "VALUES ('" + this.studentId + "', '" + this.name + "', '" + this.surname + "', '" + this.password + "')";
            stmt.executeUpdate(sql);


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
    }
    
    public void delete(){
        
        Connection conn = null;
        
        try {
            conn =
               DriverManager.getConnection("jdbc:mysql://localhost/project?" +
                                           "user=root&password=");
            Statement stmt=conn.createStatement();  
          
            String sql = "DELETE FROM student " +
                         "WHERE studentId = '" + studentId + "'";
            stmt.executeUpdate(sql);


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
    }
}

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

/**
 *
 * @author h_aca
 */
public class TakenFinalGrades {
    
    int id;
    int studentTableId;
    int teacherTableId;
    float grade;
    
    public TakenFinalGrades(){
        
    }
    
    public TakenFinalGrades(int studentTableId, int teacherTableId, float grade) {
        this.studentTableId = studentTableId;
        this.teacherTableId = teacherTableId;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentTableId() {
        return studentTableId;
    }

    public void setStudentTableId(int studentTableId) {
        this.studentTableId = studentTableId;
    }

    public int getTeacherTableId() {
        return teacherTableId;
    }

    public void setTeacherTableId(int teacherTableId) {
        this.teacherTableId = teacherTableId;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM takenfinalgrades WHERE studentTableId=" + studentTableId + " AND teacherTableId=" + teacherTableId);  
               
            // if already exist, then update it.
            if( rs.next()){
                String sql = "UPDATE takenfinalgrades " +
                            "SET grade = " + grade + " WHERE studentTableId=" + studentTableId + " AND teacherTableId=" + teacherTableId;
                stmt.executeUpdate(sql);
            }
            else{
                
                String sql = "INSERT INTO takenfinalgrades(studentTableId, teacherTableId, grade) " +
                           "VALUES (" + studentTableId + ", " + teacherTableId + ", " + grade + ")";
                stmt.executeUpdate(sql);
            }
            

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
    }
    
    public TakenFinalGrades[] getAllFinalGrades(){
        
       TakenFinalGrades list[] = new TakenFinalGrades[100];
        
        Connection conn = null;
        
        try {
            // connect to the db.
            conn =
               DriverManager.getConnection("jdbc:mysql://localhost/project?" +
                                           "user=root&password=");
            Statement stmt=conn.createStatement();  
            // run the query 
            ResultSet rs = stmt.executeQuery("SELECT * FROM takenfinalgrades");  
            // add all db records to the list one by one
            while( rs.next()){
                int id = rs.getInt("id");
                int studentTableId = rs.getInt("studentTableId");
                int teacherTableId = rs.getInt("teacherTableId");
                float grade = rs.getFloat("grade");
                // create a takenfinalgrades object and add it to the list.
                list[studentTableId] = new TakenFinalGrades(studentTableId, teacherTableId, grade);
            }
            return list;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }
    
    
    public void getFinalGrade(){
          
        Connection conn = null;
        
        try {
            conn =
               DriverManager.getConnection("jdbc:mysql://localhost/project?" +
                                           "user=root&password=");
            Statement stmt=conn.createStatement();  
            ResultSet rs = stmt.executeQuery("SELECT * FROM takenfinalgrades WHERE studentTableId=" + studentTableId);  

            if( rs.next()){
                this.teacherTableId = rs.getInt("teacherTableId");
                this.grade = rs.getFloat("grade");
                
            }
            else{
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
}

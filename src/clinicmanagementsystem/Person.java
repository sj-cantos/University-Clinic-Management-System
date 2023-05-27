/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicmanagementsystem;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author user
 */
public class Person {
    String name;
    String address;
    String phoneNo;
    int age;
    Connection con = null;
    Statement stmt = null;
    DriverManager dm;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    
}

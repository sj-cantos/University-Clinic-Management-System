/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicmanagementsystem;


import java.sql.SQLException;
import javax.swing.JOptionPane;





/**
 *
 * @author user
 */
public class Patient extends Person {
    String diagnosis;
    String course;
    String prescription;
    
    public Patient (String name,int age, String address,String course, String phoneNo,String prescription,String diagnosis){
    this.name = name;
    this.age = age;
    this.address = address;
    this.course = course;
    this.phoneNo = phoneNo;
    this.prescription = prescription;
    this.diagnosis = diagnosis;

    
    
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

  
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void addRecord(){
        
        try {
            con = dm.getConnection("jdbc:mysql://localhost:3306/clinicdb","root","root2468");
            pst = con.prepareStatement("INSERT INTO patients(name,age,address,course,phoneno,prescription,diagnosis) VALUES(?,?,?,?,?,?,?)");
            pst.setString(1, getName());
            pst.setInt(2,getAge() );
            pst.setString(3, getAddress());
            pst.setString(4, getCourse());
            pst.setString(5, getPhoneNo());
            pst.setString(6, getPrescription());
            pst.setString(7, getDiagnosis());
            
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null,"Patient record added successfully");
                } else {
                    JOptionPane.showMessageDialog(null,"Error: Patient record not added");
                }
            
            con.close();
            
            
        }catch(SQLException e){e.printStackTrace();}
        
        
    }    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicmanagementsystem;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class Doctors extends Person {

    public Doctors(String name,int age,String address,String phoneNo) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public int getAge() {
        return age;
    }
    
    public void addRecord(){
        
        try {
            con = dm.getConnection("jdbc:mysql://localhost:3306/clinicdb","root","root2468");
            pst = con.prepareStatement("INSERT INTO doctors(name,age,address,phoneno) VALUES(?,?,?,?)");
            pst.setString(1, getName());
            pst.setInt(2,getAge() );
            pst.setString(3, getAddress());
            pst.setString(4, getPhoneNo());
            
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null,"Doctor added successfully");
                } else {
                    JOptionPane.showMessageDialog(null,"Error: Doctor not added");
                }
            
            con.close();
            
            
        }catch(SQLException e){e.printStackTrace();}
        
        
    }   
        public void loadDoctorTable() throws SQLException{
            String query = "SELECT * FROM doctors";
pst = con.prepareStatement(query);

rs = pst.executeQuery();

// Step 2: Create a DefaultTableModel
DefaultTableModel model = new DefaultTableModel();

// Step 3: Add rows of data to the table model
while (rs.next()) {
    Object[] row = new Object[5];
    row[0] = rs.getString("name");
    row[1] = rs.getInt("age");
    row[2] = rs.getString("address");
    row[3] = rs.getInt("phoneno");
    model.addRow(row);
}





        }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package clinicmanagementsystem;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.VerticalAlignment;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author user
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    
    public Home() {
        initComponents();
        showPieChart();
        doctortableLoad();
        patienttableLoad();
        updateDashboard();
        
        
        scaleImage("C:\\Users\\user\\Downloads\\pngaaa.com-1856321.png",logolabel);
        
        
        
    }
    
    
    
    private void scaleImage(String location, JLabel label){
        ImageIcon icon = new ImageIcon(location);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        label.setIcon(scaledIcon);
        

    }
    public void updateDashboard(){
        Connection con = null;
    Statement stmt = null;
    DriverManager dm;
    ResultSet rs = null;
    PreparedStatement pst = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicdb","root","root2468");
            pst = con.prepareStatement("SELECT COUNT(*) FROM patients");
            rs = pst.executeQuery();
            int numPatients = 0;
            if (rs.next()) {
                numPatients = rs.getInt(1);
                
            }
            
            PreparedStatement pst2 = con.prepareStatement("SELECT COUNT(*) FROM doctors");
            ResultSet rs2 = pst2.executeQuery();
            int numDoctors = 0;
            if (rs2.next()) {
                numDoctors = rs2.getInt(1);
                
            }
            PreparedStatement pst3 = con.prepareStatement("SELECT COUNT(prescription) FROM patients WHERE prescription != ''");
            ResultSet rs3 = pst3.executeQuery();
            int numPrescriptions = 0;
            if (rs3.next()) {
                numPrescriptions= rs3.getInt(1);
                
            }
            con.close();
            
            DoctorCount.setText(String.valueOf(numDoctors));
            PatientCount.setText(String.valueOf(numPatients));
            PrescriptionCount.setText(String.valueOf(numPrescriptions));
            

        
        }catch(SQLException e){e.printStackTrace(); }
    
    
    
    }
  
    public void showPieChart(){
        
      Connection con = null;
    Statement stmt = null;
    DriverManager dm;
    ResultSet rs = null;
    PreparedStatement pst = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicdb","root","root2468");
            pst = con.prepareStatement("SELECT course, COUNT(*) as count FROM patients GROUP BY course");
            rs = pst.executeQuery();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
            while (rs.next()) {
            dataset.setValue(rs.getInt("count"), "Students", rs.getString("course"));
            JFreeChart barChart = ChartFactory.createBarChart("",           
         "",            
         "Number of Students", (CategoryDataset) dataset,          
         PlotOrientation.VERTICAL,           
         true, true, false);
            BarRenderer renderer = (BarRenderer) barChart.getCategoryPlot().getRenderer();
            renderer.setPaint(new Color(0,153,204));
            barChart.setBorderVisible(false);
      CategoryPlot plot = (CategoryPlot) barChart.getPlot();
      plot.setBackgroundPaint(DashboardPanel.getBackground());
      plot.setRangeGridlinesVisible( false );
      plot.setOutlinePaint(null);
     
        //create chartPanel to display chart(graph)
      ChartPanel barChartPanel2 = new ChartPanel(barChart);
        
        BarChartPanel.removeAll();
        BarChartPanel.setBackground(DashboardPanel.getBackground());
        BarChartPanel.add(barChartPanel2);
        
        BarChartPanel.validate();
            
        }
        
        }catch(SQLException e){e.printStackTrace(); }
     
 
  
    
      

        
    }
    public void showPieChart2(){
        
        //create dataset
      DefaultPieDataset barDataset2 = new DefaultPieDataset( );
      barDataset2.setValue( "fever" , new Double( 21 ) );  
      barDataset2.setValue( "headache" , new Double( 14 ) );   
      barDataset2.setValue( "stomachache" , new Double( 6 ) );    
      barDataset2.setValue( "nausea" , new Double( 5 ) );
      barDataset2.setValue( "sprain" , new Double( 4 ) );  
      barDataset2.setValue( "allergies" , new Double( 9 ) );  
      barDataset2.setValue( "cough" , new Double( 12 ) );  
      
      //create chart
       JFreeChart piechart2 = ChartFactory.createPieChart("",barDataset2, true,false,false);//explain
        
        PiePlot piePlot2 =(PiePlot) piechart2.getPlot();
      
       //changing pie chart blocks colors
        piePlot2.setSectionPaint("fever", new Color(255,255,102));
        piePlot2.setSectionPaint("headache", new Color(102,255,102));
        piePlot2.setSectionPaint("MM", new Color(255,102,153));
        piePlot2.setSectionPaint("ECE", new Color(0,204,204));
      
        piechart2.setBorderVisible(false);
        piePlot2.setLabelGenerator(null);
        piePlot2.setBackgroundPaint(DashboardPanel.getBackground());
        piePlot2.setLabelBackgroundPaint(null);
        piePlot2.setLabelOutlinePaint(null);
        piePlot2.setLabelShadowPaint(null);
        
        LegendTitle legend = piechart2.getLegend();
        legend.setFrame(BlockBorder.NONE);
        legend.setPosition(RectangleEdge.LEFT);
        
        legend.setVerticalAlignment(VerticalAlignment.CENTER);
        
        piePlot2.setOutlinePaint(null);
        piePlot2.setShadowYOffset(0);
        piePlot2.setShadowXOffset(0);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel2 = new ChartPanel(piechart2);
        /*
        PieChartPanel.removeAll();
        PieChartPanel.setBackground(DashboardPanel.getBackground());
        PieChartPanel.add(barChartPanel2);
        
        PieChartPanel.validate();*/
        
        
        
        
        
    }


  
    
    

   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Sidepane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ManageClinicButton = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        manageclinic_ind = new javax.swing.JPanel();
        dblabel2 = new javax.swing.JLabel();
        DboardButton = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        dashboard_ind = new javax.swing.JPanel();
        PatientLstButton = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        patientlst_ind = new javax.swing.JPanel();
        logolabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        DashboardPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        DoctorCount = new javax.swing.JLabel();
        doctorlabel = new javax.swing.JLabel();
        doctorsublabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        PrescriptionCount = new javax.swing.JLabel();
        doctorlabel1 = new javax.swing.JLabel();
        doctorsublabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        PatientCount = new javax.swing.JLabel();
        doctorlabel2 = new javax.swing.JLabel();
        doctorsublabel2 = new javax.swing.JLabel();
        BarChartPanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        PatientListPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        PatientNameTextfield = new javax.swing.JTextField();
        PatientAgeTextfield = new javax.swing.JTextField();
        PatientAddressTextfield = new javax.swing.JTextField();
        CourseTextfield = new javax.swing.JTextField();
        PatientPhonenoTextfield = new javax.swing.JTextField();
        PrescriptionTextfield = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        DiagnosisTextfield = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        AddPatientBtn = new javax.swing.JButton();
        EditPatientBtn = new javax.swing.JButton();
        DeletePatientBtn = new javax.swing.JButton();
        PatientIDTextfield = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        ManageClinicPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        DoctorNameTextfield = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        DoctorAgeTextfield = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        DoctorAddressTextfield = new javax.swing.JTextField();
        DoctorPhoneTextfield = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        AddDoctorBtn = new javax.swing.JButton();
        EditDoctorBtn = new javax.swing.JButton();
        DeleteDoctorBtn = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        DoctorIDTextfield = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(100, 100));
        setUndecorated(true);

        Sidepane.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("University Clinic");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Management System");

        ManageClinicButton.setBackground(new java.awt.Color(51, 153, 255));
        ManageClinicButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ManageClinicButtonMousePressed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(0, 102, 204));
        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinicmanagementsystem/add_user_group_woman_man_24px.png"))); // NOI18N
        jLabel4.setText("Manage Clinic");

        manageclinic_ind.setOpaque(false);
        manageclinic_ind.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout ManageClinicButtonLayout = new javax.swing.GroupLayout(ManageClinicButton);
        ManageClinicButton.setLayout(ManageClinicButtonLayout);
        ManageClinicButtonLayout.setHorizontalGroup(
            ManageClinicButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManageClinicButtonLayout.createSequentialGroup()
                .addComponent(manageclinic_ind, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ManageClinicButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ManageClinicButtonLayout.createSequentialGroup()
                    .addGap(141, 141, 141)
                    .addComponent(dblabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(142, Short.MAX_VALUE)))
        );
        ManageClinicButtonLayout.setVerticalGroup(
            ManageClinicButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ManageClinicButtonLayout.createSequentialGroup()
                .addComponent(manageclinic_ind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ManageClinicButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(ManageClinicButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ManageClinicButtonLayout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(dblabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
        );

        DboardButton.setBackground(new java.awt.Color(51, 153, 255));
        DboardButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DboardButtonMousePressed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 102, 204));
        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinicmanagementsystem/template_26px.png"))); // NOI18N
        jLabel6.setText("Dashboard");

        dashboard_ind.setOpaque(false);
        dashboard_ind.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout DboardButtonLayout = new javax.swing.GroupLayout(DboardButton);
        DboardButton.setLayout(DboardButtonLayout);
        DboardButtonLayout.setHorizontalGroup(
            DboardButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DboardButtonLayout.createSequentialGroup()
                .addComponent(dashboard_ind, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DboardButtonLayout.setVerticalGroup(
            DboardButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DboardButtonLayout.createSequentialGroup()
                .addComponent(dashboard_ind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DboardButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        PatientLstButton.setBackground(new java.awt.Color(51, 153, 255));
        PatientLstButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PatientLstButtonMousePressed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(0, 102, 204));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clinicmanagementsystem/google_forms_24px.png"))); // NOI18N
        jLabel7.setText("Patient List");

        patientlst_ind.setOpaque(false);
        patientlst_ind.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout PatientLstButtonLayout = new javax.swing.GroupLayout(PatientLstButton);
        PatientLstButton.setLayout(PatientLstButtonLayout);
        PatientLstButtonLayout.setHorizontalGroup(
            PatientLstButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PatientLstButtonLayout.createSequentialGroup()
                .addComponent(patientlst_ind, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PatientLstButtonLayout.setVerticalGroup(
            PatientLstButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PatientLstButtonLayout.createSequentialGroup()
                .addComponent(patientlst_ind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PatientLstButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout SidepaneLayout = new javax.swing.GroupLayout(Sidepane);
        Sidepane.setLayout(SidepaneLayout);
        SidepaneLayout.setHorizontalGroup(
            SidepaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ManageClinicButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(DboardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PatientLstButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(SidepaneLayout.createSequentialGroup()
                .addGroup(SidepaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SidepaneLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2))
                    .addGroup(SidepaneLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(logolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SidepaneLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel1)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        SidepaneLayout.setVerticalGroup(
            SidepaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidepaneLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(logolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(31, 31, 31)
                .addComponent(DboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PatientLstButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ManageClinicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 255));
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        DoctorCount.setFont(new java.awt.Font("Century Gothic", 1, 28)); // NOI18N
        DoctorCount.setForeground(new java.awt.Color(255, 255, 255));
        DoctorCount.setText("5");

        doctorlabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        doctorlabel.setForeground(new java.awt.Color(255, 255, 255));
        doctorlabel.setText("Doctors");

        doctorsublabel.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        doctorsublabel.setForeground(new java.awt.Color(255, 255, 255));
        doctorsublabel.setText("Total doctors today");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(doctorsublabel)
                    .addComponent(doctorlabel)
                    .addComponent(DoctorCount, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DoctorCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doctorlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doctorsublabel)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 102, 0));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        PrescriptionCount.setFont(new java.awt.Font("Century Gothic", 1, 28)); // NOI18N
        PrescriptionCount.setForeground(new java.awt.Color(255, 255, 255));
        PrescriptionCount.setText("9");

        doctorlabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        doctorlabel1.setForeground(new java.awt.Color(255, 255, 255));
        doctorlabel1.setText("Prescriptions");

        doctorsublabel1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        doctorsublabel1.setForeground(new java.awt.Color(255, 255, 255));
        doctorsublabel1.setText("Total prescriptions");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(doctorsublabel1)
                    .addComponent(doctorlabel1)
                    .addComponent(PrescriptionCount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PrescriptionCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doctorlabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doctorsublabel1)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 0, 51));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        PatientCount.setFont(new java.awt.Font("Century Gothic", 1, 28)); // NOI18N
        PatientCount.setForeground(new java.awt.Color(255, 255, 255));
        PatientCount.setText("0");

        doctorlabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        doctorlabel2.setForeground(new java.awt.Color(255, 255, 255));
        doctorlabel2.setText("Patients");

        doctorsublabel2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        doctorsublabel2.setForeground(new java.awt.Color(255, 255, 255));
        doctorsublabel2.setText("Total patients today");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(doctorsublabel2)
                    .addComponent(doctorlabel2)
                    .addComponent(PatientCount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PatientCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doctorlabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doctorsublabel2)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        BarChartPanel.setLayout(new java.awt.BorderLayout());

        jLabel14.setFont(new java.awt.Font("Century Gothic", 2, 15)); // NOI18N
        jLabel14.setText("Chart Statistics by Course");

        javax.swing.GroupLayout DashboardPanelLayout = new javax.swing.GroupLayout(DashboardPanel);
        DashboardPanel.setLayout(DashboardPanelLayout);
        DashboardPanelLayout.setHorizontalGroup(
            DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DashboardPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BarChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(DashboardPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(73, 73, 73))
            .addGroup(DashboardPanelLayout.createSequentialGroup()
                .addGap(359, 359, 359)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DashboardPanelLayout.setVerticalGroup(
            DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BarChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel14)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dashboard", DashboardPanel);

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Age", "Address", "Course", "Phone no.", "Prescription", "Diagnosis"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setBackground(new java.awt.Color(51, 153, 255));
        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 153, 255));
        jLabel5.setText("Patient List");

        PatientNameTextfield.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        PatientNameTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatientNameTextfieldActionPerformed(evt);
            }
        });

        PatientAgeTextfield.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        PatientAgeTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatientAgeTextfieldActionPerformed(evt);
            }
        });

        PatientAddressTextfield.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        PatientAddressTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatientAddressTextfieldActionPerformed(evt);
            }
        });

        CourseTextfield.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        CourseTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CourseTextfieldActionPerformed(evt);
            }
        });

        PatientPhonenoTextfield.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        PatientPhonenoTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatientPhonenoTextfieldActionPerformed(evt);
            }
        });

        PrescriptionTextfield.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel8.setText("Name");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel9.setText("Age");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel10.setText("Address");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel11.setText("Course");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel12.setText("Phone no.");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel13.setText("Prescription");

        DiagnosisTextfield.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        DiagnosisTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiagnosisTextfieldActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel24.setText("Diagnosis");

        jComboBox1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BS-CS", "BS-IT", "BS-CE", "BS-MM", "BS-BA", "BS-CE", "BS-ECE", "BS-EE", " " }));
        jComboBox1.setBorder(null);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        AddPatientBtn.setBackground(new java.awt.Color(51, 153, 255));
        AddPatientBtn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        AddPatientBtn.setForeground(new java.awt.Color(255, 255, 255));
        AddPatientBtn.setText("Add");
        AddPatientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPatientBtnActionPerformed(evt);
            }
        });

        EditPatientBtn.setBackground(new java.awt.Color(51, 153, 255));
        EditPatientBtn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        EditPatientBtn.setForeground(new java.awt.Color(255, 255, 255));
        EditPatientBtn.setText("Edit");
        EditPatientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditPatientBtnActionPerformed(evt);
            }
        });

        DeletePatientBtn.setBackground(new java.awt.Color(255, 102, 255));
        DeletePatientBtn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        DeletePatientBtn.setForeground(new java.awt.Color(255, 255, 255));
        DeletePatientBtn.setText("Delete");
        DeletePatientBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeletePatientBtnMouseClicked(evt);
            }
        });

        PatientIDTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatientIDTextfieldActionPerformed(evt);
            }
        });

        jLabel16.setText("ID");

        javax.swing.GroupLayout PatientListPanelLayout = new javax.swing.GroupLayout(PatientListPanel);
        PatientListPanel.setLayout(PatientListPanelLayout);
        PatientListPanelLayout.setHorizontalGroup(
            PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PatientListPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(PatientListPanelLayout.createSequentialGroup()
                .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PatientListPanelLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PatientListPanelLayout.createSequentialGroup()
                                .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(27, 27, 27)
                                .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PatientAgeTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PatientNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PatientListPanelLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(PatientAddressTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PatientListPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AddPatientBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PatientListPanelLayout.createSequentialGroup()
                        .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PatientListPanelLayout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(EditPatientBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78)
                                .addComponent(DeletePatientBtn))
                            .addGroup(PatientListPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel12)
                                .addGap(14, 14, 14)
                                .addComponent(PatientPhonenoTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PatientListPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PrescriptionTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PatientListPanelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(CourseTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel16))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DiagnosisTextfield, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(PatientIDTextfield))
                .addGap(98, 98, 98))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PatientListPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 927, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PatientListPanelLayout.setVerticalGroup(
            PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PatientListPanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PatientNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CourseTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel11)
                        .addComponent(DiagnosisTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24)))
                .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PatientListPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(PatientAgeTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(PatientPhonenoTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PatientListPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PatientIDTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PatientListPanelLayout.createSequentialGroup()
                        .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PatientAddressTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(PrescriptionTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 465, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PatientListPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(80, 80, 80)
                        .addGroup(PatientListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditPatientBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DeletePatientBtn)
                            .addComponent(AddPatientBtn))
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );

        jTabbedPane1.addTab("Patient List", PatientListPanel);

        jLabel17.setBackground(new java.awt.Color(51, 153, 255));
        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 153, 255));
        jLabel17.setText("Manage Clinic");

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel18.setText("Name");

        DoctorNameTextfield.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        DoctorNameTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoctorNameTextfieldActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel19.setText("Age");

        DoctorAgeTextfield.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        DoctorAgeTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoctorAgeTextfieldActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel20.setText("Address");

        DoctorAddressTextfield.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        DoctorAddressTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoctorAddressTextfieldActionPerformed(evt);
            }
        });

        DoctorPhoneTextfield.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        DoctorPhoneTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoctorPhoneTextfieldActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel22.setText("Phone no.");

        jTable2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Age", "Address", "Phone No."
            }
        ));
        jTable2.setShowGrid(true);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        AddDoctorBtn.setBackground(new java.awt.Color(51, 153, 255));
        AddDoctorBtn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        AddDoctorBtn.setForeground(new java.awt.Color(255, 255, 255));
        AddDoctorBtn.setText("Add");
        AddDoctorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDoctorBtnActionPerformed(evt);
            }
        });

        EditDoctorBtn.setBackground(new java.awt.Color(51, 153, 255));
        EditDoctorBtn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        EditDoctorBtn.setForeground(new java.awt.Color(255, 255, 255));
        EditDoctorBtn.setText("Edit");
        EditDoctorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditDoctorBtnActionPerformed(evt);
            }
        });

        DeleteDoctorBtn.setBackground(new java.awt.Color(255, 102, 255));
        DeleteDoctorBtn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        DeleteDoctorBtn.setForeground(new java.awt.Color(255, 255, 255));
        DeleteDoctorBtn.setText("Delete");
        DeleteDoctorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteDoctorBtnActionPerformed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(51, 153, 255));
        jLabel15.setFont(new java.awt.Font("Century Gothic", 3, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 153, 255));
        jLabel15.setText("Doctors List");

        jLabel21.setText("ID");

        javax.swing.GroupLayout ManageClinicPanelLayout = new javax.swing.GroupLayout(ManageClinicPanel);
        ManageClinicPanel.setLayout(ManageClinicPanelLayout);
        ManageClinicPanelLayout.setHorizontalGroup(
            ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManageClinicPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(ManageClinicPanelLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addContainerGap())))
            .addGroup(ManageClinicPanelLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addGroup(ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addGroup(ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ManageClinicPanelLayout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jLabel22))
                    .addGroup(ManageClinicPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(DoctorAgeTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DoctorPhoneTextfield, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(DoctorIDTextfield))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ManageClinicPanelLayout.createSequentialGroup()
                .addGroup(ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ManageClinicPanelLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DoctorAddressTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ManageClinicPanelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ManageClinicPanelLayout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addGroup(ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addGroup(ManageClinicPanelLayout.createSequentialGroup()
                                .addComponent(AddDoctorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99)
                                .addComponent(EditDoctorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(61, 61, 61)
                        .addComponent(DeleteDoctorBtn)))
                .addGap(0, 29, Short.MAX_VALUE))
            .addGroup(ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ManageClinicPanelLayout.createSequentialGroup()
                    .addGap(162, 162, 162)
                    .addComponent(DoctorNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        ManageClinicPanelLayout.setVerticalGroup(
            ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManageClinicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel22)
                    .addComponent(DoctorPhoneTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ManageClinicPanelLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(33, 33, 33)
                        .addGroup(ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(DoctorAddressTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DoctorAgeTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DoctorIDTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21)))
                .addGap(35, 35, 35)
                .addGroup(ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditDoctorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteDoctorBtn)
                    .addComponent(AddDoctorBtn))
                .addGap(22, 22, 22)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(201, Short.MAX_VALUE))
            .addGroup(ManageClinicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ManageClinicPanelLayout.createSequentialGroup()
                    .addGap(71, 71, 71)
                    .addComponent(DoctorNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(570, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Manage Clinic", ManageClinicPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Sidepane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Sidepane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    void setColor(JPanel panel){
        panel.setBackground(new Color(77,169,231));
        
    }
    void resetColor(JPanel panel){
        panel.setBackground(new Color(51,153,255));
    }
    private void DboardButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DboardButtonMousePressed
        // TODO add your handling code here:
        setColor(DboardButton);
        resetColor(PatientLstButton);
        resetColor(ManageClinicButton);
        dashboard_ind.setOpaque(true);
        manageclinic_ind.setOpaque(false);
        patientlst_ind.setOpaque(false);
        jTabbedPane1.setSelectedIndex(0);
        showPieChart();
        updateDashboard();
    }//GEN-LAST:event_DboardButtonMousePressed

    private void PatientLstButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PatientLstButtonMousePressed
        // TODO add your handling code here:
        setColor(PatientLstButton);
        resetColor(DboardButton);
        resetColor(ManageClinicButton);
        dashboard_ind.setOpaque(false);
        manageclinic_ind.setOpaque(false);
        patientlst_ind.setOpaque(true);
        jTabbedPane1.setSelectedIndex(1);
        
    }//GEN-LAST:event_PatientLstButtonMousePressed

    private void ManageClinicButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManageClinicButtonMousePressed
        // TODO add your handling code here:
        setColor(ManageClinicButton);
        resetColor(PatientLstButton);
        resetColor(DboardButton);
        dashboard_ind.setOpaque(false);
        manageclinic_ind.setOpaque(true);
        patientlst_ind.setOpaque(false);
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_ManageClinicButtonMousePressed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel3MousePressed

    private void CourseTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CourseTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CourseTextfieldActionPerformed

    private void PatientNameTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatientNameTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PatientNameTextfieldActionPerformed

    private void PatientAddressTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatientAddressTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PatientAddressTextfieldActionPerformed

    private void PatientPhonenoTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatientPhonenoTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PatientPhonenoTextfieldActionPerformed

    private void DoctorNameTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoctorNameTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DoctorNameTextfieldActionPerformed

    private void DoctorAddressTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoctorAddressTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DoctorAddressTextfieldActionPerformed

    private void DoctorPhoneTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoctorPhoneTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DoctorPhoneTextfieldActionPerformed

    private void DoctorAgeTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoctorAgeTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DoctorAgeTextfieldActionPerformed

    private void DiagnosisTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiagnosisTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosisTextfieldActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String course = jComboBox1.getSelectedItem().toString();
        CourseTextfield.setText(course);
    }//GEN-LAST:event_jComboBox1ActionPerformed
  
    private void AddPatientBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPatientBtnActionPerformed
        // TODO add your handling code here:
        String patientName = PatientNameTextfield.getText();
        int patientAge = Integer.valueOf(PatientAgeTextfield.getText());
        String patientAddress = PatientAddressTextfield.getText();
        String patientCourse = CourseTextfield.getText();
        String patientPhoneNo = PatientPhonenoTextfield.getText();
        String patientPrescription = PrescriptionTextfield.getText();
        String patientDiagnosis = 
                DiagnosisTextfield.getText();
        
        Patient patient = new Patient(patientName,patientAge,patientAddress,patientCourse,patientPhoneNo,patientPrescription,patientDiagnosis );
        patient.addRecord();
        PatientNameTextfield.setText(" ");
        PatientAgeTextfield.setText(" ");
        PatientAddressTextfield.setText(" ");
        CourseTextfield.setText(" ");
        PatientPhonenoTextfield.setText(" ");
        PrescriptionTextfield.setText(" ");
        DiagnosisTextfield.setText(" ");
        
        patienttableLoad();
        
     
    }//GEN-LAST:event_AddPatientBtnActionPerformed

    private void EditPatientBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditPatientBtnActionPerformed
        // TODO add your handling code here:
        Connection con = null;
    
    DriverManager dm;
    ResultSet rs = null;
    PreparedStatement pst = null;
    try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicdb","root","root2468");
            
            String query = "UPDATE patients set name=?,age=?,address=?,course=?, phoneno=?, prescription=?, diagnosis=? where patientid=?";
            pst = con.prepareStatement(query);
            pst.setString(1, PatientNameTextfield.getText());
            pst.setString(2, PatientAgeTextfield.getText());
            pst.setString(3, PatientAddressTextfield.getText());
            pst.setString(4, CourseTextfield.getText());
            pst.setString(5, PatientPhonenoTextfield.getText());
            pst.setString(6, PrescriptionTextfield.getText());
            pst.setString(7, DiagnosisTextfield.getText());
            pst.setString(8, PatientIDTextfield.getText());
            
            pst.executeUpdate();
 
            patienttableLoad();
            JOptionPane.showMessageDialog(this,"Patient Successfully Updated");
            PatientNameTextfield.setText(" ");
            PatientAgeTextfield.setText(" ");
            PatientAddressTextfield.setText(" ");
            CourseTextfield.setText(" ");
            PatientPhonenoTextfield.setText(" ");
            PrescriptionTextfield.setText(" ");
            DiagnosisTextfield.setText(" ");
            PatientIDTextfield.setText(" ");
            
        }catch(SQLException e){e.printStackTrace(); }

     
        
    }//GEN-LAST:event_EditPatientBtnActionPerformed

    private void PatientAgeTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatientAgeTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PatientAgeTextfieldActionPerformed

    private void AddDoctorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddDoctorBtnActionPerformed
        // TODO add your handling code here:
        String doctorName = DoctorNameTextfield.getText();
        int doctorAge = Integer.valueOf(DoctorAgeTextfield.getText());
        String doctorAddress = DoctorAddressTextfield.getText();
        String doctorPhoneNo = DoctorPhoneTextfield.getText();
        
        Doctors doc = new Doctors(doctorName,doctorAge,doctorAddress,doctorPhoneNo);
        doc.addRecord();
        
        DoctorNameTextfield.setText(" ");
        DoctorAgeTextfield.setText(" ");
        DoctorAddressTextfield.setText(" ");
        DoctorPhoneTextfield.setText(" "); 
        doctortableLoad();

        
        
    }//GEN-LAST:event_AddDoctorBtnActionPerformed
    public void doctortableLoad(){
         Connection con = null;
    Statement stmt = null;
    DriverManager dm;
    ResultSet rs = null;
    PreparedStatement pst = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicdb","root","root2468");
            pst = con.prepareStatement("select * from doctors");
            rs = pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));

        
        }catch(SQLException e){e.printStackTrace(); }
    
    
    
    }
    public void patienttableLoad(){
            Connection con = null;
    Statement stmt = null;
    DriverManager dm;
    ResultSet rs = null;
    PreparedStatement pst = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicdb","root","root2468");
            pst = con.prepareStatement("select * from patients");
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));

        
        }catch(SQLException e){e.printStackTrace(); }
    
    
    
    }
    private void EditDoctorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditDoctorBtnActionPerformed
        // TODO add your handling code here:
        Connection con = null;
    
    DriverManager dm;
    ResultSet rs = null;
    PreparedStatement pst = null;
    try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicdb","root","root2468");
            
            String query = "UPDATE doctors set name=?,age=?,address=?,phoneno=? where doctorid=?";
            pst = con.prepareStatement(query);
            pst.setString(1, DoctorNameTextfield.getText());
            pst.setString(2, DoctorAgeTextfield.getText());
            pst.setString(3, DoctorAddressTextfield.getText());
            pst.setString(4, DoctorPhoneTextfield.getText());
            pst.setString(5, DoctorIDTextfield.getText());
            pst.executeUpdate();
 
            doctortableLoad();
            JOptionPane.showMessageDialog(this,"Successfully Updated");
            DoctorNameTextfield.setText(" ");
            DoctorAgeTextfield.setText(" ");
            DoctorAddressTextfield.setText(" ");
            DoctorPhoneTextfield.setText(" ");
            DoctorIDTextfield.setText(" ");
            
            
        }catch(SQLException e){e.printStackTrace(); }
        
    }//GEN-LAST:event_EditDoctorBtnActionPerformed
                                 

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        int Myindex = jTable1.getSelectedRow();
        
        PatientNameTextfield.setText(model.getValueAt(Myindex, 1).toString());
        PatientAgeTextfield. setText (model. getValueAt (Myindex, 2).toString ());
        PatientAddressTextfield.setText(model.getValueAt(Myindex, 3).toString ());
        CourseTextfield.setText(model.getValueAt(Myindex, 4).toString ());
        PatientPhonenoTextfield.setText(model.getValueAt(Myindex, 5).toString ());
        PrescriptionTextfield.setText(model.getValueAt(Myindex, 6).toString ());
        DiagnosisTextfield.setText(model.getValueAt(Myindex, 7).toString ());
        PatientIDTextfield.setText(model.getValueAt(Myindex, 0).toString ());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
        int Myindex = jTable2.getSelectedRow();
        
        DoctorNameTextfield.setText(model.getValueAt(Myindex, 1).toString());
        DoctorAgeTextfield. setText (model. getValueAt (Myindex, 2).toString ());
        DoctorAddressTextfield.setText(model.getValueAt(Myindex, 3).toString ());
        DoctorPhoneTextfield.setText(model.getValueAt(Myindex, 4).toString ());
        DoctorIDTextfield.setText(model.getValueAt(Myindex, 0).toString ());
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void DeleteDoctorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteDoctorBtnActionPerformed
        // TODO add your handling code here:
        Connection con = null;
    
        DriverManager dm;
        ResultSet rs = null;
        PreparedStatement pst = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicdb","root","root2468");
            String docID = DoctorIDTextfield.getText();
            String query = "DELETE FROM doctors where doctorid="+docID;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            doctortableLoad();
            JOptionPane.showMessageDialog(this,"Patient was successfully deleted");
            DoctorNameTextfield.setText(" ");
            DoctorAgeTextfield.setText(" ");
            DoctorAddressTextfield.setText(" ");
            DoctorPhoneTextfield.setText(" ");
            
            
        }catch(SQLException e){e.printStackTrace(); }
           
    }//GEN-LAST:event_DeleteDoctorBtnActionPerformed

    private void PatientIDTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatientIDTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PatientIDTextfieldActionPerformed

    private void DeletePatientBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeletePatientBtnMouseClicked
        // TODO add your handling code here:
        Connection con = null;
    
    DriverManager dm;
    ResultSet rs = null;
    PreparedStatement pst = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicdb","root","root2468");
            int  patientID = Integer.valueOf(PatientIDTextfield.getText());
            String query = "DELETE FROM patients where patientid="+patientID;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            patienttableLoad();
            JOptionPane.showMessageDialog(this,"Patient was successfully deleted");
            PatientNameTextfield.setText(" ");
            PatientAgeTextfield.setText(" ");
            PatientAddressTextfield.setText(" ");
            CourseTextfield.setText(" ");
            PatientPhonenoTextfield.setText(" ");
            PrescriptionTextfield.setText(" ");
            DiagnosisTextfield.setText(" ");
            
        }catch(SQLException e){e.printStackTrace(); }
    }//GEN-LAST:event_DeletePatientBtnMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddDoctorBtn;
    private javax.swing.JButton AddPatientBtn;
    private javax.swing.JPanel BarChartPanel;
    private javax.swing.JTextField CourseTextfield;
    private javax.swing.JPanel DashboardPanel;
    private javax.swing.JPanel DboardButton;
    private javax.swing.JButton DeleteDoctorBtn;
    private javax.swing.JButton DeletePatientBtn;
    private javax.swing.JTextField DiagnosisTextfield;
    private javax.swing.JTextField DoctorAddressTextfield;
    private javax.swing.JTextField DoctorAgeTextfield;
    private javax.swing.JLabel DoctorCount;
    private javax.swing.JTextField DoctorIDTextfield;
    private javax.swing.JTextField DoctorNameTextfield;
    private javax.swing.JTextField DoctorPhoneTextfield;
    private javax.swing.JButton EditDoctorBtn;
    private javax.swing.JButton EditPatientBtn;
    private javax.swing.JPanel ManageClinicButton;
    private javax.swing.JPanel ManageClinicPanel;
    private javax.swing.JTextField PatientAddressTextfield;
    private javax.swing.JTextField PatientAgeTextfield;
    private javax.swing.JLabel PatientCount;
    private javax.swing.JTextField PatientIDTextfield;
    private javax.swing.JPanel PatientListPanel;
    private javax.swing.JPanel PatientLstButton;
    private javax.swing.JTextField PatientNameTextfield;
    private javax.swing.JTextField PatientPhonenoTextfield;
    private javax.swing.JLabel PrescriptionCount;
    private javax.swing.JTextField PrescriptionTextfield;
    private javax.swing.JPanel Sidepane;
    private javax.swing.JPanel dashboard_ind;
    private javax.swing.JLabel dblabel2;
    private javax.swing.JLabel doctorlabel;
    private javax.swing.JLabel doctorlabel1;
    private javax.swing.JLabel doctorlabel2;
    private javax.swing.JLabel doctorsublabel;
    private javax.swing.JLabel doctorsublabel1;
    private javax.swing.JLabel doctorsublabel2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel logolabel;
    private javax.swing.JPanel manageclinic_ind;
    private javax.swing.JPanel patientlst_ind;
    // End of variables declaration//GEN-END:variables
}

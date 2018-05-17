/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.AnalystGroupEmployee;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Organization.AnalystOrganization;
import Business.Organization.CentralBodyOrganization;
import Business.Organization.Organization;
import Business.Organization.StateBodyOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.AnalysisWorkRequest;
import Business.WorkQueue.WorkRequest;
import Interface.CentralBodyEmployee.TransportationJPanel;
import java.awt.CardLayout;
import java.util.Enumeration;
import java.util.Random;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 *
 */
public class AnalystEmployeeWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form AnalystEmployeeWorkAreaJPanel
     */
    private int requiredWaterLevel;
    private int currentWaterLevel;
    private double proposedBudget;
    private JPanel userProcessContainer;
    private UserAccount account;
    private AnalystOrganization analystOrganization;
    private Enterprise enterprise;
    private AnalysisWorkRequest selectedRequest;
    String artificialMethod;
    double waterAllowedArtificial;
    double waterAllowedTransport;
    
    public AnalystEmployeeWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        analystOrganization = (AnalystOrganization) organization;
        this.enterprise = enterprise;
        populateAnalysisTable();
        processJButton.setEnabled(false);
       
        
    }

    public AnalystEmployeeWorkAreaJPanel(JPanel userProcessContainer, AnalysisWorkRequest request, UserAccount account, Organization organization, Enterprise enterprise, double waterAllowedInter) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    initComponents();
    this.userProcessContainer = userProcessContainer;
    this.account = account;
    analystOrganization = (AnalystOrganization) organization;
    selectedRequest=request;
    this.enterprise = enterprise;
    waterAllowedTransport = waterAllowedInter;
    populateAnalysisTable();
    populateJTextFieldsForInterStateTransportation(request);
    processJButton.setEnabled(false);
    
    }

    
    public void populateAnalysisTable() {
        DefaultTableModel model = (DefaultTableModel) analystjTable.getModel();
        model.setRowCount(0);
     
        for (WorkRequest request : analystOrganization.getWorkQueue().getForecastWorkRequests()) {
            Object[] row = new Object[8];
            row[0] = request;
            row[1] = request.getWaterLevel();
            row[2] = ((AnalysisWorkRequest) request).getForecast();
            row[3] = ((AnalysisWorkRequest) request).getStateSender();
            row[4] = ((AnalysisWorkRequest) request).getAnalystReceiver();
            String alternate = ((AnalysisWorkRequest) request).getArtificialMethod();
            row[5] = alternate == null ? "Not Checked" : alternate;
            row[6] = ((AnalysisWorkRequest) request).getBudgetRequired();
            row[7] = request.getStatus();
            model.addRow(row);
        }
    }
    
    //function to generate Artificial method based on water usage
    public String generateArtificialMethod(double waterUsage)
    {
        String string=" ";
        if(waterUsage>=9 && waterUsage<=19)
            string= "Injection well";
        else if (waterUsage>19 && waterUsage<=29 )
            string= "Spreading Channel";
        else if (waterUsage>29 && waterUsage<=39)
            string= "Percolation Tank";
        else if (waterUsage>39 && waterUsage<=49)
            string= "stream augmentation";
        else if (waterUsage>49 && waterUsage<=59)
            string= "ditch and furrow system";
        else if (waterUsage>59 && waterUsage<=69)
            string = "dug well";
        return string;
       
    }
    
    //function to generate basic budget based on the ertificial method and soil type
    public double generateMethodBudget(String artificialMethod, String soilType){
        if (artificialMethod.equalsIgnoreCase("Injection well") && soilType.equalsIgnoreCase("Mountain Soil"))
                proposedBudget=1000;
        else if (artificialMethod.equalsIgnoreCase("Injection well") && soilType.equalsIgnoreCase("Desert Soil"))
                proposedBudget=2000;
        else if (artificialMethod.equalsIgnoreCase("Injection well") && soilType.equalsIgnoreCase("Prairie Soil"))
                proposedBudget=3000;
        else if (artificialMethod.equalsIgnoreCase("Injection well") && soilType.equalsIgnoreCase("Glacial Soil"))
                proposedBudget=4000;
        else if (artificialMethod.equalsIgnoreCase("Injection well") && soilType.equalsIgnoreCase("Wetland Soil"))
                proposedBudget=5000;
        else if (artificialMethod.equalsIgnoreCase("Injection well") && soilType.equalsIgnoreCase("River Soil"))
                proposedBudget=6000;
        else if (artificialMethod.equalsIgnoreCase("Injection well") && soilType.equalsIgnoreCase("Temperate Soil"))
                proposedBudget=7000;
        else if (artificialMethod.equalsIgnoreCase("Spreading Channel") && soilType.equalsIgnoreCase("Mountain Soil"))
                proposedBudget=1500;
        else if (artificialMethod.equalsIgnoreCase("Spreading Channel") && soilType.equalsIgnoreCase("Desert Soil"))
                proposedBudget=2500;
        else if (artificialMethod.equalsIgnoreCase("Spreading Channel") && soilType.equalsIgnoreCase("Prairie Soil"))
                proposedBudget=3500;
        else if (artificialMethod.equalsIgnoreCase("Spreading Channel") && soilType.equalsIgnoreCase("Glacial Soil"))
                proposedBudget=4500;
        else if (artificialMethod.equalsIgnoreCase("Spreading Channel") && soilType.equalsIgnoreCase("Wetland Soil"))
                proposedBudget=5500;
        else if (artificialMethod.equalsIgnoreCase("Spreading Channel") && soilType.equalsIgnoreCase("River Soil"))
                proposedBudget=6500;
        else if (artificialMethod.equalsIgnoreCase("Spreading Channel") && soilType.equalsIgnoreCase("Temperate Soil"))
                proposedBudget=7500;
        else if (artificialMethod.equalsIgnoreCase("Percolation Tank") && soilType.equalsIgnoreCase("Mountain Soil"))
                proposedBudget=1555;
        else if (artificialMethod.equalsIgnoreCase("Percolation Tank") && soilType.equalsIgnoreCase("Desert Soil"))
                proposedBudget=2555;
        else if (artificialMethod.equalsIgnoreCase("Percolation Tank") && soilType.equalsIgnoreCase("Prairie Soil"))
                proposedBudget=3555;
        else if (artificialMethod.equalsIgnoreCase("Percolation Tank") && soilType.equalsIgnoreCase("Glacial Soil"))
                proposedBudget=4555;
        else if (artificialMethod.equalsIgnoreCase("Percolation Tank") && soilType.equalsIgnoreCase("Wetland Soil"))
                proposedBudget=5555;
        else if (artificialMethod.equalsIgnoreCase("Percolation Tank") && soilType.equalsIgnoreCase("River Soil"))
                proposedBudget=6555;
        else if (artificialMethod.equalsIgnoreCase("Percolation Tank") && soilType.equalsIgnoreCase("Temperate Soil"))
                proposedBudget=7555;
        else if (artificialMethod.equalsIgnoreCase("stream augmentation") && soilType.equalsIgnoreCase("Mountain Soil"))
               proposedBudget=1510;
       else if (artificialMethod.equalsIgnoreCase("stream augmentation") && soilType.equalsIgnoreCase("Desert Soil"))
               proposedBudget=2510;
       else if (artificialMethod.equalsIgnoreCase("stream augmentation") && soilType.equalsIgnoreCase("Prairie Soil"))
               proposedBudget=3510;
       else if (artificialMethod.equalsIgnoreCase("stream augmentation") && soilType.equalsIgnoreCase("Glacial Soil"))
               proposedBudget=4510;
       else if (artificialMethod.equalsIgnoreCase("stream augmentation") && soilType.equalsIgnoreCase("Wetland Soil"))
               proposedBudget=5510;
       else if (artificialMethod.equalsIgnoreCase("stream augmentation") && soilType.equalsIgnoreCase("River Soil"))
               proposedBudget=6510;
       else if (artificialMethod.equalsIgnoreCase("stream augmentation") && soilType.equalsIgnoreCase("Temperate Soil"))
               proposedBudget=7510;
       else if (artificialMethod.equalsIgnoreCase("ditch and furrow system") && soilType.equalsIgnoreCase("Mountain Soil"))
               proposedBudget=1540;
       else if (artificialMethod.equalsIgnoreCase("ditch and furrow system") && soilType.equalsIgnoreCase("Desert Soil"))
               proposedBudget=2540;
       else if (artificialMethod.equalsIgnoreCase("ditch and furrow system") && soilType.equalsIgnoreCase("Prairie Soil"))
               proposedBudget=3540;
       else if (artificialMethod.equalsIgnoreCase("ditch and furrow system") && soilType.equalsIgnoreCase("Glacial Soil"))
               proposedBudget=4540;
       else if (artificialMethod.equalsIgnoreCase("ditch and furrow system") && soilType.equalsIgnoreCase("Wetland Soil"))
               proposedBudget=5540;
       else if (artificialMethod.equalsIgnoreCase("ditch and furrow system") && soilType.equalsIgnoreCase("River Soil"))
               proposedBudget=6540;
       else if (artificialMethod.equalsIgnoreCase("ditch and furrow system") && soilType.equalsIgnoreCase("Temperate Soil"))
               proposedBudget=7540;
       else if (artificialMethod.equalsIgnoreCase("dug well") && soilType.equalsIgnoreCase("Mountain Soil"))
               proposedBudget=1590;
       else if (artificialMethod.equalsIgnoreCase("dug well") && soilType.equalsIgnoreCase("Desert Soil"))
               proposedBudget=2590;
       else if (artificialMethod.equalsIgnoreCase("dug well") && soilType.equalsIgnoreCase("Prairie Soil"))
               proposedBudget=3590;
       else if (artificialMethod.equalsIgnoreCase("dug well") && soilType.equalsIgnoreCase("Glacial Soil"))
               proposedBudget=4590;
       else if (artificialMethod.equalsIgnoreCase("dug well") && soilType.equalsIgnoreCase("Wetland Soil"))
               proposedBudget=5590;
       else if (artificialMethod.equalsIgnoreCase("dug well") && soilType.equalsIgnoreCase("River Soil"))
               proposedBudget=6590;
       else if (artificialMethod.equalsIgnoreCase("dug well") && soilType.equalsIgnoreCase("Temperate Soil"))
               proposedBudget=7590;
        else proposedBudget=1;
        return proposedBudget;
    }
    
    //function to generate complete budget for one week based on the artificial method and water usage
    public double calculateCompleteBudget(double methodBudget, double waterRequired)
    {
        double finalBudget=methodBudget*waterRequired;
        return finalBudget;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WaterReplenishmentStrategy = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        requiredWaterLeveljTextField = new javax.swing.JTextField();
        currentWaterLeveljTextField = new javax.swing.JTextField();
        artificialTechniquejTextField = new javax.swing.JTextField();
        costOfArtificialMethodjTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        analystjTable = new javax.swing.JTable();
        assignjButton = new javax.swing.JButton();
        processJButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        soilTypejTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        checkTransportation = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        interstateCostjTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        requiredBudgetjTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Required Water Level :");

        jLabel2.setText("Current Water Level :");

        jLabel3.setText("Artificial Method Name:");

        jLabel4.setText("Check Cost of Interstate Transportation?");

        analystjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Water Status", "Water Level", "Weather Forecast", "Sender", "Receiver", "Artificial Method", "Budget", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(analystjTable);

        assignjButton.setBackground(new java.awt.Color(0, 102, 153));
        assignjButton.setForeground(new java.awt.Color(255, 255, 255));
        assignjButton.setText("Assign to Me");
        assignjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignjButtonActionPerformed(evt);
            }
        });

        processJButton.setBackground(new java.awt.Color(0, 102, 153));
        processJButton.setForeground(new java.awt.Color(255, 255, 255));
        processJButton.setText("Process");
        processJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processJButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Soil Type:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Get Budget");

        jLabel7.setText("Required Budget:");

        checkTransportation.setBackground(new java.awt.Color(0, 102, 153));
        checkTransportation.setForeground(new java.awt.Color(255, 255, 255));
        checkTransportation.setText("Check");
        checkTransportation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTransportationActionPerformed(evt);
            }
        });

        jLabel8.setText("Interstate Transportation Cost");

        jLabel9.setText("Cost of using Artificial Method:");

        jLabel10.setText("Water Replenishment Strategy:");

        WaterReplenishmentStrategy.add(jRadioButton1);
        jRadioButton1.setText("Artificial Water Source");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        WaterReplenishmentStrategy.add(jRadioButton2);
        jRadioButton2.setText("Interstate Transport");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Apply");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(192, 192, 192)
                            .addComponent(assignjButton)
                            .addGap(443, 443, 443)
                            .addComponent(processJButton))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(263, 263, 263)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5)
                                .addComponent(jLabel1))
                            .addGap(150, 150, 150)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(requiredWaterLeveljTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(soilTypejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(currentWaterLeveljTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(artificialTechniquejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(costOfArtificialMethodjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(82, 82, 82)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel8))
                                            .addGap(26, 26, 26)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(checkTransportation, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(interstateCostjTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(100, 100, 100)
                                            .addComponent(requiredBudgetjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(78, 78, 78)
                                    .addComponent(jRadioButton1)
                                    .addGap(74, 74, 74)
                                    .addComponent(jRadioButton2))))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(54, 54, 54)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(processJButton)
                    .addComponent(assignjButton))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(requiredWaterLeveljTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(currentWaterLeveljTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soilTypejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(artificialTechniquejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(checkTransportation, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(costOfArtificialMethodjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(interstateCostjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jButton1))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(requiredBudgetjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(66, 66, 66))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void assignjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignjButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = analystjTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the above table", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        selectedRequest = (AnalysisWorkRequest) analystjTable.getValueAt(selectedRow, 0);
        if (selectedRequest.getStatus().equalsIgnoreCase("closed")) {
            JOptionPane.showMessageDialog(null, "This Request is already closed", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            selectedRequest.setAnalystReceiver(account);
            selectedRequest.setStatus("Received By Analyst Team");
            populateAnalysisTable();
            processJButton.setEnabled(true);
           
        }
    }//GEN-LAST:event_assignjButtonActionPerformed

    private void processJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processJButtonActionPerformed
        // TODO add your handling code here:

        populateJTextFields();
        
        //requiredWaterLeveljTextField.setText(request.get);
    }//GEN-LAST:event_processJButtonActionPerformed

    private void checkTransportationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTransportationActionPerformed
        // TODO add your handling code here:
        int selectedRow = analystjTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the above table", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
//        selectedRequest = (AnalysisWorkRequest) analystjTable.getValueAt(selectedRow, 0);
else
        {
        TransportationJPanel panel=new TransportationJPanel(userProcessContainer, selectedRequest, account, analystOrganization, enterprise);
        //UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business
        userProcessContainer.add("TransportationJPanel",panel);
        CardLayout layout= (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
        }
    }//GEN-LAST:event_checkTransportationActionPerformed
    
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    String decision="No";
        
        Enumeration<AbstractButton> buttons= WaterReplenishmentStrategy.getElements();
        
        while (buttons.hasMoreElements())
         {
            JRadioButton approval= (JRadioButton) buttons.nextElement();
            if (approval.isSelected()) 
            {
                decision=approval.getText();
            }

         }
        
      if(decision.equals("Artificial Water Source"))
      {
          System.out.println(selectedRequest);
          selectedRequest.setBudgetRequired(selectedRequest.getArtificalMethodCost());
          selectedRequest.setWaterAllowed(waterAllowedArtificial);
          System.out.println("waterAllowedArtificial waterAllowedArtificial waterAllowedArtificial " +waterAllowedArtificial);
          requiredBudgetjTextField.setText(String.valueOf(selectedRequest.getArtificalMethodCost()));
          selectedRequest.setInterStateDistance(0);
          selectedRequest.setStatus("Analysis Done");
          selectedRequest.setTransportFlag(0);
      }

      if(decision.equals("Interstate Transport"))
      {
          System.out.println(selectedRequest);
          selectedRequest.setBudgetRequired(selectedRequest.getInterstateTransportCost());
          selectedRequest.setWaterAllowed(waterAllowedTransport);
          selectedRequest.setArtificialMethod("Interstate Transport");
          requiredBudgetjTextField.setText(String.valueOf(selectedRequest.getInterstateTransportCost()));
          selectedRequest.setStatus("Analysis Done");
          selectedRequest.setTransportFlag(1);
      }
      populateAnalysisTable();
    }//GEN-LAST:event_jButton1ActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup WaterReplenishmentStrategy;
    private javax.swing.JTable analystjTable;
    private javax.swing.JTextField artificialTechniquejTextField;
    private javax.swing.JButton assignjButton;
    private javax.swing.JButton checkTransportation;
    private javax.swing.JTextField costOfArtificialMethodjTextField;
    private javax.swing.JTextField currentWaterLeveljTextField;
    private javax.swing.JTextField interstateCostjTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton processJButton;
    private javax.swing.JTextField requiredBudgetjTextField;
    private javax.swing.JTextField requiredWaterLeveljTextField;
    private javax.swing.JTextField soilTypejTextField;
    // End of variables declaration//GEN-END:variables

    private void populateJTextFields() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    int selectedRow = analystjTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the above table", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        AnalysisWorkRequest request = (AnalysisWorkRequest) analystjTable.getValueAt(selectedRow, 0);

        if(request.getAnalystReceiver().getUsername().equals(account.getUsername())){
        requiredWaterLeveljTextField.setText(String.valueOf(selectedRequest.getWaterRequired()));
        currentWaterLeveljTextField.setText(String.valueOf(selectedRequest.getWaterLevel()));
        soilTypejTextField.setText(selectedRequest.getSoilType());
        currentWaterLeveljTextField.setText(String.valueOf(request.getWaterLevel()));
        artificialMethod=generateArtificialMethod(selectedRequest.getWaterUsage());
        artificialTechniquejTextField.setText(artificialMethod);
        double methodBudget=generateMethodBudget(artificialMethod,selectedRequest.getSoilType());
        double costOfArtificialMethod=calculateCompleteBudget(methodBudget, selectedRequest.getWaterUsage());
        waterAllowedArtificial = costOfArtificialMethod/methodBudget;
        costOfArtificialMethodjTextField.setText(String.valueOf(costOfArtificialMethod));
        selectedRequest.setArtificialMethod(artificialMethod);
        selectedRequest.setArtificalMethodCost(costOfArtificialMethod);
        selectedRequest.setStatus("Analysis In Progress");
        
        populateAnalysisTable();
        }
        else {
            JOptionPane.showMessageDialog(null, "The Request has not been assigned to you", "warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void populateJTextFieldsForInterStateTransportation(AnalysisWorkRequest request) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        requiredWaterLeveljTextField.setText(String.valueOf(request.getWaterRequired()));
        currentWaterLeveljTextField.setText(String.valueOf(request.getWaterLevel()));
        soilTypejTextField.setText(request.getSoilType());
        
        String artificialMethod=generateArtificialMethod(request.getWaterUsage());
        artificialTechniquejTextField.setText(artificialMethod);
        double methodBudget=generateMethodBudget(artificialMethod,request.getSoilType());
        double costOfArtificialMethod=calculateCompleteBudget(methodBudget, request.getWaterRequired());
        costOfArtificialMethodjTextField.setText(String.valueOf(costOfArtificialMethod));
        
        request.setArtificialMethod(artificialMethod);
        request.setArtificalMethodCost(costOfArtificialMethod);
        request.setStatus("Analysis In Progress");
        interstateCostjTextField.setText(String.valueOf(request.getInterstateTransportCost()));
        
    
    }
}

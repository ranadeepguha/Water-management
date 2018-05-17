/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.StateBodyEmployee;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.AnalystOrganization;
import Business.Organization.CentralBodyOrganization;
import Business.Organization.ForecastOrganization;
import Business.Organization.Organization;
import Business.Organization.StateBodyOrganization;
import Business.Sensor.Sensor;
import Business.Sensor.SensorDirectory;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.CentralBodyWorkRequest;
import Business.WorkQueue.AnalysisWorkRequest;
import Business.WorkQueue.StateBodyWorkRequest;
import Business.WorkQueue.WorkRequest;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 *
 */
public class StateEmployeeWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form StateEmployeeWorkAreaJPanel
     */
    private JPanel userProcessContainer;
    private UserAccount account;
    private StateBodyOrganization stateBodyOrganization;
    private CentralBodyOrganization centralBodyOrganization;
    private Enterprise enterprise;
    private SensorDirectory sensorDirectory;
    private EcoSystem business;
    private static int count = 1;
    private int counter;
    private int i = 0, a = 0;
    private String location = null;
    private String soilType = null;
    private double waterUsage = 0;
    private double waterStorageCapacity = 0;
    private double triggerPercentage = 0;
    private double criticalPercentage = 0;
    private int sensorCounter = 0;
    private Sensor s1;
    private Sensor s2;
    private Sensor s3;
    private Sensor s4;
    private Sensor s5;

    public StateEmployeeWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem business) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.stateBodyOrganization = (StateBodyOrganization) organization;
        this.enterprise = enterprise;
        this.business = business;

        enterpriseJTextField.setText(enterprise.getName());
        counter = count;
        readFromExcel();
        generateSensorData(counter, null, 0, 0, 0);
        populatesensorTable();
        populateForecastTable();
        populateBudgetTable();
        count = 3;
        requestForecastJButton.setEnabled(false);
        closeSensorRequestJButton.setEnabled(false);
    }

    public void readFromExcel() {

        try {
            FileInputStream file = new FileInputStream(new File("sensorData.xlsx"));
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            //Iterate through each rows one by one
            Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.iterator();

            DataFormatter df = new DataFormatter();

            while (rowIterator.hasNext()) {
                sensorCounter++;
                org.apache.poi.ss.usermodel.Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                for (a = 0; a < 6; a++) {
                    i = (a % 6);

                    //System.out.print(row.getCell(i)+"\t");
                    switch (i) {
                        case 0:
                            location = df.formatCellValue(row.getCell(i));
//                            System.out.println("location= "+location);
                            break;
                        case 1:
                            waterUsage = Double.parseDouble(df.formatCellValue(row.getCell(i)));
//                            System.out.println("waterUsage= "+waterUsage);
                            break;
                        case 2:
                            waterStorageCapacity = Double.parseDouble(df.formatCellValue(row.getCell(i)));
//                            System.out.println("waterStorageCapacity= "+waterStorageCapacity);
                            break;
                        case 3:
                            triggerPercentage = Double.parseDouble(df.formatCellValue(row.getCell(i)));
//                            System.out.println("triggerPercentage= "+triggerPercentage);
                            break;
                        case 4:
                            criticalPercentage = Double.parseDouble(df.formatCellValue(row.getCell(i)));
//                            System.out.println("criticalPercentage= "+criticalPercentage);
                            break;
                        case 5:
                            soilType = df.formatCellValue(row.getCell(i));
//                            System.out.println("location= "+soilType);
                            break;
                    }
                }
                if (sensorCounter == 1) {
                    s1 = new Sensor(location, waterUsage, waterStorageCapacity, triggerPercentage, criticalPercentage, soilType, enterprise);
                }
                if (sensorCounter == 2) {
                    s2 = new Sensor(location, waterUsage, waterStorageCapacity, triggerPercentage, criticalPercentage, soilType, enterprise);
                }
                if (sensorCounter == 3) {
                    s3 = new Sensor(location, waterUsage, waterStorageCapacity, triggerPercentage, criticalPercentage, soilType, enterprise);
                }
                if (sensorCounter == 4) {
                    s4 = new Sensor(location, waterUsage, waterStorageCapacity, triggerPercentage, criticalPercentage, soilType, enterprise);
                }
                if (sensorCounter == 5) {
                    s5 = new Sensor(location, waterUsage, waterStorageCapacity, triggerPercentage, criticalPercentage, soilType, enterprise);
                }

            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateSensorData(int counter, String location, double waterAllowed, int transFlag, double distance) {
//        Sensor s1 = new Sensor(location,waterUsage,waterStorageCapacity,triggerPercentage,criticalPercentage,soilType,enterprise);
//        Sensor s2 = new Sensor(location,waterUsage,waterStorageCapacity,triggerPercentage,criticalPercentage,soilType,enterprise);
        if (counter == 1) {
            s1.start(0, 0, 0);
            s2.start(0, 0, 0);
            s3.start(0, 0, 0);
            s4.start(0, 0, 0);
            s5.start(0, 0, 0);
        }

        if (counter == 5) {
            switch (location) {
                case "Boston":
                    s1.setWaterLevel(waterAllowed);
                    s1.start(5, transFlag, distance);
                    break;
                case "Chicago":
                    s2.setWaterLevel(waterAllowed);
                    s2.start(5, transFlag, distance);
                    break;
                case "New York City":
                    s3.setWaterLevel(waterAllowed);
                    s3.start(5, transFlag, distance);
                    break;
                case "Washington DC":
                    s4.setWaterLevel(waterAllowed);
                    s4.start(5, transFlag, distance);
                    break;
                case "San Fransisco":
                    s5.setWaterLevel(waterAllowed);
                    s5.start(5, transFlag, distance);
                    break;

            }
        }
    }

    public void populatesensorTable() {
        DefaultTableModel model = (DefaultTableModel) sensorRequestJTable.getModel();
        model.setRowCount(0);
        for (WorkRequest request : stateBodyOrganization.getWorkQueue().getWorkRequestList()) {
            Object[] row = new Object[5];
            row[0] = request;
            row[1] = request.getWaterStatus();
            row[2] = request.getWaterLevel();
            row[3] = request.getStateSender() == null ? null : request.getStateSender().getEmployee().getName();
            String status = request.getStatus();
            row[4] = status == null ? "waiting" : status;
            model.addRow(row);
        }
    }

    public void populateBudgetTable() {
        DefaultTableModel model = (DefaultTableModel) budgetRequestJTable.getModel();
        model.setRowCount(0);
        for (WorkRequest request : account.getWorkQueue().getWorkRequestList()) {
            Object[] row = new Object[6];
            row[0] = request;
            row[1] = request.getWaterStatus();
            row[2] = request.getCentralReceiver();
            row[3] = request.getBudgetStatus();
            row[4] = request.getBudgetRequired();
            row[5] = ((CentralBodyWorkRequest) request).getBudgetRequested();
            model.addRow(row);
        }
    }

    public void populateForecastTable() {
        DefaultTableModel model = (DefaultTableModel) forecastRequestJTable.getModel();
        model.setRowCount(0);
        for (WorkRequest request : account.getWorkQueue().getForecastWorkRequests()) {
            Object[] row = new Object[9];
            row[0] = request;
            row[1] = request.getWaterLevel();
            row[2] = request.getSensorLocation();
            row[3] = ((AnalysisWorkRequest) request).getForecastReceiver();
            row[4] = ((AnalysisWorkRequest) request).getAnalystReceiver();
            int forecast = ((AnalysisWorkRequest) request).getForecast();
            row[5] = forecast == 0 ? "Not Checked" : forecast;
            row[6] = ((AnalysisWorkRequest) request).getArtificialMethod();
            row[7] = ((AnalysisWorkRequest) request).getBudgetRequired();
            row[8] = request.getStatus();
            model.addRow(row);
        }
    }

    public void generateAnalysisData() {
        int selectedRow = forecastRequestJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the above table", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        AnalysisWorkRequest analystRequest = (AnalysisWorkRequest) forecastRequestJTable.getValueAt(selectedRow, 0);
        generateAnalyticWorkRequest(analystRequest);
    }

    public void generateForecastWorkRequest(int requestID, String location, double waterLevel, String soilType, double waterUsage, double waterStorageCapacity, double waterRequired) {
        AnalysisWorkRequest forecastWorkRequest = new AnalysisWorkRequest();
        forecastWorkRequest.setRequestID(requestID);
        forecastWorkRequest.setWaterLevel(waterLevel);
        forecastWorkRequest.setSensorLocation(location);
        forecastWorkRequest.setSoilType(soilType);
        forecastWorkRequest.setWaterUsage(waterUsage);
        forecastWorkRequest.setWaterStorageCapacity(waterStorageCapacity);
        forecastWorkRequest.setStateSender(account);
        forecastWorkRequest.setStatus("Sent for Forecast");
        forecastWorkRequest.setWaterRequired(waterRequired);
        System.out.println("Water Required while generating forecast request" + forecastWorkRequest.getWaterRequired());

        Organization org = null;

        for (Network network : business.getNetworkList()) {
            for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                    if (organization instanceof ForecastOrganization) {
                        org = organization;
                        break;
                    }
                }
                if (org != null) {
                    org.getWorkQueue().getForecastWorkRequests().add(forecastWorkRequest);
                    account.getWorkQueue().getForecastWorkRequests().add(forecastWorkRequest);
                }
            }
        }
    }

    public void generateAnalyticWorkRequest(AnalysisWorkRequest sensorRequest) {

        //'IF' loop to check whether forecast has been generated before sending request to analyst team.
        if (sensorRequest.getStatus().equalsIgnoreCase("Forecast Generated")) {
            AnalysisWorkRequest analysisRequest = sensorRequest;
            analysisRequest.setStatus("Sent to Analyst Team");

            Organization org = null;
            for (Network network : business.getNetworkList()) {
                for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                    for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                        if (organization instanceof AnalystOrganization) {
                            org = organization;
                            break;
                        }
                    }
                    if (org != null) {
                        org.getWorkQueue().getForecastWorkRequests().add(analysisRequest);
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "The Request is being forawrded to Analyst Team", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Forecast has not been generated yet. Please wait", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void generateBudgetWorkRequest(String location, String waterStatus, double budget, int transport, double waterRequired) {
        CentralBodyWorkRequest centralWorkRequest = new CentralBodyWorkRequest();

        centralWorkRequest.setBudgetRequired(budget);
        centralWorkRequest.setCentralSender(account);
        centralWorkRequest.setWaterStatus(waterStatus);
        centralWorkRequest.setSensorLocation(location);
        centralWorkRequest.setTransportFlag(transport);
        centralWorkRequest.setWaterRequired(waterRequired);
        centralWorkRequest.setBudgetStatus("Forwarded to Central");

        Organization org = null;
        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {

            if (organization instanceof CentralBodyOrganization) {

                org = organization;
                break;
            }
        }
        if (org != null) {

            org.getWorkQueue().getWorkRequestList().add(centralWorkRequest);
            account.getWorkQueue().getWorkRequestList().add(centralWorkRequest);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        assignToMeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        enterpriseJTextField = new javax.swing.JTextField();
        refreshJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        sensorRequestJTable = new javax.swing.JTable();
        escalateJButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        releaseWaterJButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        forecastRequestJTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        budgetRequestJTable = new javax.swing.JTable();
        requestForecastJButton = new javax.swing.JButton();
        closeSensorRequestJButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        AlternatewaterjButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        assignToMeButton.setBackground(new java.awt.Color(0, 102, 153));
        assignToMeButton.setForeground(new java.awt.Color(255, 255, 255));
        assignToMeButton.setText("Assign to me");
        assignToMeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignToMeButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Enterprise:");

        enterpriseJTextField.setEditable(false);

        refreshJButton.setText("Refresh");
        refreshJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshJButtonActionPerformed(evt);
            }
        });

        sensorRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Location", "Water Status", "Water Level", "Receiver", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(sensorRequestJTable);

        escalateJButton.setBackground(new java.awt.Color(0, 102, 153));
        escalateJButton.setForeground(new java.awt.Color(255, 255, 255));
        escalateJButton.setText("Forward to Central Body");
        escalateJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escalateJButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Welcome to State Body Water Management Department.");

        releaseWaterJButton.setBackground(new java.awt.Color(0, 102, 153));
        releaseWaterJButton.setForeground(new java.awt.Color(255, 255, 255));
        releaseWaterJButton.setText("Release Water");
        releaseWaterJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                releaseWaterJButtonActionPerformed(evt);
            }
        });

        forecastRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "WaterStatus", "WaterLevel", "Location", "Forecast Receiver", "Analyst Receiver", "Predicted Forecast", "Alternate Source", "Budget Required", "Request Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(forecastRequestJTable);
        if (forecastRequestJTable.getColumnModel().getColumnCount() > 0) {
            forecastRequestJTable.getColumnModel().getColumn(8).setResizable(false);
        }

        budgetRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Location", "Water Status", "Receiver", "Status", "FundsRequested", "FundsApproved"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(budgetRequestJTable);

        requestForecastJButton.setBackground(new java.awt.Color(0, 102, 153));
        requestForecastJButton.setForeground(new java.awt.Color(255, 255, 255));
        requestForecastJButton.setText("Request Forecast");
        requestForecastJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestForecastJButtonActionPerformed(evt);
            }
        });

        closeSensorRequestJButton.setBackground(new java.awt.Color(0, 102, 153));
        closeSensorRequestJButton.setForeground(new java.awt.Color(255, 255, 255));
        closeSensorRequestJButton.setText("Close Request");
        closeSensorRequestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeSensorRequestJButtonActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Central Body Request Status :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 102));
        jLabel4.setText("Forecast Check :");

        AlternatewaterjButton.setBackground(new java.awt.Color(0, 102, 153));
        AlternatewaterjButton.setForeground(new java.awt.Color(255, 255, 255));
        AlternatewaterjButton.setText("Request Alternate Water Strategy");
        AlternatewaterjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlternatewaterjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(releaseWaterJButton)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(enterpriseJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(refreshJButton))))
                        .addContainerGap(60, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AlternatewaterjButton)
                                .addGap(84, 84, 84)
                                .addComponent(escalateJButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(assignToMeButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(requestForecastJButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(closeSensorRequestJButton)))
                        .addGap(0, 498, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 956, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(enterpriseJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshJButton))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(assignToMeButton)
                    .addComponent(requestForecastJButton)
                    .addComponent(closeSensorRequestJButton))
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AlternatewaterjButton)
                    .addComponent(escalateJButton))
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(releaseWaterJButton)
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void assignToMeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignToMeButtonActionPerformed
        int selectedRow = sensorRequestJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the above table", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        StateBodyWorkRequest request = (StateBodyWorkRequest) sensorRequestJTable.getValueAt(selectedRow, 0);
        if (request.getStatus() == null) {
            request.setStateSender(account);
            request.setStatus("Processing");
            populatesensorTable();
            requestForecastJButton.setEnabled(true);
            closeSensorRequestJButton.setEnabled(true);
        } else if (request.getStatus().equalsIgnoreCase("closed")) {
            JOptionPane.showMessageDialog(null, "This Request is already closed", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }//GEN-LAST:event_assignToMeButtonActionPerformed

    private void escalateJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escalateJButtonActionPerformed
        int selectedRow = forecastRequestJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the above table", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        AnalysisWorkRequest request = (AnalysisWorkRequest) forecastRequestJTable.getValueAt(selectedRow, 0);
        try {
            //'IF' loop to check whether request has been assigned to the right employee.
            if (((AnalysisWorkRequest) request).getStateSender().getUsername() == null || !((AnalysisWorkRequest) request).getStateSender().getUsername().equals(account.getUsername())) {
                JOptionPane.showMessageDialog(null, "The request is not assigned to your name!!\nPlease assign it first", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            } //'IF' loop to check whether Analysis is done before sending to central body.
            else if (!request.getStatus().equalsIgnoreCase("Analysis Done") || request.getStatus().equalsIgnoreCase("Forwarded to Central")) {
                JOptionPane.showMessageDialog(null, "Analysis has not been completed or Request has been sent to Central. Please Check Again", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            } 
            else {
                String location = request.getSensorLocation();
                String waterStatus = request.getWaterStatus();
                double budget = request.getBudgetRequired();
                int transport = request.getTransportFlag();
                double waterRequired = request.getWaterRequired();
                request.setStatus("Forwarded to Central");
                generateBudgetWorkRequest(location, waterStatus, budget, transport, waterRequired);
                populateBudgetTable();
                JOptionPane.showMessageDialog(null, "Request forwarded to Central Body", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "The request is not assigned to your name!!\nPlease assign it first", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        if (!((AnalysisWorkRequest) request).getStateSender().getUsername().equals(account.getUsername())) {
            JOptionPane.showMessageDialog(null, "This request is not assigned to you");
        }

    }//GEN-LAST:event_escalateJButtonActionPerformed

    private void requestForecastJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestForecastJButtonActionPerformed
        int selectedRow = sensorRequestJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the above table", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        StateBodyWorkRequest request = (StateBodyWorkRequest) sensorRequestJTable.getValueAt(selectedRow, 0);
        try {
            //'IF' loop to check if code is assigned to correct state employee
            if (((StateBodyWorkRequest) request).getStateSender().getUsername() == null || !((StateBodyWorkRequest) request).getStateSender().getUsername().equals(account.getUsername())) {
                JOptionPane.showMessageDialog(null, "The request is not assigned to your name!!\nPlease assign it first", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (((StateBodyWorkRequest) request).getStateSender().getUsername().equals(account.getUsername()) && !(request.getStatus().equalsIgnoreCase("Processed"))) {
                int requestID = request.getRequestID();
                String location = request.getSensorLocation();
                double waterLevel = request.getWaterLevel();
                request.setStatus("Processed");
                generateForecastWorkRequest(requestID, location, waterLevel, request.getSoilType(), request.getWaterUsage(), request.getWaterStorageCapacity(), request.getWaterRequired());
                populatesensorTable();
                populateForecastTable();
                closeSensorRequestJButton.setEnabled(true);
                JOptionPane.showMessageDialog(null, "The Request is being forawrded to Forecast Team", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "The request is not assigned to your name!!\nPlease assign it first", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_requestForecastJButtonActionPerformed

    private void closeSensorRequestJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeSensorRequestJButtonActionPerformed
        int selectedRow = sensorRequestJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the above table", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        StateBodyWorkRequest sensorRequest = (StateBodyWorkRequest) sensorRequestJTable.getValueAt(selectedRow, 0);
        try {
            if (((StateBodyWorkRequest) sensorRequest).getStateSender().getUsername() == null || !((StateBodyWorkRequest) sensorRequest).getStateSender().getUsername().equals(account.getUsername())) {
                JOptionPane.showMessageDialog(null, "The request is not assigned to your name!!\nPlease assign it first", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            } else if (((StateBodyWorkRequest) sensorRequest).getStateSender().getUsername().equals(account.getUsername())) {
                sensorRequest.setStateStatus("Closed");
                populatesensorTable();
                assignToMeButton.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Request has been closed", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "The request is not assigned to your name!!\nPlease assign it first", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_closeSensorRequestJButtonActionPerformed

    private void AlternatewaterjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlternatewaterjButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = forecastRequestJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the above table", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        AnalysisWorkRequest request = (AnalysisWorkRequest) forecastRequestJTable.getValueAt(selectedRow, 0);

        if (request.getForecast() < 80 && request.getStatus().equalsIgnoreCase("Forecast Generated")) {
            generateAnalysisData();
            populateForecastTable();
        } else if (!(request.getStatus().equalsIgnoreCase("Forecast Generated"))) {
            JOptionPane.showMessageDialog(null, "The Forecast has not been done yet", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "The Predicted rain is more than the Required value.", "Warning", JOptionPane.WARNING_MESSAGE);
            request.setStatus("Closed");
            return;
        }


    }//GEN-LAST:event_AlternatewaterjButtonActionPerformed

    private void refreshJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshJButtonActionPerformed
        // TODO add your handling code here:
        populatesensorTable();
        populateForecastTable();
        populateBudgetTable();
    }//GEN-LAST:event_refreshJButtonActionPerformed

    private void releaseWaterJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_releaseWaterJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = budgetRequestJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the above table", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        CentralBodyWorkRequest request = (CentralBodyWorkRequest) budgetRequestJTable.getValueAt(selectedRow, 0);
//        readFromExcel();

        System.out.println("Water Allowed of the selected Request" + request.getWaterAllowed());
        if (request.getBudgetStatus().equalsIgnoreCase("approved")) {
            generateSensorData(5, request.getSensorLocation(), request.getWaterAllowed(), request.getTransportFlag(), request.getInterStateDistance());
            JOptionPane.showMessageDialog(null, "The approved water is released", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "The Request is not Approved.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

    }//GEN-LAST:event_releaseWaterJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AlternatewaterjButton;
    private javax.swing.JButton assignToMeButton;
    private javax.swing.JTable budgetRequestJTable;
    private javax.swing.JButton closeSensorRequestJButton;
    private javax.swing.JTextField enterpriseJTextField;
    private javax.swing.JButton escalateJButton;
    private javax.swing.JTable forecastRequestJTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton refreshJButton;
    private javax.swing.JButton releaseWaterJButton;
    private javax.swing.JButton requestForecastJButton;
    private javax.swing.JTable sensorRequestJTable;
    // End of variables declaration//GEN-END:variables

}

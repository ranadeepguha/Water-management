/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Employee.EmployeeDirectory;
import Business.Role.Role;
import Business.UserAccount.UserAccountDirectory;
import Business.WorkQueue.WorkQueue;
import java.util.ArrayList;

/**
 *
 *
 */
public abstract class Organization {
    
    private String name;
    private int organizationID;
    private static int counter=1;
    private WorkQueue workQueue;
    private EmployeeDirectory employeeDirectory;
    private UserAccountDirectory userAccountDirectory;
    
    public enum WaterType{
        AdminOrganization("AdminOrganization"),
        StateBodyOrganization("StateBodyOrganization"),
        CentralBodyOrganization("CentralBodyOrganization");
                
        private String value;

        private WaterType(String value) {
            this.value=value;
        }

        public String getValue() {
            return value;
        }
    }
    
    public enum BankType{
        BankOrganization("BankOrganization");
        
        private String value;

        private BankType(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }

    }
    
    public enum AnalystType{
        AnalystOrganization("AnalystOrganization"),
        ForecastOrganization("ForecastOrganization");
        
        private String value;

        private AnalystType(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }

    }
    public enum SensorType{
        SensorOrganization("SensorOrganization");
        
        private String value;

        private SensorType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        
    }

    public Organization(String name) {
        organizationID=counter;
        counter++;
        this.name=name;
        workQueue=new WorkQueue();
        employeeDirectory=new EmployeeDirectory();
        userAccountDirectory=new UserAccountDirectory();
    }

    public String getName() {
        return name;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeeDirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }

    public abstract ArrayList<Role> getSupportedRole();
    
    
    @Override
    public String toString() {
        return name;
    }
    
}

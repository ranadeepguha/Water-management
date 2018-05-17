/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.UserAccount.UserAccount;
import java.util.Date;

/**
 *
 *
 */
public class WorkRequest {

    private int requestID;
    private String sensorLocation;
    private String waterStatus;
    private double waterRequired;
    private double waterUsage;
    private double triggerPercecntage;
    private double waterStorageCapacity;
    private double budgetRequired;
    private String soilType;
    private double artificalMethodCost=0;
    private double interstateTransportCost=0;
    private double waterAllowed;
    private double interStateDistance;
   

    
    private double waterLevel;
    private UserAccount stateSender;
    private UserAccount centralReceiver;
    private UserAccount centralSender;
    private UserAccount bankReceiver;
    private String status;
    private String budgetStatus;
    private Date requestDate;
    private Date resolveDate;
    public static int count = 1;
     
    public WorkRequest() {
        count = requestID;
        count++;
        requestDate = new Date();
    }
    
    public double getWaterRequired() {
        return waterRequired;
    }

    public void setWaterRequired(double waterRequired) {
        this.waterRequired = waterRequired;
    }
    
    public String getBudgetStatus() {
        return budgetStatus;
    }

    public void setBudgetStatus(String budgetStatus) {
        this.budgetStatus = budgetStatus;
    }

    public int getRequestID() {
        return requestID;
    }

    public double getWaterUsage() {
        return waterUsage;
    }

    public void setWaterUsage(double waterUsage) {
        this.waterUsage = waterUsage;
    }

    public double getTriggerPercecntage() {
        return triggerPercecntage;
    }

    public void setTriggerPercecntage(double triggerPercecntage) {
        this.triggerPercecntage = triggerPercecntage;
    }
public double getArtificalMethodCost() {
        return artificalMethodCost;
    }

    public void setArtificalMethodCost(double artificalMethodCost) {
        this.artificalMethodCost = artificalMethodCost;
    }

    public double getInterstateTransportCost() {
        return interstateTransportCost;
    }

    public void setInterstateTransportCost(double interstateTransportCost) {
        this.interstateTransportCost = interstateTransportCost;
    }
    
    public double getWaterStorageCapacity() {
        return waterStorageCapacity;
    }

    public void setWaterStorageCapacity(double waterStorageCapacity) {
        this.waterStorageCapacity = waterStorageCapacity;
    }

    public double getBudgetRequired() {
        return budgetRequired;
    }

    public void setBudgetRequired(double budgetRequired) {
        this.budgetRequired = budgetRequired;
    }
        
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public String getSensorLocation() {
        return sensorLocation;
    }

    public void setSensorLocation(String sensorLocation) {
        this.sensorLocation = sensorLocation;
    }

    public String getWaterStatus() {
        return waterStatus;
    }

    public void setWaterStatus(String waterStatus) {
        this.waterStatus = waterStatus;
    }

    public UserAccount getStateSender() {
        return stateSender;
    }

    public void setStateSender(UserAccount stateSender) {
        this.stateSender = stateSender;
    }

    public UserAccount getCentralReceiver() {
        return centralReceiver;
    }

    public void setCentralReceiver(UserAccount centralReceiver) {
        this.centralReceiver = centralReceiver;
    }

    public UserAccount getCentralSender() {
        return centralSender;
    }

    public void setCentralSender(UserAccount centralSender) {
        this.centralSender = centralSender;
    }

    public UserAccount getBankReceiver() {
        return bankReceiver;
    }

    public void setBankReceiver(UserAccount bankReceiver) {
        this.bankReceiver = bankReceiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(Date resolveDate) {
        this.resolveDate = resolveDate;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public double getWaterAllowed() {
        return waterAllowed;
    }

    public void setWaterAllowed(double waterAllowed) {
        this.waterAllowed = waterAllowed;
    }

    public double getInterStateDistance() {
        return interStateDistance;
    }

    public void setInterStateDistance(double interStateDistance) {
        this.interStateDistance = interStateDistance;
    }
    
    
    @Override
    public String toString() {

        return this.sensorLocation;
    }

}

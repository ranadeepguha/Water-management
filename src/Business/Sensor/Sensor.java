/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Sensor;

import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.StateBodyOrganization;
import Business.WorkQueue.StateBodyWorkRequest;
import static Business.WriteIntoNotepad.WriteIntoNotepad.writeIntoNotepad;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 *
 */
public class Sensor implements Runnable {

    private Thread T;
    private int sensorID;
    private String Location;
    private int count;
    private String waterStatus;
    private double waterLevel;
    private double waterRequired;
    private double waterUsage;
    private double waterStorageCapacity;
    private double percentFilled;
    private double triggerPercentage;
    private double criticalPercentage;
    private String soilType;
    private int flag = 0;
    private int transportFlag = 0;
    private double distance = 0;
    private String text;

    private Enterprise enterprise;

    public Sensor(String Location, double waterUsage, double waterStorageCapacity, double triggerPercentage, double criticalPercentage, String soilType, Enterprise enterprise) {
        this.Location = Location;
        this.waterStorageCapacity = waterStorageCapacity;
        this.waterUsage = waterUsage;
        this.triggerPercentage = triggerPercentage;
        this.criticalPercentage = criticalPercentage;
        this.enterprise = enterprise;
        waterLevel = waterStorageCapacity - waterUsage;
        this.soilType = soilType;
        waterRequired=(waterStorageCapacity*(triggerPercentage/100))*(160/100);
   
    }

    public Sensor() {
    }

    public int getSensorID() {
        return sensorID;
    }

    public String getLocation() {
        return Location;
    }

    public double getWaterStorageCapacity() {
        return waterStorageCapacity;
    }

    public void setWaterStorageCapacity(double waterStorageCapacity) {
        this.waterStorageCapacity = waterStorageCapacity;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getWaterStatus() {
        return waterStatus;
    }

    public void setWaterStatus(String waterStatus) {
        this.waterStatus = waterStatus;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public double getWaterRequired() {
        return waterRequired;
    }

    public void setWaterRequired(double waterRequired) {
        this.waterRequired = waterRequired;
    }

    @Override
    public void run() {
        try {
            if (transportFlag == 1) {
                
                for (int j = 5; j > 0; j--) {
                    if (distance > 1800) {
                        System.out.println("\nThe Location :" + getLocation() + " is waiting for the insterState Water");
                        Thread.sleep(6000);
                    }
                    if (distance < 1800 && distance > 1500) {
                        System.out.println("\nThe Location :" + getLocation() + " is waiting for the insterState Water");
                        Thread.sleep(5500);
                    }
                    if (distance < 1500 && distance > 1000) {
                        System.out.println("\nThe Location :" + getLocation() + " is waiting for the insterState Water");
                        Thread.sleep(4500);
                    }
                    if (distance < 1000 && distance > 500) {
                        System.out.println("\nThe Location :" + getLocation() + " is waiting for the insterState Water");
                        Thread.sleep(4000);
                    }
                    if (distance < 500) {
                        System.out.println("\nThe Location :" + getLocation() + " is waiting for the insterState Water");
                        Thread.sleep(3500);
                    }
                }
                for (int i = 100; i > 0; i--) {
                    waterLevel -= waterUsage;
                    percentFilled = (waterLevel / waterStorageCapacity) * 100;

                    if (percentFilled > triggerPercentage) {
                        waterStatus = "Normal";
                    } else if (percentFilled < triggerPercentage) {
                        waterStatus = "Low";
                    }


                        if (waterLevel < (waterUsage*4) && flag!=1) {
                        flag = 1;
                        System.out.println("\nRequest Generated as the water level reached the threshold percentage of : " + getLocation());
                        text=("\nRequest Generated as the water level reached the threshold percentage of :  " + getLocation());
                        writeIntoNotepad(text);
                        StateBodyWorkRequest stateBodyWorkRequest = new StateBodyWorkRequest();
                        stateBodyWorkRequest.setWaterStatus(waterStatus);
                        stateBodyWorkRequest.setWaterLevel(waterLevel);
                        stateBodyWorkRequest.setSensorLocation(getLocation());
                        stateBodyWorkRequest.setWaterRequired(waterRequired);
                        stateBodyWorkRequest.setWaterUsage(waterUsage);
                        stateBodyWorkRequest.setTriggerPercecntage(triggerPercentage);
                        stateBodyWorkRequest.setWaterStorageCapacity(waterStorageCapacity);
                        stateBodyWorkRequest.setSoilType(soilType);
                        stateBodyWorkRequest.setStateSender(null);
                      
                        Organization org = null;

                        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                            if (organization instanceof StateBodyOrganization) {
                                org = organization;
                                break;
                            }
                        }
                        if (org != null) {
                            org.getWorkQueue().getWorkRequestList().add(stateBodyWorkRequest);
                        }
                    }

                    if (waterLevel <= (waterUsage*2) && flag == 1) {
                        System.out.println("\nThe Sensor will stop sending data since the waterLevel reached the critical threshold of : " + getLocation());
                        text = ("\nThe Sensor will stop sending data since the waterLevel reached the critical threshold of : " + getLocation());
                        writeIntoNotepad(text);
                        flag = 2;
                        Thread.currentThread().stop();
                    }
                    text = ("\nThe Location is : " + getLocation() + " ->  [Water Level : " + waterLevel +"]");
                    System.out.println("\nThe Location is : " + getLocation() + " ->  [Water Level : " + waterLevel + "]");
                    writeIntoNotepad(text);
                    Thread.sleep(3000);
                }
            } else {
                for (int i = 100; i > 0; i--) {
                    waterLevel -= waterUsage;
                    percentFilled = (waterLevel / waterStorageCapacity) * 100;

                    if (percentFilled > triggerPercentage) {
                        waterStatus = "Normal";
                    } else if (percentFilled < triggerPercentage) {
                        waterStatus = "Low";
                    }

                    if (waterLevel < (waterUsage*4) && flag != 1) {
                        flag = 1;
                        System.out.println("\nRequest Generated as the water level reached the threshold percentage of : " + getLocation());
                        text = ("\nRequest Generated as the water level reached the threshold percentage of : " + getLocation());
                        writeIntoNotepad(text);
                        StateBodyWorkRequest stateBodyWorkRequest = new StateBodyWorkRequest();
                        stateBodyWorkRequest.setWaterStatus(waterStatus);
                        stateBodyWorkRequest.setWaterLevel(waterLevel);
                        stateBodyWorkRequest.setSensorLocation(getLocation());
                        stateBodyWorkRequest.setWaterRequired(waterRequired);
                        stateBodyWorkRequest.setWaterUsage(waterUsage);
                        stateBodyWorkRequest.setTriggerPercecntage(triggerPercentage);
                        stateBodyWorkRequest.setWaterStorageCapacity(waterStorageCapacity);
                        stateBodyWorkRequest.setSoilType(soilType);
                        stateBodyWorkRequest.setStateSender(null);

                        Organization org = null;

                        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                            if (organization instanceof StateBodyOrganization) {
                                org = organization;
                                break;
                            }
                        }
                        if (org != null) {
                            org.getWorkQueue().getWorkRequestList().add(stateBodyWorkRequest);
                        }
                    }

                    if (waterLevel <= (waterUsage*2) && flag == 1) {
                        System.out.println("\nThe Sensor will stop sending data since the waterLevel reached the critical threshold of : " + getLocation());
                        text=("\nThe Sensor will stop sending data since the waterLevel reached the critical threshold of : " + getLocation());
                        writeIntoNotepad(text);
                        flag = 2;
                        Thread.currentThread().stop();
                    }
                    System.out.println("\nThe Location is : " + getLocation() + " ->  [Water Level : " + waterLevel + "]");
                    text=("\nThe Location is : " + getLocation() + " ->  [Water Level : " + waterLevel +"]");
                    writeIntoNotepad(text);
                    Thread.sleep(3000);
                }
            }
        } //for catching interrupted exception
        catch (InterruptedException e) {

        }

    }

    public void start(int counter, int TransFlag, double interdistance) {

        if (T == null) {
            T = new Thread(this, Location);
            T.start();
        }
        if (counter == 5) {
            T = new Thread(this, Location);
            transportFlag = TransFlag;
            distance = interdistance;
            T.start();
        }
    }

}

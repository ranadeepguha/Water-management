/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

/**
 *
 *
 */
public class CentralBodyWorkRequest extends WorkRequest {
    
    private String centralMessage;
    private double budgetRequested;
    private double budgetApproved;
    private int transportFlag;

    public String getCentralMessage() {
        return centralMessage;
    }

    public void setCentralMessage(String centralMessage) {
        this.centralMessage = centralMessage;
    }

    public int getTransportFlag() {
        return transportFlag;
    }

    public void setTransportFlag(int transportFlag) {
        this.transportFlag = transportFlag;
    }

    public double getBudgetRequested() {
        return budgetRequested;
    }

    public void setBudgetRequested(double budgetRequested) {
        this.budgetRequested = budgetRequested;
    }

    public double getBudgetApproved() {
        return budgetApproved;
    }

    public void setBudgetApproved(double budgetApproved) {
        this.budgetApproved = budgetApproved;
    }

         
}

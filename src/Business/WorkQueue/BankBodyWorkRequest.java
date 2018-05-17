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
public class BankBodyWorkRequest extends WorkRequest {
    
    private String bankStatus;
    private double budgetRequested;

    public String getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(String bankStatus) {
        this.bankStatus = bankStatus;
    }

    public double getBudgetRequested() {
        return budgetRequested;
    }

    public void setBudgetRequested(double budgetRequested) {
        this.budgetRequested = budgetRequested;
    }
    
    
}

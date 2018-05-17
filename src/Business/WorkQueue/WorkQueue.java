/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import java.util.ArrayList;

/**
 *
 *
 */
public class WorkQueue {

    private ArrayList<WorkRequest> workRequestList;
    private ArrayList<WorkRequest> forecastWorkRequests;

    public WorkQueue() {
        workRequestList = new ArrayList();
        forecastWorkRequests = new ArrayList();
    }

    public ArrayList<WorkRequest> getForecastWorkRequests() {
        return forecastWorkRequests;
    }

    public void setForecastWorkRequests(ArrayList<WorkRequest> forecastWorkRequests) {
        this.forecastWorkRequests = forecastWorkRequests;
    }

    
    public ArrayList<WorkRequest> getWorkRequestList() {
        return workRequestList;
    }

    public void setWorkRequestList(ArrayList<WorkRequest> workRequestList) {
        this.workRequestList = workRequestList;
    }

}

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
public class StateBodyWorkRequest extends WorkRequest{
    
    private String stateStatus;
    private String forecastData;

    public String getStateStatus() {
        return stateStatus;
    }

    public void setStateStatus(String stateStatus) {
        this.stateStatus = stateStatus;
    }

    public String getForecastData() {
        return forecastData;
    }

    public void setForecastData(String forecastData) {
        this.forecastData = forecastData;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.UserAccount.UserAccount;

/**
 *
 *
 */
public class AnalysisWorkRequest extends WorkRequest {
    
    private int forecast;
    private String forecastStatus;
    private UserAccount stateSender;
    private UserAccount forecastReceiver;
    private UserAccount AnalystReceiver;
    private String artificialMethod;
    private int transportFlag;

    public int getForecast() {
        return forecast;
    }

    public void setForecast(int forecast) {
        this.forecast = forecast;
    }

    public int getTransportFlag() {
        return transportFlag;
    }

    public void setTransportFlag(int transportFlag) {
        this.transportFlag = transportFlag;
    }
    
    

    public String getForecastStatus() {
        return forecastStatus;
    }

    public void setForecastStatus(String forecastStatus) {
        this.forecastStatus = forecastStatus;
    }

    @Override
    public UserAccount getStateSender() {
        return stateSender;
    }

    @Override
    public void setStateSender(UserAccount stateSender) {
        this.stateSender = stateSender;
    }

    public UserAccount getForecastReceiver() {
        return forecastReceiver;
    }

    public void setForecastReceiver(UserAccount forecastReceiver) {
        this.forecastReceiver = forecastReceiver;
    }

    public UserAccount getAnalystReceiver() {
        return AnalystReceiver;
    }

    public String getArtificialMethod() {
        return artificialMethod;
    }

    public void setArtificialMethod(String artificialMethod) {
        this.artificialMethod = artificialMethod;
    }

    public void setAnalystReceiver(UserAccount AnalystReceiver) {
        this.AnalystReceiver = AnalystReceiver;
    }

    
    
}

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
public class ForecastWorkRequest extends WorkRequest {
    
    private String forecast;
    private String forecastStatus;
    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }

    public String getForecastStatus() {
        return forecastStatus;
    }

    public void setForecastStatus(String forecastStatus) {
        this.forecastStatus = forecastStatus;
    }
    
   
    
}

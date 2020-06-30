package com.example.finedust;

public class Air {

    private String DataTime = "";
    private String pm10;
    private String pm25;
    private String pm10Grade;
    private String pm25Grede;


    public void setDataTime(String dataTime)  {
        this.DataTime = dataTime;
    }
    public String getDataTime() {
        return this.DataTime;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm10Grade() {
        return pm10Grade;
    }

    public void setPm10Grade(String pm10Grade) {
        this.pm10Grade = pm10Grade;
    }

    public String getPm25Grede() {
        return pm25Grede;
    }

    public void setPm25Grede(String pm25Grede) {
        this.pm25Grede = pm25Grede;
    }
}

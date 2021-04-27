package satt.model;

import lombok.Data;

public class Metadata {

    private String userName;
    private String os;
    private Resolution resolution;

    public Metadata(String userName, String os, Resolution resolution) {
        this.userName = userName;
        this.os = os;
        this.resolution = resolution;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

}

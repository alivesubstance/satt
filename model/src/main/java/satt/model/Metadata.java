package satt.model;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Metadata{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", os='").append(os).append('\'');
        sb.append(", resolution=").append(resolution);
        sb.append('}');
        return sb.toString();
    }
}

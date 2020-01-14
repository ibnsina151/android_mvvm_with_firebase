package ai.retail.nimontron.firebase.datamodels;

public class UpdateBillboardModel {

    private String filePath, phoneNumber;

    private double lat, lng;

    private double imgWidth, imgHeight;

    private long timeStamp;

    private double gyX;

    private double gyY;

    private double gyZ;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(double imgWidth) {
        this.imgWidth = imgWidth;
    }

    public double getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(double imgHeight) {
        this.imgHeight = imgHeight;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getGyX() {
        return gyX;
    }

    public void setGyX(double gyX) {
        this.gyX = gyX;
    }

    public double getGyY() {
        return gyY;
    }

    public void setGyY(double gyY) {
        this.gyY = gyY;
    }

    public double getGyZ() {
        return gyZ;
    }

    public void setGyZ(double gyZ) {
        this.gyZ = gyZ;
    }



}

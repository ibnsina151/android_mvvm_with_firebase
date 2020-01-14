package ai.retail.nimontron.dbhelper.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import ai.retail.nimontron.dbhelper.DbConstants;

@Entity(tableName = DbConstants.IMAGE_MAPPING_TABLE)
public class ImageMappingEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String filePath, phoneNumber;

    private double lat, lng;

    private double imgWidth, imgHeight;

    private long timeStamp;

    private double gyX;

    private double gyY;

    private double gyZ;

    private boolean isUploaded;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }

    @NonNull
    @Override
    public String toString() {
        return "ImageMappingEntity{" +
                "id='" + id + '\'' +
                ", filePath='" + filePath + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", imgWidth=" + imgWidth + '\'' +
                ", imgHeight=" + imgHeight + '\'' +
                ", gyX=" + gyX + '\'' +
                ", gyY=" + gyY + '\'' +
                ", gyZ=" + gyZ + '\'' +
                ", isUploaded=" + isUploaded +
                '}';
    }
}

package ai.retail.nimontron.viewmodels.datamodels;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import ai.retail.nimontron.utils.BillboardLocationValidationStatus;

public class ImageMappingUploadModel implements Parcelable {


    private String filePath, phoneNumber;

    private double lat, lng;
    private BillboardLocationValidationStatus status;
    private long timeStamp;
    private Location location;
    private BillboardLocationValidationStatus mStatus;
    private double imgWidth, imgHeight;

    private double gyX;
    private double gyY;
    private double gyZ;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public BillboardLocationValidationStatus getmStatus() {
        return mStatus;
    }

    public void setmStatus(BillboardLocationValidationStatus mStatus) {
        this.mStatus = mStatus;
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

    public ImageMappingUploadModel(){

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

    public BillboardLocationValidationStatus getStatus() {
        return status;
    }

    public void setStatus(BillboardLocationValidationStatus status) {
        this.status = status;
    }

    protected ImageMappingUploadModel(Parcel in) {
        this.location = in.readParcelable(Location.class.getClassLoader());
        int tmpMStatus = in.readInt();
        this.mStatus = tmpMStatus == -1 ? null : BillboardLocationValidationStatus.values()[tmpMStatus];
        this.filePath = in.readString();
        this.lat = in.readDouble();
        this.lng = in.readDouble();
        this.imgWidth = in.readDouble();
        this.imgHeight = in.readDouble();
        this.gyX = in.readLong();
        this.gyY = in.readLong();
        this.gyZ = in.readLong();
        this.phoneNumber = in.readString();
        this.timeStamp = in.readLong();
    }

    public static final Creator<ImageMappingUploadModel> CREATOR = new Creator<ImageMappingUploadModel>() {
        @Override
        public ImageMappingUploadModel createFromParcel(Parcel in) {
            return new ImageMappingUploadModel(in);
        }

        @Override
        public ImageMappingUploadModel[] newArray(int size) {
            return new ImageMappingUploadModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.location,flags);
        dest.writeInt(this.mStatus == null ? -1 : this.mStatus.ordinal());
        dest.writeString(this.filePath);
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
        dest.writeDouble(this.imgWidth);
        dest.writeDouble(this.imgHeight);
        dest.writeDouble(this.gyX);
        dest.writeDouble(this.gyY);
        dest.writeDouble(this.gyZ);
        dest.writeString(this.phoneNumber);
        dest.writeLong(this.timeStamp);
    }
}

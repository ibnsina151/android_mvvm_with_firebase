package ai.retail.nimontron.utils;

import ai.retail.nimontron.dbhelper.entity.ImageMappingEntity;
import ai.retail.nimontron.firebase.datamodels.UpdateBillboardModel;
import ai.retail.nimontron.viewmodels.datamodels.ImageMappingUploadModel;

public class Conversion {

    public static ImageMappingEntity getMappingEntity(ImageMappingUploadModel imageMappingUploadModel) {
        ImageMappingEntity imageMappingEntity = new ImageMappingEntity();

        imageMappingEntity.setPhoneNumber(imageMappingUploadModel.getPhoneNumber());
        imageMappingEntity.setFilePath(imageMappingEntity.getFilePath());
        imageMappingEntity.setTimeStamp(imageMappingEntity.getTimeStamp());
        imageMappingEntity.setImgHeight(imageMappingEntity.getImgHeight());
        imageMappingEntity.setImgWidth(imageMappingEntity.getImgWidth());
        imageMappingEntity.setLat(imageMappingEntity.getLat());
        imageMappingEntity.setLng(imageMappingEntity.getLng());
        imageMappingEntity.setGyX(imageMappingEntity.getGyX());
        imageMappingEntity.setGyY(imageMappingEntity.getGyY());
        imageMappingEntity.setGyZ(imageMappingEntity.getGyZ());
        return imageMappingEntity;
    }


    public static UpdateBillboardModel getUpdateOfBillboard(String userPhoneNumber, ImageMappingEntity entity) {
        UpdateBillboardModel model = new UpdateBillboardModel();
        model.setTimeStamp(entity.getTimeStamp());
        model.setImgHeight(entity.getImgHeight());
        model.setImgWidth(entity.getImgWidth());
        model.setFilePath(entity.getFilePath());
        model.setLat(entity.getLat());
        model.setLng(entity.getLng());
        model.setGyX(entity.getGyX());
        model.setGyY(entity.getGyY());
        model.setGyZ(entity.getGyY());
        model.setPhoneNumber(userPhoneNumber);
        return model;
    }

}

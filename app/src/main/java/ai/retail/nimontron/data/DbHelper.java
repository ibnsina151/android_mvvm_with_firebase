package ai.retail.nimontron.data;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

import ai.retail.nimontron.dbhelper.entity.ImageMappingEntity;

import java.util.List;

public interface DbHelper {

    @WorkerThread
    void  saveBillBoardInfo(ImageMappingEntity imageMappingEntity);

    LiveData<List<ImageMappingEntity>> getBillBoardInfo(String number);

    void deleteAllBillBoardInfo();

//    @WorkerThread
//    void saveOutletEntity(OutletEntity outletEntity);
//
//    LiveData<List<OutletEntity>> getSavedOutletInfoList(String number);
//
//    LiveData<List<OutletEntity>> getSavedOutletInfoList();
//
//    LiveData<Integer> getNumberOfOutletsSaved();
//
//    void deleteOutlet(OutletEntity outletEntity);
//
//    void deleteAllOutlets();
//
//    void deleteOutlet(String phoneNumber);
//
//    void updateOutletInfo(OutletEntity outletEntity);
//
//    LiveData<List<String>> getUploadHistory();
//
//    LiveData<List<OutletEntity>> getDetailUploadHistory(String date);
//
//    LiveData<CompetitionEntity> getSavedCompetitionInfo(String phoneNumber, String currentDay);
//
//    void saveCompetitionInfo(CompetitionEntity competitionEntity);
}

package ai.retail.nimontron.data.impl;

import androidx.lifecycle.LiveData;

import ai.retail.nimontron.data.DbHelper;
import ai.retail.nimontron.dbhelper.database.ProjectDB;
import ai.retail.nimontron.dbhelper.entity.ImageMappingEntity;

import java.util.List;

import javax.inject.Inject;

public class DbHelperImpl implements DbHelper {


    private final ProjectDB mAppDatabase;

    @Inject
    public DbHelperImpl(ProjectDB appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public void saveBillBoardInfo(ImageMappingEntity imageMappingEntity) {
        mAppDatabase.getImageMappingDao().saveBillBoardInfo(imageMappingEntity);
    }

    @Override
    public LiveData<List<ImageMappingEntity>> getBillBoardInfo(String number) {
        return mAppDatabase.getImageMappingDao().getBillBoardInfo();
    }

    @Override
    public void deleteAllBillBoardInfo() {
        mAppDatabase.getImageMappingDao().deleteAllBillBoardInfo();
    }

}

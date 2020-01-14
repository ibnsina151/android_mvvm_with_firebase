package ai.retail.nimontron.dbhelper.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import ai.retail.nimontron.dbhelper.DbConstants;
import ai.retail.nimontron.dbhelper.entity.ImageMappingEntity;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ImageMappingDao {

    @Insert(onConflict = REPLACE)
    void saveBillBoardInfo(ImageMappingEntity imageMappingEntity);

    @Query("SELECT * FROM "+DbConstants.IMAGE_MAPPING_TABLE)
    LiveData<List<ImageMappingEntity>> getBillBoardInfo();

    @Query("DELETE FROM " + DbConstants.IMAGE_MAPPING_TABLE)
    void deleteAllBillBoardInfo();
}

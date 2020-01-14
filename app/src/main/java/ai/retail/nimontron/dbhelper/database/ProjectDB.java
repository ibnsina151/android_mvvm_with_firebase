package ai.retail.nimontron.dbhelper.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ai.retail.nimontron.dbhelper.DbConstants;
import ai.retail.nimontron.dbhelper.dao.ImageMappingDao;
import ai.retail.nimontron.dbhelper.entity.ImageMappingEntity;

import javax.inject.Singleton;

//import ai.retail.biponon.dbhelper.Converters;
//import ai.retail.biponon.dbhelper.DbConstants;
//import ai.retail.biponon.dbhelper.dao.CompetitionDao;
//import ai.retail.biponon.dbhelper.dao.OutletDao;
//import ai.retail.biponon.dbhelper.entity.CompetitionEntity;
//import ai.retail.biponon.dbhelper.entity.OutletEntity;

@Singleton
@Database(entities = {ImageMappingEntity.class},
        version = DbConstants.DB_VERSION, exportSchema = false)
//@TypeConverters(Converters.class)
public abstract class ProjectDB extends RoomDatabase {

    abstract public ImageMappingDao getImageMappingDao();

}

package ai.retail.nimontron.dbhelper;

//
//import ai.retail.biponon.firebase.datamodels.OutletTaskModel;
//import ai.retail.biponon.viewmodels.datamodels.CompetitorDetailsModel;
//import androidx.room.TypeConverter;

public class Converters {

//    @TypeConverter
//    public static ArrayList<CompetitorDetailsModel> fromString(String value) {
//        Type listType = new TypeToken<ArrayList<CompetitorDetailsModel>>() {
//        }.getType();
//        return new Gson().fromJson(value, listType);
//    }
//
//    @TypeConverter
//    public static String fromArrayList(ArrayList<CompetitorDetailsModel> list) {
//        Type listType = new TypeToken<ArrayList<CompetitorDetailsModel>>() {
//        }.getType();
//        return new Gson().toJson(list, listType);
//    }
//
//    @TypeConverter
//    public static ArrayList<OutletTaskModel> fromTaskString(String value) {
//        Type listType = new TypeToken<ArrayList<OutletTaskModel>>() {
//        }.getType();
//        return new Gson().fromJson(value, listType);
//    }
//
//    @TypeConverter
//    public static String fromTaskArrayList(ArrayList<OutletTaskModel> list) {
//        Type listType = new TypeToken<ArrayList<OutletTaskModel>>() {
//        }.getType();
//        return new Gson().toJson(list, listType);
//    }



    /*@TypeConverter
    public static ArrayList<CompetitionActionModel> fromCompetitionActionString(String value) {
        Type listType = new TypeToken<ArrayList<CompetitionActionModel>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromCompetitionActionArrayList(ArrayList<CompetitionActionModel> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }*/
}

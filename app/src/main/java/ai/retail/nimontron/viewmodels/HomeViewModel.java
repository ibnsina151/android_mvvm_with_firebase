package ai.retail.nimontron.viewmodels;

public class HomeViewModel extends BaseViewModel {

//    private HomeRepository mHomeRepository;
//    private HomeModel mHomeModel;
//
//    @Inject
//    public HomeViewModel(HomeRepository homeRepository) {
//        mHomeRepository = homeRepository;
//        mHomeModel = new HomeModel();
//    }
//
//    public LocationManager getLocationManager() {
//        return mHomeRepository.getLocationManager();
//    }
//
//    public HomeModel getHomeModel() {
//        return mHomeModel;
//    }
//
//    public void setHomeModel(HomeModel homeModel) {
//        this.mHomeModel = homeModel;
//    }
//
//    public void captureSelfie(String number, int requestCode) {
//        mHomeModel.setImagePath(mHomeRepository.captureSelfie(number, requestCode));
//    }
//
//    public void receivedImageResult() {
//        mHomeRepository.receivedImageResult(Uri.parse(new File(mHomeModel.getImagePath()).toString()));
//    }
//
//    public LiveData<Resource<Merchandiser>> getMerchandiser(String phoneNumber) {
//        return mHomeRepository.getMerchandiser(phoneNumber);
//    }
//
//    public LiveData<Resource<Uri>> uploadAttendance() {
//        File file = new File(getHomeModel().getImagePath());
//        /*if (!file.exists()){
//            MutableLiveData<Resource<Uri>> resourceMutableLiveData= new MutableLiveData<>();
//            resourceMutableLiveData.setValue(Resource.<Uri>error(IMAGE_NOT_FOUND));
//            return resourceMutableLiveData;
//        }*/
//        return mHomeRepository.uploadAttendanceImage(file);
//    }
//
//    public LiveData<Resource> saveAttendanceInfo(String phoneNumber, Uri data, String appVersion) {
//        return mHomeRepository.saveAttendanceInfo(phoneNumber, data, appVersion);
//    }
//
//    public LiveData<Resource<Attendance>> checkAttendance(String phoneNumber) {
//        return mHomeRepository.checkAttendance(phoneNumber);
//    }
//
//    public LiveData<Resource<ArrayList<OutletModel>>> getOutlets(String phoneNumber) {
//        return mHomeRepository.getOutlets(phoneNumber);
//    }
//
//    public LiveData<Integer> getSavedOutletCount() {
//        return mHomeRepository.getSavedOutletCount();
//    }
//
//    public void signOut() {
//        mHomeRepository.signOut();
//    }
//
//    public LiveData<String> checkTime() {
//        return mHomeRepository.checkServerTime();
//    }
//
//    public void saveAccessToken(String token) {
//        mHomeRepository.saveToken(token);
//    }
//
//    public void saveAttendanceState(boolean attendance){
//        mHomeRepository.saveAttendanceState(attendance);
//    }
//
//    public boolean getAttendanceState(){
//        return mHomeRepository.getAttendanceState();
//    }
//
//    public LiveData<Resource<Map<String, BmccModel>>> getBmccCode() {
//        return mHomeRepository.getBmccCodeMap();
//    }
}

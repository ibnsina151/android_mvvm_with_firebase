package ai.retail.nimontron.domain.repositories.impl;


public class AddInventoryRepositoryImpl  {

//    private DataManager mDataManager;
//    private InventoryApiService mInventoryApiService;
//
//    public AddInventoryRepositoryImpl(DataManager dataManager, InventoryApiService inventoryApiService) {
//        mDataManager = dataManager;
//        mInventoryApiService = inventoryApiService;
//    }
//
//    @Override
//    public LiveData<Resource<BaseDataEntity<InventoryItemEntity>>> getInventoryItems() {
//        final MutableLiveData<Resource<BaseDataEntity<InventoryItemEntity>>> liveInventoryItem = new MutableLiveData<>();
//        liveInventoryItem.setValue(Resource.<BaseDataEntity<InventoryItemEntity>>loading(null));
//
//        String accessToken = CommonTasks.genAccessToken(mDataManager.getAccessToken());
//
//        mInventoryApiService.getInventoryItems(accessToken).enqueue(new Callback<BaseDataEntity<InventoryItemEntity>>() {
//            @Override
//            public void onResponse(Call<BaseDataEntity<InventoryItemEntity>> call, Response<BaseDataEntity<InventoryItemEntity>> response) {
//                if (response.isSuccessful()) {
//                    liveInventoryItem.setValue(Resource.success(response.body()));
//                } else {
//                    liveInventoryItem.setValue(Resource.<BaseDataEntity<InventoryItemEntity>>error(ApiConstant.SOMETHING_WENT_WRONG, null));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BaseDataEntity<InventoryItemEntity>> call, Throwable t) {
//                liveInventoryItem.setValue(Resource.<BaseDataEntity<InventoryItemEntity>>error(ApiConstant.SOMETHING_WENT_WRONG, null));
//            }
//        });
//        return liveInventoryItem;
//    }

}

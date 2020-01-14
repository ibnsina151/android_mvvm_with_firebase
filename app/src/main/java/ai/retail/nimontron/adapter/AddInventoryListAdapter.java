package ai.retail.nimontron.adapter;

//import ai.retail.biponon.R;
//import ai.retail.biponon.adapter.diffutils.InventoryItemCallbacks;
//import ai.retail.biponon.adapter.viewholders.AddinventoryViewHolder;
//import ai.retail.biponon.databinding.ItemViewerInventoryBinding;
//import ai.retail.biponon.domain.entitysforinventory.InventoryItemEntity;


/**
 * Created by Ibn Sina Munim on 12/6/18.
 */

//public class AddInventoryListAdapter extends ListAdapter<InventoryItemEntity, AddinventoryViewHolder> {
//
//    private static final String TAG = "AddInventoryListAdapter";
//    private Context mContext;
//    private OnItemClickListener mOnItemClickListener;
//    public interface OnItemClickListener {
//        void onItemClick(InventoryItemEntity inventoryItemEntity);
//    }
//
//    public AddInventoryListAdapter(@NonNull InventoryItemCallbacks inventoryItemCallbacks, Context context) {
//        super(inventoryItemCallbacks);
//        this.mContext = context;
//    }
//
//    @NonNull
//    @Override
//    public AddinventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        ItemViewerInventoryBinding itemViewerInventoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_viewer_inventory,parent,false);
//        return new AddinventoryViewHolder(itemViewerInventoryBinding,mContext,mOnItemClickListener);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull AddinventoryViewHolder holder, int position) {
//        holder.bindTo(getItem(position));
//    }
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        mOnItemClickListener = onItemClickListener;
//    }
//
//
//
//}

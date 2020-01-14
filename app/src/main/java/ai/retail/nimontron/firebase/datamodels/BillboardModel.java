package ai.retail.nimontron.firebase.datamodels;

import java.io.Serializable;

public class BillboardModel implements Serializable, Cloneable {
    private String number;
    public static final int STATUS_NOT_VISITED = 0;
    public static final int STATUS_DATA_SAVED = 1;
    public static final int STATUS_DATA_UPLOADED = 2;
}

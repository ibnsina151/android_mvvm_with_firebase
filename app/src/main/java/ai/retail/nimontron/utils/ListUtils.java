package ai.retail.nimontron.utils;

import java.util.List;

public class ListUtils {
    public static boolean isEmpty(List<? extends Object> objects) {
        return objects == null || objects.size() == 0;
    }
}

package ai.retail.nimontron.network.networkutils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static ai.retail.nimontron.network.networkutils.Status.ERROR;
import static ai.retail.nimontron.network.networkutils.Status.LOADING;
import static ai.retail.nimontron.network.networkutils.Status.SUCCESS;

/**
 * A generic class for common use.
 * Shows status against operations
 * @param <T>
 */
public class Resource<T> {

    public static final String ERROR_MESSAGE = "Error detected! Please check your internet connection";
    public static final String BAD_RESPONSE_FROM_SERVER = "Unexpected response from server";

    @NonNull
    public final Status status;

    @Nullable
    public final String message;

    @Nullable
    public final T data;

    /**
     * {@link Resource} constructor
     * @param status
     * @param data
     * @param message
     */
    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    /**
     * Shows success status
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    /**
     * Shows error status
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    /**
     * Shows error status
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Resource<T> error(String msg) {
        return new Resource<>(ERROR, null, msg);
    }

    /**
     * Shows loading status
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    /**
     * Observes changes between current object and passed object as parameter
     * @param o as {@link Object}
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource<?> resource = (Resource<?>) o;

        if (status != resource.status) {
            return false;
        }

        if (message != null ? !message.equals(resource.message) : resource.message != null) {
            return false;
        }
        return data != null ? data.equals(resource.data) : resource.data == null;
    }

    /**
     * Compares objects and returns same integer value if objects are equal
     * @return integer value
     */
    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    /**
     * Converts all values into string
     * @return a {@link String} object
     */
    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

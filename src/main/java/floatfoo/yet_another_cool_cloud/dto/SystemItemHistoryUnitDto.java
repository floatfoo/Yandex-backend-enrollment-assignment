package floatfoo.yet_another_cool_cloud.dto;

import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;

public class SystemItemHistoryUnitDto {
    @NonNull
    private String id;
    private String url;
    private String parentId;
    @NonNull
    private String type;
    private int size;

    @NonNull
    private OffsetDateTime date;

    public SystemItemHistoryUnitDto(@NonNull String id, String url, String parentId, @NonNull String type, int size, @NonNull OffsetDateTime date) {
        this.id = id;
        this.url = url;
        this.parentId = parentId;
        this.type = type;
        this.size = size;
        this.date = date;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @NonNull
    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(@NonNull OffsetDateTime date) {
        this.date = date;
    }
}

package floatfoo.yet_another_cool_cloud.dto;

import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;
import java.util.List;

public class SystemItemDto {
    @NonNull
    private String id;
    private String url;
    @NonNull
    private OffsetDateTime date;
    private String parentId;
    @NonNull
    private String type;
    private int size;

    public SystemItemDto(@NonNull String id, String url, @NonNull OffsetDateTime date, String parentId, @NonNull String type, int size, List<SystemItemDto> children) {
        this.id = id;
        this.url = url;
        this.date = date;
        this.parentId = parentId;
        this.type = type;
        this.size = size;
        this.children = children;
    }

    public SystemItemDto(@NonNull String id, String url, @NonNull OffsetDateTime date, String parentId, @NonNull String type, int size) {
        this.id = id;
        this.url = url;
        this.date = date;
        this.parentId = parentId;
        this.type = type;
        this.size = size;
    }

    private List<SystemItemDto> children;

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

    @NonNull
    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(@NonNull OffsetDateTime date) {
        this.date = date;
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

    public List<SystemItemDto> getChildren() {
        return children;
    }

    public void setChildren(List<SystemItemDto> children) {
        this.children = children;
    }
}

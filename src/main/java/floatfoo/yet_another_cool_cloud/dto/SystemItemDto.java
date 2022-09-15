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

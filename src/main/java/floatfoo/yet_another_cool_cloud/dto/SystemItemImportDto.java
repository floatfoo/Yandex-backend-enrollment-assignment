package floatfoo.yet_another_cool_cloud.dto;

import org.springframework.lang.NonNull;

public class SystemItemImportDto {
    @NonNull
    private String id;
    private String url;
    private String parentId;
    @NonNull
    private String type;
    private int size = 0;

    public SystemItemImportDto(@NonNull String id, String url, String parentId, @NonNull String type, int size) {
        this.id = id;
        this.url = url;
        this.parentId = parentId;
        this.type = type;
        this.size = size;
    }

    public SystemItemImportDto(@NonNull String id, String url, String parentId, @NonNull String type) {
        this.id = id;
        this.url = url;
        this.parentId = parentId;
        this.type = type;
    }

    public SystemItemImportDto() {
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
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
}

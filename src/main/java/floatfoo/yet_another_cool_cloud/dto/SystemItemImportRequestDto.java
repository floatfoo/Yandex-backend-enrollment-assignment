package floatfoo.yet_another_cool_cloud.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class SystemItemImportRequestDto {
    private List<SystemItemImportDto> items;

    private OffsetDateTime updateDate;

    public SystemItemImportRequestDto(List<SystemItemImportDto> items, OffsetDateTime updateDate) {
        this.items = items;
        this.updateDate = updateDate;
    }

    public List<SystemItemImportDto> getItems() {
        return items;
    }

    public void setItems(List<SystemItemImportDto> items) {
        this.items = items;
    }

    public OffsetDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(OffsetDateTime updateDate) {
        this.updateDate = updateDate;
    }
}

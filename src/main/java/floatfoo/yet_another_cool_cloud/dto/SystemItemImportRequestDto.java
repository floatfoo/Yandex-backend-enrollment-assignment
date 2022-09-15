package floatfoo.yet_another_cool_cloud.dto;

import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;

public class SystemItemImportRequestDto {
    private List<SystemItemImportDto> items;

    private Instant updateDate;

    public SystemItemImportRequestDto(List<SystemItemImportDto> items, Instant updateDate) {
        this.items = items;
        this.updateDate = updateDate;
    }

    public SystemItemImportRequestDto() {
    }

    public List<SystemItemImportDto> getItems() {
        return items;
    }

    public void setItems(List<SystemItemImportDto> items) {
        this.items = items;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }
}

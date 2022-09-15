package floatfoo.yet_another_cool_cloud.dto;

import java.util.List;

public class SystemItemHistoryResponseDto {
    private List<SystemItemHistoryUnitDto> items;

    public List<SystemItemHistoryUnitDto> getItems() {
        return items;
    }

    public void setItems(List<SystemItemHistoryUnitDto> items) {
        this.items = items;
    }
}

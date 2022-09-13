package floatfoo.yet_another_cool_cloud.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@MappedSuperclass
@Table(name = "system_item")
public class SystemItem {
    @Column(name = "date")
    private OffsetDateTime date;

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }
}
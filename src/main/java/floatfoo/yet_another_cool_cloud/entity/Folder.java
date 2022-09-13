package floatfoo.yet_another_cool_cloud.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "folder")
public class Folder extends SystemItem {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "date")
    private OffsetDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Folder parent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public Folder getParent() {
        return parent;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", parent=" + parent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Folder folder)) return false;
        return id.equals(folder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
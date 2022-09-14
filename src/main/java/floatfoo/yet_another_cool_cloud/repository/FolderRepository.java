package floatfoo.yet_another_cool_cloud.repository;

import floatfoo.yet_another_cool_cloud.entity.Folder;
import floatfoo.yet_another_cool_cloud.entity.SystemItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends CrudRepository<Folder, String> {
    @Query("select f from Folder f where f.parent.id = ?1")
    List<Folder> findByParent_IdEquals(String id);
}

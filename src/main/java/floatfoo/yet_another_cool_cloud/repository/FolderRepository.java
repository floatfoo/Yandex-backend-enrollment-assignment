package floatfoo.yet_another_cool_cloud.repository;

import floatfoo.yet_another_cool_cloud.entity.Folder;
import floatfoo.yet_another_cool_cloud.entity.SystemItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends CrudRepository<Folder, String> {
}

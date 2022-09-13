package floatfoo.yet_another_cool_cloud.repository;

import floatfoo.yet_another_cool_cloud.entity.Folder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends CrudRepository<Folder, String> {
}

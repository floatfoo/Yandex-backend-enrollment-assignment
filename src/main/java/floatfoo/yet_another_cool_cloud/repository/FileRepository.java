package floatfoo.yet_another_cool_cloud.repository;

import floatfoo.yet_another_cool_cloud.entity.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<File, String> {
}

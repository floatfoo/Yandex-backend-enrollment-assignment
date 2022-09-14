package floatfoo.yet_another_cool_cloud.repository;

import floatfoo.yet_another_cool_cloud.entity.File;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends CrudRepository<File, String> {
    @Query("select f from File f where f.parent.id = ?1")
    List<File> findByParent_IdEquals(String id);
}

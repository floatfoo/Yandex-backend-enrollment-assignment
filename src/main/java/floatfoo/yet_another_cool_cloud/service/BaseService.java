package floatfoo.yet_another_cool_cloud.service;

import com.sun.istack.NotNull;
import floatfoo.yet_another_cool_cloud.dto.ObjectMapper;
import floatfoo.yet_another_cool_cloud.dto.SystemItemImportDto;
import floatfoo.yet_another_cool_cloud.dto.SystemItemImportRequestDto;
import floatfoo.yet_another_cool_cloud.entity.File;
import floatfoo.yet_another_cool_cloud.entity.Folder;
import floatfoo.yet_another_cool_cloud.repository.FileRepository;
import floatfoo.yet_another_cool_cloud.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class BaseService {

    private FolderRepository folderRepository;
    private FileRepository fileRepository;
    private ExecutorService pool;

    @Autowired
    public BaseService(FolderRepository folderRepository, FileRepository fileRepository) {
        this.folderRepository = folderRepository;
        this.fileRepository = fileRepository;
        this.pool = Executors.newFixedThreadPool(100);
    }

    public ResponseEntity importItems(SystemItemImportRequestDto requestDto) {
        // Ensure unique ids among single request
        Set<String> requestItemsIds = new HashSet<>();
        for (SystemItemImportDto item: requestDto.getItems()) {
            if (requestItemsIds.contains(item.getId()))
                return ResponseEntity.badRequest().build();
            requestItemsIds.add(item.getId());
        }
        for (SystemItemImportDto item: requestDto.getItems()) {
            pool.submit(() -> {
                if (item.getType().equals("FILE")) {
                    File newFile = ObjectMapper.mapToFile(item, folderRepository);
                    newFile.setDate(requestDto.getUpdateDate());
                    fileRepository.save(newFile);
                    return ResponseEntity.ok().build();
                } else if (item.getType().equals("FOLDER")) {
                    Folder newFolder = ObjectMapper.mapToFolder(item, folderRepository);
                    newFolder.setDate(requestDto.getUpdateDate());
                    folderRepository.save(newFolder);
                    return ResponseEntity.ok().build();
                } else return ResponseEntity.badRequest().build();
            });
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deleteItem(@NotNull String id) {
        if (fileRepository.findById(id).isPresent()) {
            fileRepository.deleteById(id);
        } else if (folderRepository.findById(id).isPresent()) {
            List<File> childrenFiles = fileRepository.findByParent_IdEquals(id);
            for (File file: childrenFiles)
                fileRepository.delete(file);
            List<Folder> childrenFolders = folderRepository.findByParent_IdEquals(id);
            for (Folder folder: childrenFolders)
                deleteItem(folder.getId());
            folderRepository.deleteById(id);
        } else return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}

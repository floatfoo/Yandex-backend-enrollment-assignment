package floatfoo.yet_another_cool_cloud.service;

import com.sun.istack.NotNull;
import floatfoo.yet_another_cool_cloud.dto.ObjectMapper;
import floatfoo.yet_another_cool_cloud.dto.SystemItemDto;
import floatfoo.yet_another_cool_cloud.dto.SystemItemImportDto;
import floatfoo.yet_another_cool_cloud.dto.SystemItemImportRequestDto;
import floatfoo.yet_another_cool_cloud.entity.File;
import floatfoo.yet_another_cool_cloud.entity.Folder;
import floatfoo.yet_another_cool_cloud.repository.FileRepository;
import floatfoo.yet_another_cool_cloud.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class BaseService {

    private final FolderRepository folderRepository;
    private final FileRepository fileRepository;

    @Autowired
    public BaseService(FolderRepository folderRepository, FileRepository fileRepository) {
        this.folderRepository = folderRepository;
        this.fileRepository = fileRepository;
    }

    public ResponseEntity<HttpStatus> importItems(@NonNull SystemItemImportRequestDto requestDto) {
        // Ensure unique ids among single request dto
        Set<String> requestItemsIds = new HashSet<>();
        for (SystemItemImportDto item: requestDto.getItems()) {
            if (requestItemsIds.contains(item.getId()))
                return ResponseEntity.badRequest().build();
            requestItemsIds.add(item.getId());
        }

        for (SystemItemImportDto item: requestDto.getItems()) {
            if (item.getType().equals("FILE")) {
                File newFile = ObjectMapper.mapToFile(item, folderRepository);
                newFile.setDate(requestDto.getUpdateDate());
                fileRepository.save(newFile);
            } else if (item.getType().equals("FOLDER")) {
                Folder newFolder = ObjectMapper.mapToFolder(item, folderRepository);
                newFolder.setDate(requestDto.getUpdateDate());
                folderRepository.save(newFolder);
            } else return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<HttpStatus> deleteItem(@NotNull String id) {
        if (fileRepository.findById(id).isPresent()) {
            fileRepository.deleteById(id);
        } else if (folderRepository.findById(id).isPresent()) {
            List<File> childrenFiles = fileRepository.findByParent_IdEquals(id);
            fileRepository.deleteAll(childrenFiles);
            List<Folder> childrenFolders = folderRepository.findByParent_IdEquals(id);
            for (Folder folder: childrenFolders)
                deleteItem(folder.getId());
            folderRepository.deleteById(id);
        } else return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    public SystemItemDto getItem(String id) {
        if (fileRepository.findById(id).isPresent()) {
            File foundFile = fileRepository.findById(id).get();
            return ObjectMapper.mapFromFile(foundFile);
        } else if (folderRepository.findById(id).isPresent()) {
            SystemItemDto foundFolder = ObjectMapper.mapFromFolder(folderRepository.findById(id).get(), folderRepository, fileRepository);
            foundFolder.setChildren(new ArrayList<>());
            List<File> childrenFiles = fileRepository.findByParent_IdEquals(id);
            List<Folder> childrenFolders = folderRepository.findByParent_IdEquals(id);
            for (File file: childrenFiles)
                foundFolder.getChildren().add(getItem(file.getId()));
            for (Folder folder: childrenFolders)
                foundFolder.getChildren().add(getItem(folder.getId()));
            return foundFolder;
        } else return new SystemItemDto();
    }
}

package floatfoo.yet_another_cool_cloud.service;

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
import java.util.Set;

@Service
public class BaseService {

    private FolderRepository folderRepository;
    private FileRepository fileRepository;

    @Autowired
    public BaseService(FolderRepository folderRepository, FileRepository fileRepository) {
        this.folderRepository = folderRepository;
        this.fileRepository = fileRepository;
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
}

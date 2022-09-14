package floatfoo.yet_another_cool_cloud.dto;

import floatfoo.yet_another_cool_cloud.entity.File;
import floatfoo.yet_another_cool_cloud.entity.Folder;
import floatfoo.yet_another_cool_cloud.repository.FolderRepository;

import java.time.OffsetDateTime;
import java.util.Optional;

public class ObjectMapper {
    public static File mapToFile(SystemItemImportDto dto, FolderRepository folderRepository) {
        File file = new File();
        file.setId(dto.getId());
        file.setDate(OffsetDateTime.now());
        file.setSize(dto.getSize());
        file.setUrl(dto.getUrl());
        Optional<Folder> parent = folderRepository.findById(dto.getParentId());
        if (parent.isPresent())
            file.setParent(parent.get());
        return file;
    }

    public static Folder mapToFolder(SystemItemImportDto dto, FolderRepository folderRepository) {
        Folder folder = new Folder();
        folder.setId(dto.getId());
        folder.setDate(OffsetDateTime.now());
        Optional<Folder> parent = folderRepository.findById(dto.getId());
        if (parent.isPresent())
            folder.setParent(parent.get());
        return folder;
    }
}

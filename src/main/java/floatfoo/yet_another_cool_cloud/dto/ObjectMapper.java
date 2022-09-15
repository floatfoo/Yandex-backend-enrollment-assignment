package floatfoo.yet_another_cool_cloud.dto;

import floatfoo.yet_another_cool_cloud.entity.File;
import floatfoo.yet_another_cool_cloud.entity.Folder;
import floatfoo.yet_another_cool_cloud.repository.FileRepository;
import floatfoo.yet_another_cool_cloud.repository.FolderRepository;
import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ObjectMapper {
    @NonNull
    public static File mapToFile(@NonNull SystemItemImportDto dto, @NonNull FolderRepository folderRepository) {
        File file = new File();
        file.setId(dto.getId());
        file.setDate(OffsetDateTime.now());
        file.setSize(dto.getSize());
        file.setUrl(dto.getUrl());
        Optional<Folder> parent = folderRepository.findById(dto.getParentId());
        parent.ifPresent(file::setParent);
        return file;
    }

    @NonNull
    public static Folder mapToFolder(@NonNull SystemItemImportDto dto, @NonNull FolderRepository folderRepository) {
        Folder folder = new Folder();
        folder.setId(dto.getId());
        folder.setDate(OffsetDateTime.now());
        if (dto.getParentId() != null) {
            Optional<Folder> parent = folderRepository.findById(dto.getParentId());
            parent.ifPresent(folder::setParent);
        }
        return folder;
    }

    @NonNull
    public static SystemItemDto mapFromFile(@NonNull File file) {
        SystemItemDto systemItemDto = new SystemItemDto();
        systemItemDto.setId(file.getId());
        systemItemDto.setUrl(file.getUrl());
        systemItemDto.setSize(file.getSize());
        systemItemDto.setType("FILE");
        systemItemDto.setDate(OffsetDateTime.now());
        if (file.getParent() != null) systemItemDto.setParentId(file.getParent().getId());
        systemItemDto.setChildren(null);
        return systemItemDto;
    }

    @NonNull
    public static SystemItemDto mapFromFolder(@NonNull Folder folder, FolderRepository folderRepository, FileRepository fileRepository) {
        SystemItemDto systemItemDto = new SystemItemDto();
        systemItemDto.setId(folder.getId());
        systemItemDto.setType("FOLDER");
        systemItemDto.setDate(OffsetDateTime.now());
        if (folder.getParent() != null) systemItemDto.setParentId(folder.getParent().getId());

        systemItemDto.setChildren(new ArrayList<>());

        List<File> childrenFiles = fileRepository.findByParent_IdEquals(folder.getId());
        for (File file: childrenFiles)
            systemItemDto.getChildren().add(ObjectMapper.mapFromFile(file));

        List<Folder> childrenFolders = folderRepository.findByParent_IdEquals(folder.getId());
        for (Folder childFolder: childrenFolders)
            systemItemDto.getChildren().add(ObjectMapper.mapFromFolder(childFolder, folderRepository, fileRepository));

        systemItemDto.setSize(ObjectMapper.getFolderSize(folder, folderRepository, fileRepository));
        return systemItemDto;
    }

    private static int getFolderSize(@NonNull Folder folder, FolderRepository folderRepository, @NonNull FileRepository fileRepository) {
        int size = 0;

        List<File> childrenFiles = fileRepository.findByParent_IdEquals(folder.getId());
        for (File childFile: childrenFiles)
            size += childFile.getSize();

        List<Folder> childrenFolders = folderRepository.findByParent_IdEquals(folder.getId());
        for (Folder childFolder: childrenFolders)
            size += getFolderSize(childFolder, folderRepository, fileRepository);

        return size;
    }
}

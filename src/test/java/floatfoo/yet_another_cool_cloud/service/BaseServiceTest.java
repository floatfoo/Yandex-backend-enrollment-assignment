package floatfoo.yet_another_cool_cloud.service;

import floatfoo.yet_another_cool_cloud.dto.SystemItemImportDto;
import floatfoo.yet_another_cool_cloud.dto.SystemItemImportRequestDto;
import floatfoo.yet_another_cool_cloud.entity.File;
import floatfoo.yet_another_cool_cloud.entity.Folder;
import floatfoo.yet_another_cool_cloud.repository.FileRepository;
import floatfoo.yet_another_cool_cloud.repository.FolderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BaseServiceTest {

    @Autowired
    private BaseService baseService;

    @MockBean
    private FileRepository fileRepository;

    @MockBean
    private FolderRepository folderRepository;

    @Test
    void importItems_Should_Return_ResponseEntity_200() {
        when(folderRepository.findById(any())).thenReturn(Optional.empty());
        when(fileRepository.findById(any())).thenReturn(Optional.empty());
        SystemItemImportDto folderItem1 = new SystemItemImportDto("DIR_1", null, null, "FOLDER");
        SystemItemImportDto folderItem2 = new SystemItemImportDto("DIR_2", null, "DIR_1", "FOLDER");
        SystemItemImportDto fileItem = new SystemItemImportDto("FIlE_1", "/etc/gentoo/make.conf", "DIR_1", "FILE", 234);
        SystemItemImportRequestDto requestDto = new SystemItemImportRequestDto(List.of(folderItem1, folderItem2, fileItem), OffsetDateTime.now());
        assertEquals(ResponseEntity.ok().build(), baseService.importItems(requestDto));
    }

    @Test
    void importItem_Should_Return_ResponseEntity_400_Duplicate_Ids() {
        when(folderRepository.findById(any())).thenReturn(Optional.empty());
        when(fileRepository.findById(any())).thenReturn(Optional.empty());
        SystemItemImportDto folderItem1 = new SystemItemImportDto("DIR_2", null, null, "FOLDER");
        SystemItemImportDto folderItem2 = new SystemItemImportDto("DIR_2", null, "DIR_1", "FOLDER");
        SystemItemImportDto fileItem = new SystemItemImportDto("FIlE_1", "/etc/gentoo/make.conf", "DIR_1", "FILE", 234);
        SystemItemImportRequestDto requestDto = new SystemItemImportRequestDto(List.of(folderItem1, folderItem2, fileItem), OffsetDateTime.now());
        assertEquals(ResponseEntity.badRequest().build(), baseService.importItems(requestDto));
    }

    @Test
    void deleteItem_Should_Delete_File_And_Return_200() {
        when(fileRepository.findById(any())).thenReturn(Optional.of(new File()));
        assertEquals(ResponseEntity.ok().build(), baseService.deleteItem("FILE_1"));
        verify(fileRepository).deleteById("FILE_1");
    }

    @Test
    void deleteItem_File_NotFound_Return_404() {
        when(fileRepository.findById(any())).thenReturn(Optional.empty());
        when(folderRepository.findById(any())).thenReturn(Optional.empty());
        assertEquals(ResponseEntity.notFound().build(), baseService.deleteItem("DIR_1"));
    }
}
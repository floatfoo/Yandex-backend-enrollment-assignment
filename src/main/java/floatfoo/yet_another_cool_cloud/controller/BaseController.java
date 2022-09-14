package floatfoo.yet_another_cool_cloud.controller;

import floatfoo.yet_another_cool_cloud.dto.SystemItemImportRequestDto;
import floatfoo.yet_another_cool_cloud.service.BaseService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
public class BaseController {
    private BaseService baseService;

    @Autowired
    public BaseController(BaseService baseService) {
        this.baseService = baseService;
    }

    @PostMapping("/imports")
    @ApiOperation("Импорт файлов и папок. Обновляет при повторном импорте")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Вставка или обновление прошли усешно!"),
            @ApiResponse(code = 400, message = "Невалидная схема документа или входные данные не верны")
    })
    public ResponseEntity importItems(@RequestBody @NonNull SystemItemImportRequestDto requestDto) {
        return baseService.importItems(requestDto);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Удалить элемент по идентификатору. При удалении папки удаляются все дочерние элементы. Доступ к истории обновлений удаленного элемента невозможен.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Удаление прошло успешно"),
            @ApiResponse(code = 400, message = "Невалидная схема документа или исходные данные не верны"),
            @ApiResponse(code = 404, message = "Элемент не найден")
    })
    public ResponseEntity deleteItem(@PathVariable(value = "id") String id) {
        return baseService.deleteItem(id);
    }

    /*@GetMapping("/nodes/{id}")
    public List<SystemItemDto> getItem() {
    }*/
}

package ru.biponline.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.biponline.demo.entity.PublishingEntity;
import ru.biponline.demo.exception.ValidationExceptionPublishing;
import ru.biponline.demo.response.BaseResponse;
import ru.biponline.demo.response.PublishingListResponse;
import ru.biponline.demo.response.PublishingResponse;
import ru.biponline.demo.service.PublishingService;
import ru.biponline.demo.utils.PublishingValidationUtils;

@RestController
@RequestMapping("api/v1/publisher")
public class PublishingController {
    private final PublishingService service;
    private PublishingController(PublishingService service) {this.service=service;}

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll(){
        return ResponseEntity.ok(new PublishingListResponse(service.getAll()));
    }

    @PostMapping("/add")
    public ResponseEntity <BaseResponse> registration(@RequestBody PublishingEntity data) {
        try {
            PublishingValidationUtils.validationPublishing(data);
            service.save(data);
            return ResponseEntity.ok(new BaseResponse(true, "Издательство добавлено."));
        } catch (ValidationExceptionPublishing e){
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity <BaseResponse> update(@RequestBody PublishingEntity data) {
        try {
            PublishingValidationUtils.validationPublishing(data);
            service.save(data);
            return ResponseEntity.ok(new BaseResponse(true,"В издательство внесены изменения."));
        } catch (ValidationExceptionPublishing e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @DeleteMapping("{publishing_id}")
    public ResponseEntity<?> delete(@PathVariable long publishing_id) {
        try {
            service.delete(publishing_id);
            return ResponseEntity.ok(new BaseResponse(true,"Издательство удалено."));
        } catch (Exception e) {
            return ResponseEntity.ok((new BaseResponse(false, e.getMessage())));
        }
    }

    @GetMapping("{publishing_id}")
    public ResponseEntity<BaseResponse> getOne(@PathVariable long publishing_id){
        return ResponseEntity.ok(new PublishingResponse(service.getPublishing(publishing_id)));
    }
}

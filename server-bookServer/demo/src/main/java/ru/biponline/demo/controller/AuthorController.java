package ru.biponline.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.biponline.demo.entity.AuthorEntity;
import ru.biponline.demo.exception.ValidationExceptionAuthor;
import ru.biponline.demo.response.AuthorListResponse;
import ru.biponline.demo.response.BaseResponse;
import ru.biponline.demo.service.AuthorService;
import ru.biponline.demo.utils.AuthorValidationUtils;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {
    private final AuthorService service;
    private AuthorController(AuthorService service) {this.service=service;}

    @GetMapping("/all")
    public ResponseEntity <BaseResponse> getAll(){
        return ResponseEntity.ok(new AuthorListResponse(service.getAll()));
    }

    @PostMapping("/add")
    public ResponseEntity <BaseResponse> registration(@RequestBody AuthorEntity data) {
        try {
            AuthorValidationUtils.authorValidation(data);
            service.save(data);
            return ResponseEntity.ok(new BaseResponse(true, "Автор добавлен."));
        } catch (ValidationExceptionAuthor e){
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity <BaseResponse> update(@RequestBody AuthorEntity data) {
        try {
            AuthorValidationUtils.authorValidation(data);
            service.save(data);
            return ResponseEntity.ok(new BaseResponse(true,"К автору внесены изменения."));
        } catch (ValidationExceptionAuthor e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage()));
        }
    }

    @DeleteMapping("{author_id}")
    public ResponseEntity<?> delete(@PathVariable long author_id) {
        try {
            service.delete(author_id);
            return ResponseEntity.ok(new BaseResponse(true,"Автор удален."));
        } catch (Exception e) {
            return ResponseEntity.ok((new BaseResponse(false, e.getMessage())));
        }
    }
}

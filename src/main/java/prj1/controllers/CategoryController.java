package prj1.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import prj1.exceptions.CustomException;
import prj1.models.category.BaseCategoryDTO;
import prj1.models.category.RequestCategory;
import prj1.models.category.TagPropertyDTO;
import prj1.models.response.ResponseData;
import prj1.services.CategoryService;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/category")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public Mono<ResponseEntity<ResponseData<Object>>> search(
            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber,
            ServerHttpRequest request) {
        Integer pageSize = 10;
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            Page<BaseCategoryDTO> pageCategory = categoryService.search(name, PageRequest.of(pageNumber, pageSize));
            response.success(pageCategory.getContent());
        } catch (CustomException ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.BAD_REQUEST;
            response.error(statusResponse.value(), ex.getMessage(), ex.getMessageKey(), statusResponse.value());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
            response.error(statusResponse.value(), ex.getMessage(), statusResponse.value());
        }
        return Mono.just(new ResponseEntity<>(response, statusResponse));
    }

    @PostMapping
    public Mono<ResponseEntity<ResponseData<Object>>> createCategory(@RequestBody BaseCategoryDTO category, ServerHttpRequest request) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            categoryService.createCategory(category);
            response.success("success");
        } catch (CustomException ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.BAD_REQUEST;
            response.error(statusResponse.value(), ex.getMessage(), ex.getMessageKey(), statusResponse.value());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
            response.error(statusResponse.value(), ex.getMessage(), statusResponse.value());
        }
        return Mono.just(new ResponseEntity<>(response, statusResponse));
    }

    @PostMapping("/create-multi")
    public Mono<ResponseEntity<ResponseData<Object>>> createCategoryTagProperty(@RequestBody RequestCategory requestCategory, ServerHttpRequest request) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            categoryService.createCategoryTagProperty(requestCategory);
            response.success("success");
        } catch (CustomException ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.BAD_REQUEST;
            response.error(statusResponse.value(), ex.getMessage(), ex.getMessageKey(), statusResponse.value());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
            response.error(statusResponse.value(), ex.getMessage(), statusResponse.value());
        }
        return Mono.just(new ResponseEntity<>(response, statusResponse));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ResponseData<Object>>> updateCategory(@PathVariable String id, @RequestBody BaseCategoryDTO categoryDTO, ServerHttpRequest request) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            categoryService.updateCategory(id, categoryDTO);
            response.success("success");
        } catch (CustomException ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.BAD_REQUEST;
            response.error(statusResponse.value(), ex.getMessage(), ex.getMessageKey(), statusResponse.value());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
            response.error(statusResponse.value(), ex.getMessage(), statusResponse.value());
        }
        return Mono.just(new ResponseEntity<>(response, statusResponse));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ResponseData<Object>>> deleteCategory(@PathVariable String id, ServerHttpRequest request) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            categoryService.deleteCategory(id);
            response.success("success");
        } catch (CustomException ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.BAD_REQUEST;
            response.error(statusResponse.value(), ex.getMessage(), ex.getMessageKey(), statusResponse.value());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
            response.error(statusResponse.value(), ex.getMessage(), statusResponse.value());
        }
        return Mono.just(new ResponseEntity<>(response, statusResponse));
    }

    @PostMapping("/{id}/tag-property")
    public Mono<ResponseEntity<ResponseData<Object>>> createTagProperty(@PathVariable String id, @RequestBody TagPropertyDTO dto, ServerHttpRequest request) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            categoryService.createTagProperty(id, dto);
            response.success("success");
        } catch (CustomException ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.BAD_REQUEST;
            response.error(statusResponse.value(), ex.getMessage(), ex.getMessageKey(), statusResponse.value());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
            response.error(statusResponse.value(), ex.getMessage(), statusResponse.value());
        }
        return Mono.just(new ResponseEntity<>(response, statusResponse));
    }


    @PutMapping("/{id}/tag-property")
    public Mono<ResponseEntity<ResponseData<Object>>> updateCategory(@PathVariable String id, @RequestBody TagPropertyDTO tagPropertyDTO, ServerHttpRequest request) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            categoryService.updateTagProperty(id, tagPropertyDTO);
            response.success("success");
        } catch (CustomException ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.BAD_REQUEST;
            response.error(statusResponse.value(), ex.getMessage(), ex.getMessageKey(), statusResponse.value());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
            response.error(statusResponse.value(), ex.getMessage(), statusResponse.value());
        }
        return Mono.just(new ResponseEntity<>(response, statusResponse));
    }

    @GetMapping("/{id}/tag-property")
    public Mono<ResponseEntity<ResponseData<Object>>> getTagProductPropertyByCategoryId(@PathVariable String id, ServerHttpRequest request) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            response.success(categoryService.getTagPropertyByCategoryId(id));
        } catch (CustomException ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.BAD_REQUEST;
            response.error(statusResponse.value(), ex.getMessage(), ex.getMessageKey(), statusResponse.value());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
            response.error(statusResponse.value(), ex.getMessage(), statusResponse.value());
        }
        return Mono.just(new ResponseEntity<>(response, statusResponse));
    }
}

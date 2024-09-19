package prj1.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import prj1.exceptions.CustomException;
import prj1.models.product.GetProductDTO;
import prj1.models.product.PostProductDTO;
import prj1.models.response.ResponseData;
import prj1.services.IProductService;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {
    private final IProductService iProductService;

    @GetMapping("/list")
    public Mono<ResponseEntity<ResponseData<Object>>> list(
            ServerHttpRequest request,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) ObjectId id,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {

        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            Page<GetProductDTO> list = iProductService.list(name, status, id, PageRequest.of(page, size));
            response.success(list);
            statusResponse = HttpStatus.OK;
        } catch (CustomException ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.BAD_REQUEST;
            response.error(
                    statusResponse.value(), ex.getMessage(), ex.getMessageKey(), statusResponse.value());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
            response.error(statusResponse.value(), ex.getMessage(), statusResponse.value());
        }
        return Mono.just(new ResponseEntity<>(response, statusResponse));
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<ResponseData<Object>>> create(
            @Valid @RequestBody PostProductDTO dto,
            ServerHttpRequest request) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;

        try {
            response.success(iProductService.create(dto));
            statusResponse = HttpStatus.OK;
        } catch (CustomException ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.BAD_REQUEST;
            response.error(
                    statusResponse.value(), ex.getMessage(), ex.getMessageKey(), statusResponse.value());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
            response.error(statusResponse.value(), ex.getMessage(), statusResponse.value());
        }
        return Mono.just(new ResponseEntity<>(response, statusResponse));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ResponseData<Object>>> getById(
            ServerHttpRequest request, @PathVariable String id) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            response.success(iProductService.findById(id));
            statusResponse = HttpStatus.OK;
        } catch (CustomException ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.BAD_REQUEST;
            response.error(
                    statusResponse.value(), ex.getMessage(), ex.getMessageKey(), statusResponse.value());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
            response.error(statusResponse.value(), ex.getMessage(), statusResponse.value());
        }
        return Mono.just(new ResponseEntity<>(response, statusResponse));
    }

    @PutMapping("update/{id}")
    public Mono<ResponseEntity<ResponseData<Object>>> update(
            @PathVariable String id,
            @Valid @RequestBody PostProductDTO dto,
            ServerHttpRequest request) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;

        try {
            iProductService.update(id, dto);
            response.success("updated");
            statusResponse = HttpStatus.OK;
        } catch (CustomException ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.BAD_REQUEST;
            response.error(
                    statusResponse.value(), ex.getMessage(), ex.getMessageKey(), statusResponse.value());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
            response.error(statusResponse.value(), ex.getMessage(), statusResponse.value());
        }
        return Mono.just(new ResponseEntity<>(response, statusResponse));
    }

    @DeleteMapping("delete/{id}")
    public Mono<ResponseEntity<ResponseData<Object>>> delete(
            @PathVariable String id, ServerHttpRequest request) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            iProductService.delete(id);
            response.success("deleted");
            statusResponse = HttpStatus.OK;
        } catch (CustomException ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.BAD_REQUEST;
            response.error(
                    statusResponse.value(), ex.getMessage(), ex.getMessageKey(), statusResponse.value());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            statusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
            response.error(statusResponse.value(), ex.getMessage(), statusResponse.value());
        }
        return Mono.just(new ResponseEntity<>(response, statusResponse));
    }
}

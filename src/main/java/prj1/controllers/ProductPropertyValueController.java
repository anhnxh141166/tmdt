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
import prj1.models.productpropertyvalue.ProductPropertyValueDTO;
import prj1.models.response.ResponseData;
import prj1.services.ProductPropertyValueService;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/property-value")
public class ProductPropertyValueController {
    private final ProductPropertyValueService productPropertyValueService;

    @GetMapping
    public Mono<ResponseEntity<ResponseData<Object>>> search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber,
            ServerHttpRequest request) {
        Integer pageSize = 10;
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            Page<ProductPropertyValueDTO> pagePropertyValue = productPropertyValueService.search(name, PageRequest.of(pageNumber, pageSize));
            response.success(pagePropertyValue.getContent());
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

    @PostMapping()
    public Mono<ResponseEntity<ResponseData<Object>>> create(
            @RequestBody ProductPropertyValueDTO productPropertyValueDTO,
            ServerHttpRequest request) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            productPropertyValueService.createPropertyValue(productPropertyValueDTO);
            response.success("success");
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

    @PutMapping()
    public Mono<ResponseEntity<ResponseData<Object>>> update(
//            @PathVariable String id,
            @RequestBody ProductPropertyValueDTO productPropertyValueDTO,
            ServerHttpRequest request) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            productPropertyValueService.updatePropertyValue(productPropertyValueDTO);
            response.success("success");
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

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ResponseData<Object>>> delete(
            @PathVariable String id,
            ServerHttpRequest request) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            productPropertyValueService.deletePropertyValue(id);
            response.success("success");
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



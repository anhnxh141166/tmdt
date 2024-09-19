package prj1.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import prj1.exceptions.CustomException;
import prj1.models.product.GetGroupProductDTO;
import prj1.models.product.PostGroupProductDTO;
import prj1.models.response.ResponseData;
import prj1.services.IGroupProductService;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/group_product")
@Slf4j
public class GroupProductController {
    private final IGroupProductService iGroupProductService;

    @PostMapping("/create")
    public Mono<ResponseEntity<ResponseData<Object>>> create(
            @RequestBody PostGroupProductDTO dto, ServerHttpRequest request) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            response.success(iGroupProductService.create(dto));
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
            statusResponse = HttpStatus.OK;
            GetGroupProductDTO res = iGroupProductService.findById(id);
            response.success(res);
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
            @RequestBody PostGroupProductDTO dto, ServerHttpRequest request, @PathVariable String id) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            GetGroupProductDTO res = iGroupProductService.update(id, dto);
            response.success(res);
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
            ServerHttpRequest request, @PathVariable String id) {
        ResponseData<Object> response = new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            iGroupProductService.delete(id);
            response.success("deleted");
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

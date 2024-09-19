package prj1.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import prj1.exceptions.CustomException;
import prj1.models.promotion.PromotionDTO;
import prj1.models.promotion.PromotionRequets;
import prj1.models.response.ResponseData;
import prj1.services.DetailPromotionService;
import prj1.services.PromotionService;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/promotion")
@Slf4j
@RequiredArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;

    private final DetailPromotionService detailPromotionService;


    @GetMapping
    public Mono<ResponseEntity<ResponseData<?>>> getAllPromotion(
            ServerHttpRequest request,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ) {
        ResponseData<Object> response =
                new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
//            response.success(promotionService.getAllPromotion(search, status, PageRequest.of(page, size)));
            Page<PromotionDTO> pageData = promotionService.getAllPromotionDTO(name, status, PageRequest.of(page, size));
//            Page<DetailPromotion> pageData = detailPromotionRepository.findAll(PageRequest.of(page, size));
            if (pageData.hasContent()) {
                // Chứa dữ liệu, thực hiện xử lý
                response.success(pageData.getContent());
//                response.success(promotionService.getAll(PageRequest.of(page, size)));

            } else {
                // Không có dữ liệu, thực hiện xử lý khác hoặc trả về thông báo rỗng
                response.success(Collections.emptyList());
            }

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

    @GetMapping("/detail/{id}")
    public Mono<ResponseEntity<ResponseData<?>>> getPromotionById(
            ServerHttpRequest request,
            @PathVariable(value = "id", required = false) String id
    ) {
        ResponseData<Object> response =
                new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            response.success(promotionService.getDetailPromotionById(id));
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


    @PostMapping
    public Mono<ResponseEntity<ResponseData<?>>> createPromotion(
            @RequestBody PromotionRequets promotionRequets, ServerHttpRequest request
    ) {
        ResponseData<Object> response =
                new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            promotionService.createPromotion(promotionRequets);
            response.success("");
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

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ResponseData<?>>> updatePromotion(
            @RequestBody PromotionRequets promotionRequets, ServerHttpRequest request,
            @PathVariable("id") String idPromotion
    ) {
        ResponseData<Object> response =
                new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            promotionService.updatePromotion(idPromotion, promotionRequets);
            response.success("");
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

    @DeleteMapping("/{name}")
    public Mono<ResponseEntity<ResponseData<?>>> deletePromotion(
            ServerHttpRequest request,
            @PathVariable("name") String name
    ) {
        ResponseData<Object> response =
                new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            promotionService.removePromotion(name);
            response.success("");
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

    @GetMapping("/groupProduct/{id}")
    public Mono<ResponseEntity<ResponseData<?>>> getDetailPromotionByIdGroupProduct(
            @PathVariable("id") String id,
            ServerHttpRequest request
    ) {
        ResponseData<Object> response =
                new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            response.success(detailPromotionService.getDetailPromotionByIdGroupProduct(id));
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

    @GetMapping("/dt")
    public Mono<ResponseEntity<ResponseData<?>>> getDetailPromotionByProduct(
            ServerHttpRequest request
    ) {
        ResponseData<Object> response =
                new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            response.success(detailPromotionService.getDetailPromotionByStatusPromotion());
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

    @GetMapping("/dt/{id}")
    public Mono<ResponseEntity<ResponseData<?>>> getDetailPromotionByPromotionId(
            @PathVariable("id") String promotionID,
            ServerHttpRequest request
    ) {
        ResponseData<Object> response =
                new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            response.success(detailPromotionService.getDetailPromotionByPromotionId(promotionID));
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

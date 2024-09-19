package prj1.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prj1.exceptions.CustomException;
import prj1.models.response.ResponseData;
import prj1.services.DetailPromotionService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/detailPromotion")
@Slf4j
@RequiredArgsConstructor
public class DetailPromotionController {

    private final DetailPromotionService detailPromotionService;

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ResponseData<?>>> deletePromotion(
            ServerHttpRequest request,
            @PathVariable("id") String id
    ) {
        ResponseData<Object> response =
                new ResponseData<>(request);
        HttpStatus statusResponse;
        try {
            statusResponse = HttpStatus.OK;
            detailPromotionService.deleteDetailPromotion(id);
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
}

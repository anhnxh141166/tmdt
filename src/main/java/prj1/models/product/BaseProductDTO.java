package prj1.models.product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BaseProductDTO {
    private String id;

    private String name;

    private String description;

    private String warrantyPeriod;

    private Long weight;

    private Integer dimensionUnit;

    private Long height;

    private Long width;

    private Long length;

    private Boolean isCodOpen;

    private Integer status;

    @NotNull
    private String brandId;

    @NotNull
    private String categoryId;

    @NotNull
    private String shopId;
}

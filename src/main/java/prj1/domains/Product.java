package prj1.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import prj1.models.product.PostProductDTO;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document("Product")
public class Product {
    @Id
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

    private String brandId;

    private Integer status = 0;

    private String categoryId;

    private String shopId;

    public Product(PostProductDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.warrantyPeriod = dto.getWarrantyPeriod();
        this.weight = dto.getWeight();
        this.dimensionUnit = dto.getDimensionUnit();
        this.height = dto.getHeight();
        this.width = dto.getWidth();
        this.length = dto.getLength();
        this.isCodOpen = dto.getIsCodOpen();
    }

    public Product update(PostProductDTO dto) {
        this.id = dto.getId();

        this.name = dto.getName();
        this.description = dto.getDescription();
        this.warrantyPeriod = dto.getWarrantyPeriod();
        this.weight = dto.getWeight();
        this.dimensionUnit = dto.getDimensionUnit();
        this.height = dto.getHeight();
        this.width = dto.getWidth();
        this.length = dto.getLength();
        this.isCodOpen = dto.getIsCodOpen();

        return this;
    }
}

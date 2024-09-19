package prj1.models.product;

import lombok.Data;
import prj1.domains.SaleProperty;

@Data
public class GetSalePropertyDTO {
    private String id;
    private String name;
    private String valueId;
    private String valueName;
    private String groupProductDetailId;

    public GetSalePropertyDTO(SaleProperty saleProperty) {
        this.id = saleProperty.getVariationId();
        this.name = "";
        this.valueId = saleProperty.getId();
        this.valueName = saleProperty.getValue();
        this.groupProductDetailId = saleProperty.getGroupProductDetailId();
    }
}

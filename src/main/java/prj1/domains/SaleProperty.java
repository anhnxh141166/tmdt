package prj1.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import prj1.models.product.GetSalePropertyDTO;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document("SaleProperty")
public class SaleProperty {
    @Id
    private String id;
    private String variationId;
    private String groupProductDetailId;
    private String value;


    public SaleProperty update(GetSalePropertyDTO dto) {
        this.id = dto.getId();
        return this;
    }
}

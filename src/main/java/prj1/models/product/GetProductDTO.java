package prj1.models.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetProductDTO extends BaseProductDTO {
    private List<GetGroupProductDetailDTO> groupProductDetails;
    private List<GetSalePropertyDTO> saleProperties;
    private List<BaseVariationDTO> variations;
}

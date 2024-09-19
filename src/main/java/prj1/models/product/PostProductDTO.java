package prj1.models.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostProductDTO extends BaseProductDTO {
    private List<GetGroupProductDetailDTO> groupProductDetails;

    private List<BaseVariationDTO> variations;

    private List<PostSalePropertyDTO> saleProperties;
}

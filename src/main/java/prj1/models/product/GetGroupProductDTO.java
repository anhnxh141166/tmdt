package prj1.models.product;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetGroupProductDTO extends BaseGroupProductDTO {
    private List<BaseGroupProductDetailDTO> groupProductDetails;
}

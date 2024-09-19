package prj1.models.product;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostGroupProductDTO extends BaseGroupProductDTO {
    private List<String> groupProductDetailIDs;

}

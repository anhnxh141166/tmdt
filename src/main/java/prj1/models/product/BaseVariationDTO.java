package prj1.models.product;

import lombok.Data;

@Data
public class BaseVariationDTO {
  private String id;
  private String name;
  private boolean isCustom;
}

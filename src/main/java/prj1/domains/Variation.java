package prj1.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import prj1.models.product.BaseVariationDTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document("Variation")
public class Variation {

    @Id
    private String id;

    private String name;

    private Integer status = 1;

    private String mapID;

    private boolean isCustom;

    public Variation(BaseVariationDTO dto) {
        this.name = dto.getName();
        this.mapID = dto.getId();
        this.isCustom = dto.isCustom();
    }

    public Variation update(BaseVariationDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        return this;
    }
}

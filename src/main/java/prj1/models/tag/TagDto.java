package prj1.models.tag;

import lombok.Data;
import lombok.NoArgsConstructor;
import prj1.domains.Tag;

import java.util.List;

@Data
@NoArgsConstructor
public class TagDto {
    private String id;
    private String name;
    private List<TagDto> tagDtoList;

    public TagDto(Tag tag){
        this.id = tag.getId();
        this.name = tag.getName();
    }
}

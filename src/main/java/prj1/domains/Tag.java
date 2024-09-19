package prj1.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import prj1.models.tag.TagDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "Tag")
public class Tag {
    @Id
    private String id;

    private String name;

    @DBRef
    private List<Category> categoryList;

    public Tag(TagDto tagDto){
        this.id = tagDto.getId();
        this.name = tagDto.getName();
    }
}

package prj1.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "Brand")
public class Brand {
    @Id
    private String id;

    private String name;

    private boolean is_authorized;

    public void setIs_authorized(boolean is_authorized) {
        this.is_authorized = is_authorized;
    }
}


package prj1.domains;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("shop")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shop {
    @Id

    private String id;

    private  String userId;

    private String shopName;

    private String shopAddress;

    private String shopDescription;

}

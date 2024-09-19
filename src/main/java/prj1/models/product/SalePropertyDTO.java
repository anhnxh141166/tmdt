package prj1.models.product;

import lombok.Data;

import java.util.ArrayList;

@Data
public class SalePropertyDTO {
    private String name;
    private ArrayList<String> values;
}

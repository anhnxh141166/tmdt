package prj1.models.mapper;

import org.modelmapper.ModelMapper;

import prj1.domains.Product;
import prj1.models.product.GetProductDTO;

public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductMapper() {
        this.modelMapper = new ModelMapper();
    }

    public GetProductDTO convertToDTO(Product product) {
        return modelMapper.map(product, GetProductDTO.class);
    }
}

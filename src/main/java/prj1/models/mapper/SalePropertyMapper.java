package prj1.models.mapper;

import org.modelmapper.ModelMapper;
import prj1.domains.SaleProperty;
import prj1.models.product.GetSalePropertyDTO;
import prj1.models.product.PostSalePropertyDTO;

import java.util.List;


public class SalePropertyMapper {
    private final ModelMapper modelMapper;

    public SalePropertyMapper() {
        this.modelMapper = new ModelMapper();
    }

    public GetSalePropertyDTO convertToDTO(SaleProperty domain) {
        return modelMapper.map(domain, GetSalePropertyDTO.class);
    }

    public List<GetSalePropertyDTO> convertToDTOs(List<SaleProperty> domains) {
        return domains.stream().map(this::convertToDTO).toList();
    }

    public SaleProperty convertToEntity(PostSalePropertyDTO dto) {
        return modelMapper.map(dto, SaleProperty.class);
    }

    public List<SaleProperty> convertToEntities(List<PostSalePropertyDTO> dtoList) {
        return dtoList.stream().map(this::convertToEntity).toList();
    }
}

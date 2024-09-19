package prj1.models.mapper;

import org.modelmapper.ModelMapper;
import prj1.domains.Variation;
import prj1.models.product.BaseVariationDTO;

import java.util.List;

public class VariationMapper {
    private final ModelMapper modelMapper;

    public VariationMapper() {
        this.modelMapper = new ModelMapper();
    }

    public BaseVariationDTO convertToDTO(Variation domain) {
        return modelMapper.map(domain, BaseVariationDTO.class);
    }

    public List<BaseVariationDTO> convertToDTOs(List<Variation> domains) {
        return domains.stream().map(this::convertToDTO).toList();
    }

    public Variation convertToEntity(BaseVariationDTO dto) {
        return modelMapper.map(dto, Variation.class);
    }

    public List<Variation> convertToEntities(List<BaseVariationDTO> dtoList) {
        return dtoList.stream().map(item -> new VariationMapper().convertToEntity(item)).toList();
    }
}

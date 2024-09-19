package prj1.models.mapper;

import org.modelmapper.ModelMapper;
import prj1.domains.GroupProductDetail;
import prj1.models.product.GetGroupProductDetailDTO;

import java.util.List;

public class GroupProductDetailMapper {
    private final ModelMapper modelMapper;

    public GroupProductDetailMapper() {
        this.modelMapper = new ModelMapper();
    }

    public GetGroupProductDetailDTO convertToDTO(GroupProductDetail domain) {
        return modelMapper.map(domain, GetGroupProductDetailDTO.class);
    }

    public List<GetGroupProductDetailDTO> convertToDTOs(List<GroupProductDetail> domain) {
        return domain.stream().map(this::convertToDTO).toList();
    }

    public GroupProductDetail convertToEntity(GetGroupProductDetailDTO dto) {
        return modelMapper.map(dto, GroupProductDetail.class);
    }
}

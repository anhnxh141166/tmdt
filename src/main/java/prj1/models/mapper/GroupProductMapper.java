package prj1.models.mapper;

import org.modelmapper.ModelMapper;
import prj1.domains.GroupProduct;
import prj1.models.product.GetGroupProductDTO;

public class GroupProductMapper {
    private final ModelMapper modelMapper;

    public GroupProductMapper() {
        this.modelMapper = new ModelMapper();
    }

    public GetGroupProductDTO convertToDTO(GroupProduct domain) {
        return modelMapper.map(domain, GetGroupProductDTO.class);
    }

//    public GroupProduct convertToEntity(PostGroupProductDTO dto) {
//        return modelMapper.map(dto, GroupProduct.class);
//    }
}

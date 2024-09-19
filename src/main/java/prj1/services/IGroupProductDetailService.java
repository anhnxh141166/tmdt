package prj1.services;

import jakarta.validation.constraints.NotNull;
import prj1.domains.GroupProductDetail;
import prj1.models.product.BaseGroupProductDetailDTO;
import prj1.models.product.GetGroupProductDetailDTO;

import java.util.List;

public interface IGroupProductDetailService {
    String create(GetGroupProductDetailDTO dto);
    BaseGroupProductDetailDTO findById(@NotNull String id);
    void update(GroupProductDetail groupProductDetail, GetGroupProductDetailDTO dto);
    void delete(@NotNull String id);



    List<GroupProductDetail> createAll(List<GetGroupProductDetailDTO> listDTO, @NotNull String productId);
    List<GetGroupProductDetailDTO> productPromotionPrice(List<GroupProductDetail> groupProductDetails);


    List<GroupProductDetail> saveAll(List<GroupProductDetail> list);
    GroupProductDetail save(@NotNull GroupProductDetail groupProductDetail);
    GroupProductDetail getById(@NotNull String id);
    List<GroupProductDetail> getByIds(List<String> ids);
    List<GroupProductDetail> getByProductIdAndStatus(@NotNull String id, Integer status);
    List<GroupProductDetail> getByProductId(@NotNull String id);
    GroupProductDetail getInList(List<GroupProductDetail> list, @NotNull String id) ;
}

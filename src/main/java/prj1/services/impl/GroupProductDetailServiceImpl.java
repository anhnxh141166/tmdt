package prj1.services.impl;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prj1.domains.DetailPromotion;
import prj1.domains.GroupProduct;
import prj1.domains.GroupProductDetail;
import prj1.exceptions.CustomException;
import prj1.exceptions.ExceptionUtils;
import prj1.models.mapper.GroupProductDetailMapper;
import prj1.models.product.GetGroupProductDetailDTO;
import prj1.repositories.GroupProductDetailRepository;
import prj1.repositories.GroupProductRepository;
import prj1.services.DetailPromotionService;
import prj1.services.IGroupProductDetailService;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class GroupProductDetailServiceImpl implements IGroupProductDetailService {
    private final GroupProductDetailRepository groupProductDetailRepository;
    private final GroupProductRepository groupProductRepository;

    private final DetailPromotionService detailPromotionService;

    private final static Integer inactiveStatus = 0;

    @Override
    public String create(GetGroupProductDetailDTO dto) {
        GroupProductDetail newGroupProductDetail = groupProductDetailRepository.save(new GroupProductDetail(dto));
        return newGroupProductDetail.getId();
    }

    @Override
    public GetGroupProductDetailDTO findById(@NotNull String id) {
        return new GroupProductDetailMapper().convertToDTO(groupProductDetailRepository.findById(id).orElse(null));
    }

    @Override
    public void update(GroupProductDetail groupProductDetail, GetGroupProductDetailDTO dto) {
        groupProductDetail.setQuantity(dto.getQuantity());
        groupProductDetail.setBasePrice(dto.getBasePrice());
        groupProductDetail.setDiscountPrice(dto.getDiscountPrice());
        groupProductDetail.setSku(dto.getSku());
    }

    @Override
    public void delete(@NotNull String id) {
        GroupProductDetail groupProductDetail = getById(id);
        groupProductDetail.setStatus(inactiveStatus);
        save(groupProductDetail);
    }

    @Override
    public List<GroupProductDetail> createAll(List<GetGroupProductDetailDTO> listDTO, @NotNull String productID) {
        List<GroupProductDetail> groupProductDetails = listDTO.stream().map(item -> new GroupProductDetail(item, productID)).toList();
        return groupProductDetailRepository.saveAll(groupProductDetails);
    }


   @Override
    public List<GetGroupProductDetailDTO> productPromotionPrice(List<GroupProductDetail> groupProductDetails) {
        List<GroupProduct> groupProductList = groupProductRepository.findAllByGroupProductDetailsIn(groupProductDetails);

        groupProductList.forEach(groupProduct -> {
            List<DetailPromotion> activeDetailPromotionList = detailPromotionService
                    .getDetailPromotionByIdGroupProduct(groupProduct.getId());

            groupProductDetails.forEach(groupProductDetail -> {
                Double basePrice = groupProductDetail.getBasePrice();

                activeDetailPromotionList.forEach(detailPromotion -> {
                    if (detailPromotion.getPromotion().getTools() == 2
                            && detailPromotion.getPromotion().getType() == 2) {
                        groupProductDetail.setDiscountPrice(basePrice - detailPromotion.getDiscount());
                    } else if (detailPromotion.getPromotion().getTools() == 2
                            && detailPromotion.getPromotion().getType() == 1) {
                        groupProductDetail.setDiscountPrice(basePrice * (100 - detailPromotion.getPercent()) / 100);
                    }
                });

            });
        });

        return new GroupProductDetailMapper().convertToDTOs(groupProductDetails);
    }

    @Override
    public List<GroupProductDetail> saveAll(List<GroupProductDetail> list) {
        return groupProductDetailRepository.saveAll(list);
    }

    @Override
    public GroupProductDetail save(@NotNull GroupProductDetail groupProductDetail) {
        return groupProductDetailRepository.save(groupProductDetail);
    }

    @Override
    public GroupProductDetail getById(@NotNull String id) {
        GroupProductDetail groupProductDetail = groupProductDetailRepository.findById(id).orElse(null);
        if (groupProductDetail == null) {
            throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
        }
        return groupProductDetail;
    }

    @Override
    public List<GroupProductDetail> getByIds(List<String> ids) {
        return groupProductDetailRepository.findAllById(ids);
    }

    @Override
    public List<GroupProductDetail> getByProductIdAndStatus(String id, Integer status) {
        return groupProductDetailRepository.findAllByProductIdAndStatus(id, status);
    }

    @Override
    public List<GroupProductDetail> getByProductId(String id) {
        return groupProductDetailRepository.findAllByProductId(id);
    }

    @Override
    public GroupProductDetail getInList(List<GroupProductDetail> list, @NotNull String id) {
        for (GroupProductDetail obj : list) {
            if (obj.getId().equals(id)) {
                return obj;
            }
        }
        return null;
    }
}

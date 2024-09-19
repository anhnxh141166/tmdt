package prj1.services.impl;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prj1.domains.GroupProduct;
import prj1.domains.GroupProductDetail;
import prj1.exceptions.CustomException;
import prj1.exceptions.ExceptionUtils;
import prj1.models.mapper.GroupProductMapper;
import prj1.models.product.GetGroupProductDTO;
import prj1.models.product.PostGroupProductDTO;
import prj1.repositories.GroupProductRepository;
import prj1.services.IGroupProductDetailService;
import prj1.services.IGroupProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
public class GroupProductServiceImpl implements IGroupProductService {
    private final GroupProductRepository groupProductRepository;

    private final IGroupProductDetailService iGroupProductDetailService;

    private final static Integer activeStatus = 1;
    private final static Integer inactiveStatus = 0;

    @Override
    public String create(PostGroupProductDTO dto) {
        GroupProduct groupProduct = new GroupProduct(dto);
        List<String> groupProductDetailIdList = dto.getGroupProductDetailIDs();
        GroupProduct savedGroupProduct = create(groupProductDetailIdList, groupProduct);
        return savedGroupProduct.getId();
    }

    @Override
    public GetGroupProductDTO findById(@NotNull String id) {
        GroupProduct groupProduct = getById(id);
        return new GroupProductMapper().convertToDTO(groupProduct);
    }

    @Override
    public GetGroupProductDTO update(@NotNull String id, PostGroupProductDTO dto) {
        GroupProduct groupProduct = groupProductRepository.findById(id).orElse(null);
        if (groupProduct != null) {
            groupProduct.update(dto);
            List<String> newGroupProductDetailIdList = dto.getGroupProductDetailIDs();
            List<String> oldGroupProductDetailIdList = new ArrayList<>();
            List<GroupProductDetail> groupProductDetailList = groupProduct.getGroupProductDetails();

            for (GroupProductDetail gpd : groupProductDetailList) {
                oldGroupProductDetailIdList.add(gpd.getId());
            }

            // compare 2 list, if different, create brand new groupProduct
            if (!newGroupProductDetailIdList.equals(oldGroupProductDetailIdList)) {
                GroupProduct newGroupProduct = new GroupProduct(dto);

                GroupProduct savedGroupProduct = create(newGroupProductDetailIdList, newGroupProduct);

                groupProduct.setStatus(inactiveStatus);
                groupProductRepository.save(groupProduct);

                return new GroupProductMapper().convertToDTO(savedGroupProduct);
            }

            GroupProduct newGroupProduct = groupProductRepository.save(groupProduct);

            return new GroupProductMapper().convertToDTO(newGroupProduct);

        }

        return null;
    }

    private GroupProduct create(List<String> groupProductDetailIdList, GroupProduct groupProduct) {
        List<GroupProductDetail> groupProductDetails = iGroupProductDetailService.getByIds(groupProductDetailIdList);

        List<GroupProductDetail> newGroupProductDetailList = groupProductDetails.stream().filter(item -> !Objects.equals(item
                .getStatus(), inactiveStatus)).toList();
        groupProduct.setGroupProductDetails(newGroupProductDetailList);

        GroupProduct savedGroupProduct = groupProductRepository.save(groupProduct);

        for (GroupProductDetail gpd : newGroupProductDetailList) {
            gpd.addGroupProduct(savedGroupProduct);
        }
        iGroupProductDetailService.saveAll(newGroupProductDetailList);

        return savedGroupProduct;
    }

    @Override
    public void delete(@NotNull String id) {
        if (groupProductRepository.existsById(id)) {
            GroupProduct groupProduct = groupProductRepository.findById(id).orElse(null);
            if (groupProduct != null) {
                groupProduct.setStatus(inactiveStatus);
                groupProductRepository.save(groupProduct);
            }
        }
    }

    @Override
    public GroupProduct getById(@NotNull String id) {
        GroupProduct groupProduct = groupProductRepository.findById(id).orElse(null);
        if (groupProduct == null) {
            throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
        }
        return groupProduct;
    }
}

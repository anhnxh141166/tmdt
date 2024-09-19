package prj1.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prj1.domains.GroupProductDetail;
import prj1.domains.SaleProperty;
import prj1.domains.Variation;
import prj1.models.mapper.SalePropertyMapper;
import prj1.models.product.PostSalePropertyDTO;
import prj1.repositories.SalePropertyRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class SalePropertyServiceImpl {
    private final SalePropertyRepository salePropertyRepository;

    List<SaleProperty> map(List<PostSalePropertyDTO> salePropertyDTOS, List<GroupProductDetail> groupProductDetails, List<Variation> variations) {
         Map<String, GroupProductDetail> groupProductDetailMap = new HashMap<>();
         Map<String, Variation> variationMap = new HashMap<>();

        for (GroupProductDetail groupProductDetail : groupProductDetails) {
            groupProductDetailMap.put(groupProductDetail.getMapID(), groupProductDetail);
        }

        for (Variation variation : variations) {
            variationMap.put(variation.getMapID(), variation);
        }

        List<SaleProperty> saleProperties = new SalePropertyMapper().convertToEntities(salePropertyDTOS);

        saleProperties.forEach(item -> {
            if(item.getId() == null) {
                item.setGroupProductDetailId(groupProductDetailMap.get(item.getGroupProductDetailId()).getId());
                item.setVariationId(variationMap.get(item.getVariationId()).getId());
            }
        });
        return saleProperties;
    }

    void saveAll(List<SaleProperty> list){
        salePropertyRepository.saveAll(list);
    }

    List<SaleProperty> getByGroupProductDetailIDs(Set<String> ids) {
        return salePropertyRepository.findAllByGroupProductDetailIdIsIn(ids);
    }

}

package prj1.services.impl;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prj1.domains.*;
import prj1.exceptions.CustomException;
import prj1.exceptions.ExceptionUtils;
import prj1.models.mapper.ProductMapper;
import prj1.models.mapper.VariationMapper;
import prj1.models.product.*;
import prj1.repositories.BrandRepository;
import prj1.repositories.CategoryRepository;
import prj1.repositories.ProductRepository;
import prj1.repositories.ShopRepository;
import prj1.services.IGroupProductDetailService;
import prj1.services.IProductService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ShopRepository shopRepository;

    private final IGroupProductDetailService iGroupProductDetailService;
    private final VariationServiceImpl variationService;
    private final SalePropertyServiceImpl salePropertyService;
    private final static Integer activeStatus = 1;
    private final static Integer inactiveStatus = 0;

    @Override
    public Page<GetProductDTO> list(String name, Integer status, ObjectId id, Pageable pageable) {
        return productRepository.findAll(name, status, id, pageable)
                .map(item -> new ProductMapper().convertToDTO(item));
    }

    @Override
    public Product getById(@NotNull String id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
        }
        return product;
    }

    @Override
    public String create(PostProductDTO dto) {
        Product newProduct = new Product(dto);

        // Category
        String categoryId = dto.getCategoryId();

        Category category = categoryRepository.findById(categoryId).orElse(null);

        if (category == null) {
            throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
        }

        newProduct.setCategoryId(categoryId);

        // Brand
        String brandId = dto.getBrandId();

        Brand brand = brandRepository.findById(brandId).orElse(null);

        if (brand == null) {
            throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
        }

        newProduct.setBrandId(brandId);

        // Shop
        String shopId = dto.getShopId();

        Shop shop = shopRepository.findById(shopId).orElse(null);

        if (shop == null) {
            throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
        }

        newProduct.setShopId(shopId);

        Product savedProduct = productRepository.save(newProduct);
        String newProductId = savedProduct.getId();

        // Variation
        List<Variation> savedVariations = variationService.createAll(dto.getVariations());

        // GPD
        List<GetGroupProductDetailDTO> groupProductDetails = dto.getGroupProductDetails();
        List<GroupProductDetail> savedGroupProductDetails = iGroupProductDetailService.createAll(groupProductDetails,
                newProductId);

        // Sale Property
        List<PostSalePropertyDTO> salePropertyDTOList = dto.getSaleProperties();
        List<SaleProperty> saleProperties = salePropertyService.map(salePropertyDTOList, savedGroupProductDetails,
                savedVariations);
        salePropertyService.saveAll(saleProperties);

        return newProductId;
    }

    @Override
    public GetProductDTO findById(@NotNull String id) {
        Product product = getById(id);

        List<GroupProductDetail> groupProductDetails = iGroupProductDetailService.getByProductIdAndStatus(id,
                activeStatus);
        Set<String> groupProductDetailIds = groupProductDetails.stream().map(GroupProductDetail::getId)
                .collect(Collectors.toSet());

        List<SaleProperty> saleProperties = salePropertyService.getByGroupProductDetailIDs(groupProductDetailIds);

        Set<String> variationIds = saleProperties.stream().map(SaleProperty::getVariationId)
                .collect(Collectors.toSet());
        List<Variation> variations = variationService.getByIds(variationIds);

        List<GetSalePropertyDTO> salePropertyDTOS = saleProperties.stream().map(GetSalePropertyDTO::new).toList();
        for (GetSalePropertyDTO dto : salePropertyDTOS) {
            variations.stream().filter(item -> Objects.equals(item.getId(), dto.getId())).findFirst()
                    .ifPresent(variation -> dto.setName(variation.getName()));
        }

        GetProductDTO productDTO = new ProductMapper().convertToDTO(product);

        List<GetGroupProductDetailDTO> getGroupProductDetailDTOS = iGroupProductDetailService
                .productPromotionPrice(groupProductDetails);

        productDTO.setGroupProductDetails(getGroupProductDetailDTOS);
        productDTO.setSaleProperties(salePropertyDTOS);
        productDTO.setVariations(new VariationMapper().convertToDTOs(variations));
        return productDTO;
    }

    @Override
    public void update(@NotNull String productID, PostProductDTO dto) {
        Product oldProduct = getById(productID);
        Product newProduct = new Product().update(dto);

        // Shop
        String newShopId = dto.getShopId();
        String oldShopId = oldProduct.getShopId();

        if (!newShopId.equals(oldShopId)) {
            Shop newShop = shopRepository.findById(newShopId).orElse(null);
            if (newShop == null) {
                throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
            }
            newProduct.setShopId(newShopId);
        } else {
            newProduct.setShopId(oldShopId);
        }

        // Brand
        String newBrandId = dto.getBrandId();
        String oldBrandId = oldProduct.getBrandId();

        if (!newBrandId.equals(oldBrandId)) {
            Brand newBrand = brandRepository.findById(newBrandId).orElse(null);
            if (newBrand == null) {
                throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
            }
            newProduct.setBrandId(newBrandId);
        } else {
            newProduct.setBrandId(oldBrandId);
        }

        // Category
        String newCategoryId = dto.getCategoryId();
        String oldCategoryId = oldProduct.getCategoryId();

        if (!newCategoryId.equals(oldCategoryId)) {
            Category newCategory = categoryRepository.findById(newCategoryId).orElse(null);

            if (newCategory == null) {
                throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
            }

            newProduct.setCategoryId(newCategoryId);
        } else {
            newProduct.setCategoryId(oldCategoryId);
        }

        // GPD
        // Tìm các GroupProductDetail cần xóa
        List<GetGroupProductDetailDTO> getGroupProductDetailDTOS = dto.getGroupProductDetails();
        if (getGroupProductDetailDTOS.isEmpty()) {
            throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
        }

        Set<String> newGpdIDSet = getGroupProductDetailDTOS.stream().map(GetGroupProductDetailDTO::getId)
                .collect(Collectors.toSet());

        List<GroupProductDetail> oldGroupProductDetails = iGroupProductDetailService.getByProductIdAndStatus(productID,
                activeStatus);

        if (oldGroupProductDetails.isEmpty()) {
            throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
        }

        List<String> deleteGpdIDs = oldGroupProductDetails.stream()
                .map(GroupProductDetail::getId)
                .filter(id -> !newGpdIDSet.contains(id))
                .toList();

        List<GroupProductDetail> groupProductDetailList = new ArrayList<>();
        GroupProductDetail groupProductDetail;

        for (GetGroupProductDetailDTO getGroupProductDetailDTO : getGroupProductDetailDTOS) {
            if (getGroupProductDetailDTO.getId().startsWith("GPD")) {
                groupProductDetail = new GroupProductDetail(getGroupProductDetailDTO);
            } else {
                groupProductDetail = iGroupProductDetailService.getInList(oldGroupProductDetails,
                        getGroupProductDetailDTO.getId());
                iGroupProductDetailService.update(groupProductDetail, getGroupProductDetailDTO);
            }
            groupProductDetail.setProductId(productID);
            groupProductDetailList.add(groupProductDetail);
        }

        List<GroupProductDetail> deleteGroupProductDetails = new ArrayList<>();

        oldGroupProductDetails.forEach(item -> {
            if (deleteGpdIDs.contains(item.getId())) {
                item.setStatus(inactiveStatus);
                deleteGroupProductDetails.add(item);
            }
        });

        groupProductDetailList.addAll(deleteGroupProductDetails);

        groupProductDetailList.forEach(item -> {
            String id = item.getId();
            if (id != null) {
                item.setMapID(id);
            }
        });

        List<GroupProductDetail> savedGroupProductDetails = iGroupProductDetailService.saveAll(groupProductDetailList);

        // Variation
        List<Variation> savedVariations = variationService.createAll(dto.getVariations());

        // Sale Property
        List<PostSalePropertyDTO> salePropertyDTOList = dto.getSaleProperties();
        if (salePropertyDTOList.isEmpty()) {
            throw new CustomException(ExceptionUtils.ITEM_NOT_EXIST);
        }

        List<SaleProperty> saleProperties = salePropertyService.map(salePropertyDTOList, savedGroupProductDetails,
                savedVariations);
        salePropertyService.saveAll(saleProperties);

        productRepository.save(newProduct);
    }

    @Override
    public void delete(@NotNull String id) {
        Product product = getById(id);
        product.setStatus(inactiveStatus);
        List<GroupProductDetail> groupProductDetailList = iGroupProductDetailService.getByProductIdAndStatus(id,
                activeStatus);
        groupProductDetailList.forEach(item -> item.setStatus(inactiveStatus));
        iGroupProductDetailService.saveAll(groupProductDetailList);
        productRepository.save(product);
    }
}

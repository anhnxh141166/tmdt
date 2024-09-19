package prj1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prj1.domains.Brand;
import prj1.repositories.BrandRepository;
import prj1.services.BrandService;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand saveBrand(Brand brand) {
        if (isNameUnique(brand)) {
            return brandRepository.save(brand);
        } else {
            return null;
        }
    }

    private boolean isNameUnique(Brand brand) {
        Optional<Brand> existingBrand = brandRepository.findByName(brand.getName());
        return existingBrand.isEmpty() || existingBrand.get().getId().equals(brand.getId());
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> getBrandById(String id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand updateBrand(String id, Brand updatedBrand) {
        Optional<Brand> existingBrand = brandRepository.findById(id);

        if (existingBrand.isPresent()) {
            Brand brandToUpdate = existingBrand.get();
            brandToUpdate.setName(updatedBrand.getName());
            brandToUpdate.setIs_authorized(updatedBrand.is_authorized());

            return brandRepository.save(brandToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public List<Brand> searchBrands(String keyword) {
        return brandRepository.findByNameContainingIgnoreCase(keyword);
    }
}

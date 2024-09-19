package prj1.services;

import prj1.domains.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    Brand saveBrand(Brand brand);

    List<Brand> getAllBrands();

    Optional<Brand> getBrandById(String id);

    Brand updateBrand(String id, Brand updatedBrand);

    List<Brand> searchBrands(String keyword);

}

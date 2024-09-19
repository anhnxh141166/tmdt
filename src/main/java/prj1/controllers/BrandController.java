package prj1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prj1.domains.Brand;
import prj1.services.impl.BrandServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandServiceImpl brandService;

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands()
    {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable String id) {
        return brandService.getBrandById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Brand> saveBrand(@RequestBody Brand brand) {
        return ResponseEntity.ok(brandService.saveBrand(brand));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable String id, @RequestBody Brand updatedBrand) {
        Brand result = brandService.updateBrand(id, updatedBrand);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Brand>> searchBrands(@RequestParam String keyword) {
        return ResponseEntity.ok(brandService.searchBrands(keyword));
    }




}


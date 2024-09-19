package prj1.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prj1.domains.Variation;
import prj1.models.product.BaseVariationDTO;
import prj1.repositories.VariationRepository;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class VariationServiceImpl {
    private final VariationRepository variationRepository;

    List<String> getAllVariationNames() {
        return variationRepository.findAll().stream().map(Variation::getName).toList();
    }

    List<Variation> createAll(List<BaseVariationDTO> list) {
        List<Variation> variations = list.stream().map(Variation::new).toList();
        List<String> variationNames = getAllVariationNames();
        return variationRepository.saveAll(variations.stream().filter(item -> (!variationNames.contains(item.getName()))).toList());
    }

    List<Variation> getByIds(Set<String> ids) {
        return variationRepository.findAllByIdIn(ids);
    }
}

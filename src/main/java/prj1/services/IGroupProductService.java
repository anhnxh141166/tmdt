package prj1.services;

import jakarta.validation.constraints.NotNull;
import prj1.domains.GroupProduct;
import prj1.models.product.GetGroupProductDTO;
import prj1.models.product.PostGroupProductDTO;

public interface IGroupProductService {

    String create(PostGroupProductDTO dto);

    GetGroupProductDTO findById(@NotNull String id);

    GetGroupProductDTO update(@NotNull String id, PostGroupProductDTO dto);

    void delete(@NotNull String id);

    GroupProduct getById(@NotNull String id);

}

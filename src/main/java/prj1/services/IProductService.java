package prj1.services;

import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import prj1.domains.Product;
import prj1.models.product.GetProductDTO;
import prj1.models.product.PostProductDTO;

import java.util.List;

public interface IProductService {
    Page<GetProductDTO> list(String name, Integer status, ObjectId id, Pageable pageable);

    String create(PostProductDTO product);

    GetProductDTO findById(@NotNull String id);

    Product getById(@NotNull String id);

    void update(@NotNull String id, PostProductDTO product);

    void delete(@NotNull String id);
}

package prj1.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagingResponse<T> {

  private long count;
  private List<T> models;

  private PagingResponse(Page<T> page) {
    this.count = page.getTotalElements();
    this.models = page.getContent();
  }

  public static <T> PagingResponse<T> of(Page<T> page) {
    return new PagingResponse<>(page);
  }
}

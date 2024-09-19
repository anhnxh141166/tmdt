package prj1.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class DataResponseTemplate<T> {

    private HashMap<String,Object> dropLists;

    private T data ;

  public DataResponseTemplate(HashMap<String, Object> dropLists, T data) {
      if(MapUtils.isNotEmpty(dropLists)){
          this.dropLists = dropLists;

      }
        this.data = data;
    }

    public DataResponseTemplate(T data){
      this.data = data;
    }

}

package prj1.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component("qHelper")
public class QueryHelper {
    /**
     * Removes blank values from the given map
     */
    public Map<String, Object> noBlanks(Map<String, Object> params) {
        Map<String, Object> filteredMap = new HashMap<>(params);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() == null || StringUtils.isBlank(entry.getValue().toString())) {
                filteredMap.remove(entry.getKey());
            }
        }
        return filteredMap;
    }
}


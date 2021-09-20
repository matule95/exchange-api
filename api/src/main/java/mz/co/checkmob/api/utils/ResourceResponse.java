package mz.co.checkmob.api.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResourceResponse {
    public static Map<String, Object> success(Object data, String url) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("url", url);
        return response;
    }

    public static Map<String, Object> error(String message, String code) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("error", message);
        return response;
    }
}

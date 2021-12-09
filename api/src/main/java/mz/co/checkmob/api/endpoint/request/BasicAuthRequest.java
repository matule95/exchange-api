package mz.co.checkmob.api.endpoint.request;

import mz.co.checkmob.api.authorization.domain.AuthorizationType;
import mz.co.checkmob.api.connections.domain.RequestType;
import mz.co.checkmob.api.core.utils.ApiService;
import mz.co.checkmob.api.endpoint.domain.Endpoint;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BasicAuthRequest implements Request {
    private Map<String, Object> authentication;

    @Override
    public Map<String, Object> authenticate(RequestType type, String URL, Endpoint endpoint) {
        if (endpoint.getAuthenticationType().equals(AuthorizationType.BASIC_AUTH)) {
            Map<String, Object> dataReader = new HashMap<>(endpoint.getDataReader());
            String auth = "authURL";
            String authURL = String.valueOf(dataReader.getOrDefault(auth, ""));
            String prefix = "prefix";
            String responseKey = "response";
            String headerValue = "header";
            dataReader.remove(auth);
            dataReader.remove(prefix);
            dataReader.remove(responseKey);
            dataReader.remove(headerValue);
            authentication = (Map<String, Object>) ApiService.post(authURL, dataReader, Map.class);
            return execute(type, URL, endpoint);
        }
        return new BearerTokenRequest().authenticate(type, URL, endpoint);
    }

    @Override
    public Map<String, Object> execute(RequestType type, String URL, Endpoint endpoint) {
        Map<String, Object> result = new LinkedHashMap<>();
        StringBuilder headerValue = new StringBuilder();
        String prefix = endpoint.getDataReader().get("prefix").toString();
        String response = endpoint.getDataReader().get("response").toString();
        String token = authentication.get(response).toString();
        if (prefix.isEmpty()) {
            headerValue.append(token);
        } else {
            headerValue.append(prefix);
            headerValue.append(" ");
            headerValue.append(token);
        }
        if (type.equals(RequestType.GET)) {
            result = ApiService.get(URL, endpoint.getDataReader().get("header").toString(), headerValue.toString(), Map.class);
        }
        return result;

    }
}

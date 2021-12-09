package mz.co.checkmob.api.endpoint.authentication;

import mz.co.checkmob.api.authorization.domain.AuthorizationType;
import mz.co.checkmob.api.core.utils.ApiService;
import mz.co.checkmob.api.endpoint.domain.Endpoint;

import java.util.HashMap;
import java.util.Map;

public class BearerTokenAuthentication implements Authentication{
    @Override
    public Map<String, Object> authenticate(Endpoint endpoint) {
        if (endpoint.getAuthenticationType().equals(AuthorizationType.BEARER_TOKEN)){
            Map<String, Object> dataReader =  new HashMap<>(endpoint.getDataReader());
            String auth = "authURL";
            String authURL = String.valueOf(dataReader.getOrDefault(auth,""));
            String prefix = "prefix";
            String responseKey = "response";
            String headerValue = "header";
            dataReader.remove(auth);
            dataReader.remove(prefix);
            dataReader.remove(responseKey);
            dataReader.remove(headerValue);
            return (Map<String, Object>) ApiService.post(authURL,dataReader,Map.class);
        }
        return new ApiKeyAuthentication().authenticate(endpoint);
    }
}

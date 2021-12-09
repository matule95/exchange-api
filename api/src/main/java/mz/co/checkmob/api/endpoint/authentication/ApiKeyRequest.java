package mz.co.checkmob.api.endpoint.authentication;

import mz.co.checkmob.api.authorization.domain.AuthorizationType;
import mz.co.checkmob.api.connections.domain.RequestType;
import mz.co.checkmob.api.endpoint.domain.Endpoint;

import java.util.Map;

public class ApiKeyRequest implements Authentication{
    @Override
    public Map<String, Object> authenticate(RequestType type, String URL, Endpoint endpoint) {
        if (endpoint.getAuthenticationType().equals(AuthorizationType.API_KEY)){
            return execute(type,URL,endpoint);
        }
        return new OAuthTwoAuthentication().authenticate(type, URL, endpoint);
    }

    @Override
    public Map<String, Object> execute(RequestType type, String URL, Endpoint endpoint) {
        return null;
    }
}

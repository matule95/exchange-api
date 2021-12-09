package mz.co.checkmob.api.endpoint.authentication;

import mz.co.checkmob.api.authorization.domain.AuthorizationType;
import mz.co.checkmob.api.endpoint.domain.Endpoint;

import java.util.Map;

public class ApiKeyAuthentication implements Authentication{
    @Override
    public Map<String, Object> authenticate(Endpoint endpoint) {
        if (endpoint.getAuthenticationType().equals(AuthorizationType.API_KEY)){
            return null;
        }
        return new OAuthTwoAuthentication().authenticate(endpoint);
    }
}

package mz.co.checkmob.api.endpoint.request;

import mz.co.checkmob.api.authorization.domain.AuthorizationType;
import mz.co.checkmob.api.connections.domain.RequestType;
import mz.co.checkmob.api.endpoint.domain.Endpoint;

import java.util.Map;

public class OAuthTwoRequest implements Request {
    @Override
    public Map<String, Object> authenticate(RequestType type, String URL, Endpoint endpoint) {
        if (endpoint.getAuthenticationType().equals(AuthorizationType.OAUTH2)){
            return execute(type, URL, endpoint);
        }
        return null;
    }

    @Override
    public Map<String, Object> execute(RequestType type, String URL, Endpoint endpoint) {
        return null;
    }
}

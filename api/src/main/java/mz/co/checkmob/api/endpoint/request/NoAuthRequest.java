package mz.co.checkmob.api.endpoint.request;

import mz.co.checkmob.api.authorization.domain.AuthorizationType;
import mz.co.checkmob.api.connections.domain.RequestType;
import mz.co.checkmob.api.endpoint.domain.Endpoint;

import java.util.Map;

public class NoAuthRequest implements Request {

    @Override
    public Map<String, Object> authenticate(RequestType type, String URL, Endpoint endpoint) {
        if (endpoint.getAuthenticationType().equals(AuthorizationType.NO_AUTH)){
            return execute(type,URL,endpoint);
        }
        return new BasicAuthRequest().authenticate(type,URL,endpoint);
    }

    @Override
    public Map<String, Object> execute(RequestType type, String URL, Endpoint endpoint) {
        return null;
    }
}

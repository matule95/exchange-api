package mz.co.checkmob.api.endpoint.authentication;

import mz.co.checkmob.api.connections.domain.RequestType;
import mz.co.checkmob.api.endpoint.domain.Endpoint;

import java.util.Map;

public interface Authentication {
    Map<String, Object> authenticate(RequestType type, String URL,Endpoint endpoint);
    Map<String, Object> execute(RequestType type, String URL, Endpoint endpoint);
}

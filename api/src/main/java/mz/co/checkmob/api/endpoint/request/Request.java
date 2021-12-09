package mz.co.checkmob.api.endpoint.request;

import mz.co.checkmob.api.connections.domain.RequestType;
import mz.co.checkmob.api.endpoint.domain.Endpoint;

import java.util.Map;

public interface Request {
    Map<String, Object> authenticate(RequestType type, String URL,Endpoint endpoint);
    Map<String, Object> execute(RequestType type, String URL, Endpoint endpoint);
}

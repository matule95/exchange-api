package mz.co.checkmob.api.endpoint.authentication;

import mz.co.checkmob.api.endpoint.domain.Endpoint;

import java.util.Map;

public interface Authentication {
    Map<String, Object> authenticate(Endpoint endpoint);
}

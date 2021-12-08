package mz.co.checkmob.api.core.utils;

import mz.co.checkmob.api.connections.domain.RequestType;

public enum API {

    NO_AUTH {
        @Override
        public <T> T request(String url, RequestType requestType, Object params, Class<T> returnClassType) {
            return returnClassType.cast(requestType.execute(url,params, returnClassType));
        }
    };

    public abstract <T> T request(String url,RequestType requestType, Object params, Class<T> returnClassType);

}

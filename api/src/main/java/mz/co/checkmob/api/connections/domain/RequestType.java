package mz.co.checkmob.api.connections.domain;

import mz.co.checkmob.api.core.utils.ApiService;

public enum RequestType {
    GET{
        @Override
        public <T> T execute(String url, Object params, Class<T> returnClassType) {
            return returnClassType.cast(ApiService.get(url,returnClassType));
        }
    },
    PUT{
        @Override
        public <T> T execute(String url, Object params, Class<T> returnClassType) {
            return returnClassType.cast(ApiService.put(url,params, returnClassType));
        }
    },
    POST{
        @Override
        public <T> T execute(String url, Object params, Class<T> returnClassType) {
            return returnClassType.cast(ApiService.post(url, params,returnClassType));
        }
    };

    public abstract <T> T execute(String url, Object params, Class<T> returnClassType);

}

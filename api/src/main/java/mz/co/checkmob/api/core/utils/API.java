package mz.co.checkmob.api.core.utils;

import mz.co.checkmob.api.connections.domain.RequestType;

public enum API {

    NO_AUTH {
        @Override
        public <T> T request(String url, RequestType requestType, Object params, Class<T> returnClassType) {
            if(requestType.equals(RequestType.POST)){
                returnClassType.cast(ApiService.post(url,params,returnClassType));
            }
            return null;
        }

        @Override
        public <T> T request(String url, RequestType requestType, Class<T> returnClassType){
            if(requestType.equals(RequestType.GET)){
                return returnClassType.cast(ApiService.get(url,returnClassType));
            }
            return null;
        }
    };

    public abstract <T> T request(String url,RequestType requestType, Object params, Class<T> returnClassType);
    public abstract <T> T request(String url, RequestType requestType, Class<T> returnClassType);

}

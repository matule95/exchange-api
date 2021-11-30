package mz.co.checkmob.api.core.utils;

import org.springframework.util.MultiValueMap;

public enum API {

    NO_AUTH {
        @Override
        public <T> T post(String url, MultiValueMap<String, Object> params, Class<T> returnClassType) {
            return returnClassType.cast(ApiService.post(url,params,returnClassType));
        }

        @Override
        public <T> T get(String url, Class<T> returnClassType){
            return returnClassType.cast(ApiService.get(url,returnClassType));
        }

    };

    public abstract <T> T post(String url,MultiValueMap<String, Object> params, Class<T> returnClassType);
    public abstract <T> T get(String url, Class<T> returnClassType);

}

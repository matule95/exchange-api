package mz.co.checkmob.api.authorization.domain;

import mz.co.checkmob.api.core.utils.ApiService;

import java.util.Map;

public enum AuthorizationType {
    NO_AUTH {
        @Override
        public <T> T authentication(Map<String, Object> dataReader) {
            return null;
        }
    }, API_KEY {
        @Override
        public <T> T authentication(Map<String, Object> dataReader) {
            return (T) dataReader;
        }
    }, BEARER_TOKEN {
        @Override
        public <T> T authentication(Map<String, Object> dataReader) {
            String auth = "authURL";
            String authURL = String.valueOf(dataReader.getOrDefault(auth,""));
            String prefix = "prefix";
            String responseKey = "response";
            String headerValue = "header";
            dataReader.remove(auth);
            dataReader.remove(prefix);
            dataReader.remove(responseKey);
            dataReader.remove(headerValue);
            Map<String,Object> map = (Map<String, Object>) ApiService.post(authURL,dataReader,Map.class);
            return (T) map;
        }
    }, BASIC_AUTH {
        @Override
        public <T> T authentication(Map<String, Object> dataReader) {
            String auth = "authURL";
            String authURL = String.valueOf(dataReader.getOrDefault(auth,""));
            String prefix = "prefix";
            String responseKey = "response";
            String headerValue = "header";
            dataReader.remove(auth);
            dataReader.remove(prefix);
            dataReader.remove(responseKey);
            dataReader.remove(headerValue);
            Map<String,Object> map = (Map<String, Object>) ApiService.post(authURL,dataReader,Map.class);
            return (T) map;
        }
    },OAUTH2{
        @Override
        public <T> T authentication(Map<String, Object> dataReader) {
            return null;
        }
    };
    public abstract <T> T authentication(Map<String,Object> dataReader);
}
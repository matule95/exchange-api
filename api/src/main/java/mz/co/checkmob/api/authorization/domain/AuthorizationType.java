package mz.co.checkmob.api.authorization.domain;

import mz.co.checkmob.api.core.utils.ApiService;

import java.util.Map;

public enum AuthorizationType {
    NO_AUTH {
        @Override
        public <T> T authentication(String authURL, Map<String, Object> dataReader) {
            return null;
        }
    }, API_KEY {
        @Override
        public <T> T authentication(String authURL, Map<String, Object> dataReader) {
            return null;
        }
    }, BEARER_TOKEN {
        @Override
        public <T> T authentication(String authURL, Map<String, Object> dataReader) {
            Map<String,Object> map = (Map<String, Object>) ApiService.post(authURL,dataReader,Object.class);
            return (T) map;
        }
    }, BASIC_AUTH {
        @Override
        public <T> T authentication(String authURL, Map<String, Object> dataReader) {
            Map<String,Object> map = (Map<String, Object>) ApiService.post(authURL,dataReader,Object.class);
            return (T) map;
        }
    },OAUTH2{
        @Override
        public <T> T authentication(String authURL, Map<String, Object> dataReader) {
            return null;
        }
    };
    public abstract <T> T authentication(String authURL,Map<String,Object> dataReader);
}
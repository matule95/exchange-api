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
            return null;
        }
    }, BASIC_AUTH {
        @Override
        public <T> T authentication(String authURL, Map<String, Object> dataReader) {
            StringBuilder requestBody = new StringBuilder("{\n");
            dataReader.entrySet().forEach(data ->{
                requestBody.append("\"");
                requestBody.append(data.getKey());
                requestBody.append("\"");
                requestBody.append(":");
                requestBody.append(data.getValue());
                requestBody.append(",\n");
            });
            requestBody.append("}");
            int index = requestBody.lastIndexOf(",");
            requestBody.replace(index,index+1,"");
            Map<String,Object> map = (Map<String, Object>) ApiService.post(authURL,requestBody.toString(),Object.class);
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
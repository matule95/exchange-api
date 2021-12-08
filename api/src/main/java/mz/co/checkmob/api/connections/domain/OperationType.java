package mz.co.checkmob.api.connections.domain;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum OperationType {

    UPPERCASE {
        @Override
        public void operate(Param param, Map<String, Object> map, Map<String, Object> params) {
            params.put(param.getToField()[0], map.get(param.getFromField()[0]).toString().toUpperCase());
        }
    },
    LOWERCASE {
        @Override
        public void operate(Param param, Map<String, Object> map, Map<String, Object> params) {
            params.put(param.getToField()[0], map.get(param.getFromField()[0]).toString().toLowerCase());
        }
    },
    CONCAT {
        @Override
        public void operate(Param param, Map<String, Object> map, Map<String, Object> params) {
            params.put(param.getToField()[0], concat(map,param.getFromField(), param.getDelimiter()));
        }

        private String concat(Map<String, Object> map, String [] attributes, String delimiter) {
            StringBuilder stringBuilder = new StringBuilder();

            if(attributes.length > 0){
                stringBuilder.append(map.get(attributes[0]));
            }

            for(int i = 1; i < attributes.length; i++){
                stringBuilder.append(delimiter != null ? delimiter : " ").append(map.get(attributes[i]));
            }

            return stringBuilder.toString();
        }
    }, SPLIT{
        @Override
        public void operate(Param param, Map<String, Object> map, Map<String, Object> params) {
            params.putAll(mapAttributes(map, param));
        }

        private MultiValueMap<String, Object> mapAttributes(Map<String, Object> values,Param param){
            MultiValueMap<String, Object> aux =  new LinkedMultiValueMap<>();

                Object[] object = values.get(param.getFromField()[0]).toString().split(param.getDelimiter() != null ?
                        param.getDelimiter() : " ",param.getToField().length);

                for (int i = 0; i < param.getToField().length; i++) {
                    if(i > object.length - 1) {
                        break;
                    }
                    aux.add(param.getToField()[i],object[i]);
                }
            return aux;
        }
    };

    public abstract void operate(Param commandParam, Map<String, Object> map, Map<String,Object> params);

    public static void operate(List<Param> commandParams, Map<String, Object> map,
                               Map<String,Object> params){

        commandParams.parallelStream().forEach(e-> e.getOperationType().operate(e,map,params));

        map.forEach((k, v) -> {
            if(notContains(commandParams, k)){
                params.put(k, v);
            }
        });
    }

    private static boolean notContains(List<Param> params, String key){
        return !params.parallelStream()
                .anyMatch(param -> Arrays.asList(param.getFromField()).contains(key));
    }

}

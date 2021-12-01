package mz.co.checkmob.api.connections.domain;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum OperationType {

    UPPERCASE {
        @Override
        public void operate(List<Param> commandParams, Map<String, Object> map, MultiValueMap<String, Object> params) {
            commandParams.forEach(param ->
                    params.add(param.getToField()[0], map.get(param.getFromField()[0]).toString().toLowerCase()) //rever depois
            );
            map.forEach((k, v) -> {
                if(!contains(commandParams, k)){
                    params.add(k, v.toString().toLowerCase());
                }
            });
        }
    }, LOWERCASE {
        @Override
        public void operate(List<Param> commandParams, Map<String, Object> map, MultiValueMap<String, Object> params) {
            commandParams.forEach(param ->
                    params.add(param.getToField()[0], map.get(param.getFromField()[0]).toString().toUpperCase()) //rever depois
            );

            map.forEach((k, v) -> {
                if(!contains(commandParams, k)){
                    params.add(k, v.toString().toUpperCase());
                }
            });
        }
    }, CONCAT {
        @Override
        public void operate(List<Param> commandParams, Map<String, Object> map, MultiValueMap<String, Object> params) {
            commandParams.forEach(param ->
                    params.add(param.getToField()[0], concat(map,param.getFromField())) //rever depois
            );
            map.forEach((k, v) -> {
                if(!contains(commandParams, k)){
                    params.add(k, v.toString().toLowerCase());
                }
            });
        }
        private String concat(Map<String, Object> map, String [] attributes){
            StringBuilder stringBuilder = new StringBuilder();
            Arrays.stream(attributes)
                    .forEach(attribute -> stringBuilder.append(" ").append(map.get(attribute)));
            return stringBuilder.toString();
        }
    }, JOIN{
        @Override
        public void operate(List<Param> commandParams, Map<String, Object> map, MultiValueMap<String, Object> params) {
            params.addAll(mapAttributes(map, commandParams));

            map.forEach((k, v) -> {
                if(!contains(commandParams, k)){
                    params.add(k, v.toString().toLowerCase());
                }
            });
        }

        private MultiValueMap<String, Object> mapAttributes(Map<String, Object> values,List<Param> params){
            MultiValueMap<String, Object> aux =  new LinkedMultiValueMap<>();

            params.forEach(p -> {
                Object[] object = values.get(p.getFromField()[0]).toString().split(" ",p.getToField().length);
                for (int i = 0; i < p.getToField().length; i++) {
                    aux.add(p.getToField()[i],object[i]);
                }
            });
            return aux;
        }
    };

    public abstract void operate(List<Param> commandParams,
                                 Map<String, Object> map, MultiValueMap<String,Object> params);

    public static void operate(List<Param> commandParams,
                                 Map<String, Object> map, MultiValueMap<String,Object> params, OperationType operateType){
        if(operateType  == OperationType.LOWERCASE){
            OperationType.LOWERCASE.operate(commandParams, map, params);
        }
        else if(operateType == OperationType.UPPERCASE){
            OperationType.UPPERCASE.operate(commandParams, map, params);
        }
        else if(operateType == OperationType.CONCAT){
            OperationType.CONCAT.operate(commandParams, map, params);

        }else if(operateType == OperationType.JOIN){
            OperationType.JOIN.operate(commandParams,map,params);
        }
    }

    private static boolean contains(List<Param> params, String key){
        return params.parallelStream()
                .anyMatch(param -> Arrays.asList(param.getFromField()).contains(key));
    }

}

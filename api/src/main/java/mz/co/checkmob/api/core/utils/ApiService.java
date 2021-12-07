package mz.co.checkmob.api.core.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

public class ApiService {

    private static final WebClient client = WebClient.builder().exchangeStrategies(ExchangeStrategies.builder().build())
            .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
                    .maxInMemorySize(20*1024*1024))
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public static <T> T post(String url, Object params, Class<T> returnClassType){

        if(params  instanceof MultiValueMap) {
            return returnClassType.cast(client.post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromMultipartData((MultiValueMap)params))
                    .retrieve()
                    .bodyToMono(returnClassType)
             .block());
        }else{
            return returnClassType.cast(client.post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(params))
                    .retrieve()
                    .bodyToMono(returnClassType));
        }
    }

    public static <T> T put(String url, Object params, Class<T> returnClassType){

        if(params  instanceof MultiValueMap) {
            return returnClassType.cast(client.put()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromMultipartData((MultiValueMap)params))
                    .retrieve()
                    .bodyToMono(returnClassType)
                    .block());
        }else{
            return returnClassType.cast(client.put()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(params))
                    .retrieve()
                    .bodyToMono(returnClassType));
        }
    }

    public static <T> T get(String url ,Class<T> returnClassType){

        return returnClassType.cast(client.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(returnClassType)
                .block());
    }

}


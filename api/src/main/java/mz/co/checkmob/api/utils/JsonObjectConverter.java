package mz.co.checkmob.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Map;

@Converter
public class JsonObjectConverter implements AttributeConverter<Map<String, Object>, String> {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Map<String, Object> jsonAttributes) {

        String jsonAttrs = null;
        try {
            jsonAttrs = objectMapper.writeValueAsString(jsonAttributes);
        } catch (final JsonProcessingException e) {
        }

        return jsonAttrs;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String customerInfoJSON) {

        Map<String, Object> jsonAttributes= null;
        try {
            jsonAttributes = objectMapper.readValue(customerInfoJSON, Map.class);
        } catch (final IOException e) {
        }

        return jsonAttributes;
    }

}
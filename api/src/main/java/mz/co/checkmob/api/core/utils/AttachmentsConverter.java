package mz.co.checkmob.api.core.utils;

import javax.persistence.AttributeConverter;

public class AttachmentsConverter implements AttributeConverter<String[], String> {
    @Override
    public String convertToDatabaseColumn(String[] attachments) {
        if(attachments!=null){
            if (attachments.length <= 0) return null;
            return String.join(",", attachments);
        }
        return null;
    }

    @Override
    public String[] convertToEntityAttribute(String attachments) {
        if (attachments == null) return new String[] {};
        return attachments.split(",");
    }
}

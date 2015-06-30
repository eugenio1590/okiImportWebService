package com.okiimport.web_service.lib;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.springframework.stereotype.Component;

@Component
public class JsonDateDeserializer extends JsonDeserializer<Timestamp>
{
    @Override
    public Timestamp deserialize(JsonParser jsonparser,
            DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {

        SimpleDateFormat format = new //SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        		SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = jsonparser.getText();
        try {
            return new Timestamp(format.parse(date).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

}

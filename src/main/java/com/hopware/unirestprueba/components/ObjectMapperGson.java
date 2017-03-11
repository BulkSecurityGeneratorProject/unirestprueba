package com.hopware.unirestprueba.components;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jackson.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.map.ser.std.ToStringSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by juarez on 08/03/17.
 */
public class ObjectMapperGson extends org.codehaus.jackson.map.ObjectMapper{

    public ObjectMapperGson() {
        SimpleModule sm = new SimpleModule("SomeName", new Version(1,1,1,""));
        sm.addSerializer(LocalDate.class, new JsonSerializer<LocalDate>() {
            @Override
            public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
                ToStringSerializer.instance.serialize(value, jgen, provider);
                // System.out.println(value.toString());
            }
        });
        sm.addSerializer(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
            @Override
            public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
                ToStringSerializer.instance.serialize(value, jgen, provider);
                // System.out.println(value.toString());
            }
        });
        sm.addDeserializer(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                String text = jp.getText();
                LocalDate date = LocalDate.parse(text);
                return date;
            }
        });
        sm.addDeserializer(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                String text = jp.getText();
                LocalDateTime dateTime = LocalDateTime.parse(text);
                return dateTime;
            }
        });
        registerModule(sm);
    }

}

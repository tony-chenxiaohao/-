package com.ruoyi.web.core.config;


import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.ruoyi.common.annotation.Sensitives;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SensitiveInfoSerialize extends JsonSerializer<String> implements ContextualSerializer {

    private DesensitizedUtil.DesensitizedType desensitizedType;

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(DesensitizedUtil.desensitized(s,desensitizedType));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        Sensitives sensitive = beanProperty.getAnnotation(Sensitives.class);
        if(sensitive !=null&&sensitive.isSensitive()){
            this.desensitizedType=sensitive.value();
            return this;
        }
        return serializerProvider.findValueSerializer(beanProperty.getType());
    }
}
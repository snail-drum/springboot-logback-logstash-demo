package com.snaildrum.demo.provider.access;

import ch.qos.logback.access.spi.IAccessEvent;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.logstash.logback.composite.AbstractFieldJsonProvider;
import net.logstash.logback.composite.FieldNamesAware;
import net.logstash.logback.composite.JsonWritingUtils;
import net.logstash.logback.fieldnames.LogstashAccessFieldNames;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * request params json provider
 */
public class RequestParamsJsonProvider extends AbstractFieldJsonProvider<IAccessEvent> implements FieldNamesAware<LogstashAccessFieldNames> {

    private static final String FIELD_REQUESTPARAMS = "requestParams";

    public RequestParamsJsonProvider() {
        setFieldName(FIELD_REQUESTPARAMS);
    }

    @Override
    public void writeTo(JsonGenerator generator, IAccessEvent event) throws IOException {
        // when content-type is  application/json
        String jsonStr = event.getAttribute("jsonStr");
        if (jsonStr != null && !"".equals(jsonStr) && !IAccessEvent.NA.equals(jsonStr)) {
            JsonWritingUtils.writeStringField(generator, getFieldName(), jsonStr);
            return;
        }

        // not application/json
        Map<String, String[]> requestParameterMap = event.getRequestParameterMap();
        if (requestParameterMap == null || requestParameterMap.isEmpty()) {
            return;
        }

        Set<String> requestParamKeys = requestParameterMap.keySet();

        Map<String, String> map = requestParamKeys.stream().collect(Collectors.toMap(k -> k, requestParamKey -> {
                    String[] requestParameter = requestParameterMap.get(requestParamKey);
                    if (requestParameter.length == 1) {
                        return requestParameter[0];
                    }
                    return String.join(",", requestParameter);
                },
                (oldValue, newValue) -> newValue)
        );

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        JsonWritingUtils.writeStringField(generator, getFieldName(), json);
    }

    @Override
    public void setFieldNames(LogstashAccessFieldNames fieldNames) {
        setFieldName(FIELD_REQUESTPARAMS);
    }

}

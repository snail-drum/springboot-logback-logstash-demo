package com.snaildrum.demo.provider.access;

import ch.qos.logback.access.spi.IAccessEvent;
import com.fasterxml.jackson.core.JsonGenerator;
import net.logstash.logback.composite.AbstractFieldJsonProvider;
import net.logstash.logback.composite.FieldNamesAware;
import net.logstash.logback.composite.JsonWritingUtils;
import net.logstash.logback.fieldnames.LogstashAccessFieldNames;

import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * pid json provider
 *
 */
public class PidJsonProvider extends AbstractFieldJsonProvider<IAccessEvent> implements FieldNamesAware<LogstashAccessFieldNames> {

    private static final String FIELD_PID = "pid";

    public PidJsonProvider() {
        setFieldName(FIELD_PID);
    }

    @Override
    public void writeTo(JsonGenerator generator, IAccessEvent event) throws IOException {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        JsonWritingUtils.writeStringField(generator, getFieldName(), pid);
    }

    @Override
    public void setFieldNames(LogstashAccessFieldNames fieldNames) {
        setFieldName(FIELD_PID);
    }
}

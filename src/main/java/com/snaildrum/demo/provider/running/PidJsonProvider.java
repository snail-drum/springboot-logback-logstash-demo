package com.snaildrum.demo.provider.running;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.fasterxml.jackson.core.JsonGenerator;
import net.logstash.logback.composite.AbstractFieldJsonProvider;
import net.logstash.logback.composite.FieldNamesAware;
import net.logstash.logback.composite.JsonWritingUtils;
import net.logstash.logback.fieldnames.LogstashFieldNames;

import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * pid json provider
 */
public class PidJsonProvider extends AbstractFieldJsonProvider<ILoggingEvent> implements FieldNamesAware<LogstashFieldNames> {

    private static final String FIELD_PID = "pid";

    public PidJsonProvider() {
        setFieldName(FIELD_PID);
    }

    @Override
    public void writeTo(JsonGenerator generator, ILoggingEvent event) throws IOException {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        JsonWritingUtils.writeStringField(generator, getFieldName(), pid);
    }

    @Override
    public void setFieldNames(LogstashFieldNames fieldNames) {
        setFieldName(FIELD_PID);
    }

}

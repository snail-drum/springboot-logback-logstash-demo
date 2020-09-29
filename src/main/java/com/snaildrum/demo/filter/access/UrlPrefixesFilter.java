package com.snaildrum.demo.filter.access;

import ch.qos.logback.access.spi.IAccessEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * urls prefixes filter
 *
 */
public class UrlPrefixesFilter extends AbstractMatcherFilter<IAccessEvent> {

    /**
     * url prefixes
     *
     * split by ,
     */
    private String prefixes;

    @Override
    public FilterReply decide(IAccessEvent event) {
        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }
        for (String prefix : prefixes.split(",")) {
            if (event.getRequestURI().toLowerCase().startsWith(prefix)) {
                return onMatch;
            }
        }
        return onMismatch;
    }

    public String getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(String prefixes) {
        this.prefixes = prefixes;
    }

    @Override
    public void start() {
        if (this.prefixes != null) {
            super.start();
        }
    }

}

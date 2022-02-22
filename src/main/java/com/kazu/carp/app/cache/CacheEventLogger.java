package com.kazu.carp.app.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author akifova
 * 08.10.2021
 */
public class CacheEventLogger implements CacheEventListener<Object, Object> {
    private static final Logger logger = LoggerFactory.getLogger(CacheEventLogger.class);

    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        System.out.println("Cache Event: " +
                cacheEvent.getKey() + " " + cacheEvent.getOldValue() + " " + cacheEvent.getNewValue());
            logger.info("Cache Event: ",
                    cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
    }
}

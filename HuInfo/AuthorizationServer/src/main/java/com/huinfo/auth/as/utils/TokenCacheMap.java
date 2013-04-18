/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huinfo.auth.as.utils;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.huinfo.auth.as.pojo.BaseAccessToken;

/**
 *
 * @author ZhangZhenli
 */
public class TokenCacheMap extends LinkedHashMap<String, BaseAccessToken> {

    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TokenCacheMap.class);
    private static final int MAX_ENTRIES = 1000;

    public TokenCacheMap() {
        super(MAX_ENTRIES, Float.valueOf(0.75f), true);
    }

    @Override
    protected boolean removeEldestEntry(Entry<String, BaseAccessToken> eldest) {
        logger.debug("TokenCacheMap size:" + size());
        if (true) {
            logger.debug("TokenCacheMap toString:" + this.toString());
        }
        return size() > MAX_ENTRIES;
    }
}

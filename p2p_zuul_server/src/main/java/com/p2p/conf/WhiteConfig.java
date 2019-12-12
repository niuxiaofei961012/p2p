package com.p2p.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//@ConfigurationProperties(prefix = "filter")
public class WhiteConfig {

    List<String> whiteList;

    @Override
    public String toString() {
        return "WhiteConfig{" +
                "whiteList=" + whiteList +
                '}';
    }

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }
}

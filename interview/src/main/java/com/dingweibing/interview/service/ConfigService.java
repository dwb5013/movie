package com.dingweibing.interview.service;

public interface ConfigService {
    String getEsHostname();

    Integer getEsPort();

    String getEsConnectionSchema();

    String getEsUsername();

    String getEsPassword();

    Integer getEsConnectTimeoutLength();

    Integer getEsSocketTimeoutLength();

    Long getQueryServiceMaxResultSize();

    Long getCommandServiceMaxRequestSize();

    static String getSystemId() {
        return System.getenv("SYSTEM_ID");
    }
}

package com.dingweibing.interview.service;

public interface ConfigService {
    String getEsHostname();

    Integer getEsPort();

    String getEsConnectionSchema();

    String getEsUsername();

    String getEsPassword();

    Integer getEsConnectTimeoutLength();

    Integer getEsSocketTimeoutLength();

    Integer getQueryServiceMaxPageSize();

    Integer getQueryServiceMaxPage();

    Integer getCommandServiceMaxRequestSize();

    static String getSystemId() {
        return System.getenv("SYSTEM_ID");
    }
}

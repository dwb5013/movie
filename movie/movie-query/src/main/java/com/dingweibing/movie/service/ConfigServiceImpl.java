package com.dingweibing.movie.service;

import org.springframework.stereotype.Service;

// 本来は各マイクロサービスの設定データはクラウド上で取得することが一般的ですが、
// 今回は試作のため、ハードコーディングで定義する
@Service
public class ConfigServiceImpl implements ConfigService {

    @Override
    public String getEsHostname() {
        // HttpClient httpClient = HttpClient.newBuilder().authenticator(usernameAndPassword ? SSL Certs ?).build();
        // httpClient.send(ConfigService.getSystemId(), 172.218.xxx.xxx)
        return "es01";
    }

    @Override
    public Integer getEsPort() {
        return 9200;
    }

    @Override
    public String getEsConnectionSchema() {
        return "https";
    }

    // Todo 試作のため、明文化にハードコーディングにする、Prod環境であれば、要注意
    @Override
    public String getEsUsername() {
        return "elastic";
    }

    // Todo 試作のため、明文化にハードコーディングにする、Prod環境であれば、要注意
    @Override
    public String getEsPassword() {
        return "BobWithAlice";
    }

    @Override
    public Integer getEsConnectTimeoutLength() {
        return 5000;
    }

    @Override
    public Integer getEsSocketTimeoutLength() {
        return 120000;
    }

    @Override
    public Integer getQueryServiceMaxPage() {
        return 50;
    }

    @Override
    public Integer getQueryServiceMaxPageSize() {
        return 100;
    }


    @Override
    public Integer getCommandServiceMaxRequestSize() {
        return 1000;
    }
}

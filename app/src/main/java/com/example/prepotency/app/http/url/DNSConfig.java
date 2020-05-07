package com.example.prepotency.app.http.url;

public class DNSConfig {

    private static volatile DNSConfig singleton;

    private DNSConfig() {
    }

    public static DNSConfig getInstance() {
        if (singleton == null) {
            synchronized (DNSConfig.class) {
                if (singleton == null) {
                    singleton = new DNSConfig();
                }
            }
        }
        return singleton;
    }

    /**
     * 获取域名服务器 DNS(domain name server)
     * @return
     */
    public boolean getDNS(){
        return ConstantUrl.DEBUG;
    }

    /**
     * 获取基本网址
     * @return
     * boolean startsWith(String prefix)
     * 测试此字符串是否以指定的前缀开始。
     * @param url
     */
    public String getBaseUrl(String url){
        if(getDNS()){
            if(url.startsWith(ConstantUrl.VVV)){
                return ConstantUrl.BASE_URL_SERVER_DEBUG;
            } else{
                return ConstantUrl.BASE_URL_DEBUG;
            }
        }else{
            if(url.startsWith(ConstantUrl.VVV)){
                return ConstantUrl.BASE_URL_SERVER_RELEASE;
            } else{
                return ConstantUrl.BASE_URL_RELEASE;
            }
        }
    }

    public String getH5Url(){
        return getDNS() ? ConstantUrl.H5_URL_DEBUG : ConstantUrl.H5_URL_RELEASE;
    }

    public String getWs(){
        return getDNS() ? ConstantUrl.WS_DEBUG : ConstantUrl.WS_RELEASE;
    }

}
package com.ixinhoo.web.weixin.Utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientDoGet {

    public static String doGet(String appId,String appsecret) throws Exception {

        // 
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 
        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appsecret);

        CloseableHttpResponse response = null;
        try {
            // 
            response = httpclient.execute(httpGet);
            // 
            if (response.getStatusLine().getStatusCode() == 200||response.getStatusLine().getStatusCode() == 201) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
               return content;
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }
    return null;
    }
}

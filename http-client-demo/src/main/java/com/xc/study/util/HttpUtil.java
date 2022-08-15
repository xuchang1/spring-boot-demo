package com.xc.study.util;


import com.xc.study.config.HttpClientPool;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtil {

    public static void main(String[] args) {
        String url = "http://localhost:8080/demo/testLimit";
        System.out.println("response : " + get(url));
    }

    public static String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse httpResponse = HttpClientPool.getHttpClient().execute(httpGet);
            return parseResponse(httpResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String parseResponse(CloseableHttpResponse httpResponse) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (HttpStatus.SC_OK != statusCode) {
            throw new RuntimeException(statusCode + "-" + httpResponse.getStatusLine().getReasonPhrase());
        }

        try {
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

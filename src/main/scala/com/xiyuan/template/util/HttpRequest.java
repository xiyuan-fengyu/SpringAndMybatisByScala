package com.xiyuan.template.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiyuan_fengyu on 2016/12/1.
 */
public class HttpRequest {

    private String url;

    public static final String GET = "GET";

    public static final String POST = "POST";

    private String method;

    private HashMap<String, Object> params = new HashMap<>();

    private String output;

    private String responseCharset;

    private int responseCode;

    private String responseMessage;

    private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36";

    private List<String[]> headers = new ArrayList<>();

    public HttpRequest(String url, String method) {
        this.url = url;
        if (POST.equals(method)) {
            this.method = method;
        }
        else this.method = "GET";
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public <T> HttpRequest addParam(String key, T value) {
        if (key != null) {
            params.put(key, value == null? "": value);
        }
        return this;
    }

    public String getUrlWithParams() {
        boolean paramExisted = url.matches("^.*\\?.*=.*");
        String tempUrl = url;
        for (Map.Entry<String, Object> keyVal: params.entrySet()) {
            String key = keyVal.getKey();
            Object object = keyVal.getValue();
            String encodedValue;
            try {
                encodedValue = URLEncoder.encode(object.toString(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                encodedValue = "";
            }
            if (paramExisted) {
                tempUrl += "&" + key + "=" + encodedValue;
            }
            else {
                tempUrl += "?" + key + "=" + encodedValue;
                paramExisted = true;
            }
        }
        return tempUrl;
    }

    public HttpRequest setCookie(String cookie) {
        return addHeader("Cookie", cookie);
    }

    public HttpRequest setOutputData(String data) {
        if (data != null && method.equals(POST)) {
            this.output = data;
        }
        return this;
    }

    public HttpRequest setResponseCharset(String responseCharset) {
        if (responseCharset != null && !"".equals(responseCharset)) {
            this.responseCharset = responseCharset;
        }
        return this;
    }

    public HttpRequest addHeader(String key, String value) {
        if (key != null && value != null) {
            headers.add(new String[]{key, value});
        }
        return this;
    }

    public static void setUserAgent(String userAgent) {
        HttpRequest.userAgent = userAgent;
    }

    public String getString() {
        try {
            URL tempUrl = new URL(getUrlWithParams());
            HttpURLConnection connection = (HttpURLConnection) tempUrl.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(POST.equals(method));
            connection.setUseCaches(false);
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            if (userAgent != null) {
                connection.setRequestProperty("User-Agent", userAgent);
            }

            for (String[] header: headers) {
                connection.setRequestProperty(header[0], header[1]);
            }

            if (output != null) {
                //connection.connect();通过下面的方式会隐式调用connect
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.write(output.getBytes("utf-8"));
                out.flush();
                out.close();
            }
            else {
                connection.connect();
            }

            responseCode = connection.getResponseCode();
            responseMessage = connection.getResponseMessage();

            String charset = responseCharset == null? getResponseCharset(connection.getContentType()): responseCharset;
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            String line;
            StringBuilder strBld = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                strBld.append(line).append("\n");
            }
            reader.close();
            return strBld.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JsonObject getJson() {
        String result = getString();
        if (result == null) {
            return null;
        }
        else {
            try {
                return new JsonParser().parse(result).getAsJsonObject();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = "UTF-8";
        if(ctype != null && !ctype.isEmpty()) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2 && pair[1] != null && !pair[1].isEmpty()) {
                        charset = pair[1].trim();
                    }
                    break;
                }
            }
        }
        return charset;
    }

}

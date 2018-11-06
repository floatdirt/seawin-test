package com.seawin.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.seawin.common.tool.io.IOUtil;
import com.seawin.common.tool.string.StringUtil;
import com.seawin.pojo.vo.RequestResultVO;

/**
 * httpClient的增强
 * @author jin
 * @version $Id: HttpClientSupport.java, v 0.1 2015年7月29日 上午9:36:57 jin Exp $
 * @param <T>
 */
public class HttpClientSupport<T> {
    private static ThreadLocal<String> sessionStock = new ThreadLocal<String>();

    /**
     * 发送一个带文件的
     * 
     * @param url
     * @param content
     * @param c
     * @return
     * @throws IOException
     */
    public static <T> RequestResultVO sendFileRequest(String url, File file, String fieldName,
                                                      Class<T> c) throws IOException {
        return sendFileRequestWithHeaders(url, file, fieldName, c, null);
    }

    /**
     * 发送带有文件域的表单
     * 
     * @param url
     * @param form
     * @param c
     * @return
     * @throws IOException
     */
    public static <T> RequestResultVO sendFormWithFileRequest(String url, Map<String, Object> form,
                                                              Class<T> c) throws IOException {
        return sendFormWithFileAndHeaders(url, form, c, null);
    }

    /**
     * 发送带有文件域的表单，还有包头
     * 
     * @param url
     * @param form
     * @param c
     * @return
     * @throws IOException
     */
    public static <T> RequestResultVO sendFormWithFileAndHeaders(String url,
                                                                 Map<String, Object> form,
                                                                 Class<T> c,
                                                                 Map<String, String> headers)
                                                                                             throws IOException {
        HttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);
        setHeaders(post, headers);
        MultipartEntity entity = new MultipartEntity();
        post.setEntity(entity);
        for (Map.Entry<String, Object> entry : form.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            if (val instanceof File) {
                entity.addPart(key, new FileBody((File) val));
            } else {
                entity.addPart(key, new StringBody(val.toString(), Charset.forName("utf-8")));
            }
        }
        HttpResponse response = client.execute(post);
        InputStream is = response.getEntity().getContent();
        String jsonResult = IOUtil.readAsString(is);
        return convert(jsonResult, c);
    }

    /**
     * 发送一个带文件+headers包头的
     * 
     * @param url
     * @param file
     * @param fieldName
     * @param c
     * @param headers
     * @return
     * @throws IOException
     */
    public static <T> RequestResultVO sendFileRequestWithHeaders(String url, File file,
                                                                 String fieldName, Class<T> c,
                                                                 Map<String, String> headers)
                                                                                             throws IOException {
        HttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);
        setHeaders(post, headers);
        if (file != null) {
            MultipartEntity entity = new MultipartEntity();
            post.setEntity(entity);
            entity.addPart(fieldName, new FileBody(file));
        }
        HttpResponse response = client.execute(post);
        InputStream is = response.getEntity().getContent();
        String jsonResult = IOUtil.readAsString(is);
        return convert(jsonResult, c);
    }

    /**
     * 发送一般性的请求
     * 
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static <T> RequestResultVO sendNormalRequest(String url, Class<T> c) throws IOException {
        return sendNormalRequestWithHeader(url, c, null, null);
    }

    /**
     * 发送一般性的请求
     * 
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static <T> RequestResultVO sendNormalRequest(String url, Map<String, String> params,
                                                        Class<T> c) throws IOException {
        return sendNormalRequestWithHeader(url, c, params, null);
    }

    /**
     * 发送一般性的请求，带上包头
     * 
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static <T> RequestResultVO sendNormalRequestWithHeader(String url, Class<T> c,
                                                                  Map<String, String> params,
                                                                  Map<String, String> headers)
                                                                                              throws IOException {
        HttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);
        setHeaders(post, headers);
        setPostForm(post, params);
        HttpResponse response = client.execute(post);
        InputStream is = response.getEntity().getContent();
        String jsonResult = IOUtil.readAsString(is);
        System.out.println(jsonResult);
        return convert(jsonResult, c);
    }

    /**
     * 发送请求
     * 
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendRequest(String url) throws IOException {
        HttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);
        HttpResponse response = client.execute(post);
        InputStream is = response.getEntity().getContent();
        return IOUtil.readAsString(is);
    }

    public static String sendGetRequest(String url) throws IOException {
        HttpClient client = getHttpClient();
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        InputStream is = response.getEntity().getContent();
        return IOUtil.readAsString(is);
    }

    public static byte[] sendGetRequest4Bytes(String url) throws IOException {
        HttpClient client = getHttpClient();
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        InputStream is = response.getEntity().getContent();
        return IOUtil.readAsBytes(is);
    }

    public static String sendPosRequest(String url, Map<String, String> params,
                                        Map<String, String> headers) throws IOException {
        HttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                post.addHeader(entry.getKey(), entry.getValue());
            }
        }
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            BasicNameValuePair vp = new BasicNameValuePair(entry.getKey(), entry.getValue());
            parameters.add(vp);
        }
        post.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
        HttpResponse response = client.execute(post);
        InputStream is = response.getEntity().getContent();
        return IOUtil.readAsString(is);
    }

    public static String getSessionId(String url, Map<String, String> params,
                                      Map<String, String> headers) throws IOException {
        HttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                post.addHeader(entry.getKey(), entry.getValue());
            }
        }
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            BasicNameValuePair vp = new BasicNameValuePair(entry.getKey(), entry.getValue());
            parameters.add(vp);
        }
        post.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
        HttpResponse response = client.execute(post);
        String JSESSIONID = StringUtil.split(
            StringUtil.split(response.getHeaders("Set-Cookie")[0].getValue(), ";")[0], "=")[1];
        return JSESSIONID;
    }

    public static String sendPosRequest(String url, Map<String, String> params) throws IOException {
        Map<String, String> headers = null;
        return sendPosRequest(url, params, headers);
    }

    public static <T> RequestResultVO sendPosJsonRequest(String url, Object params, Class<T> c)
                                                                                               throws IOException {
        HttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);
        StringEntity s = new StringEntity(JSON.toJSONString(params), "utf-8");
        s.setContentEncoding("UTF-8");
        s.setContentType("application/json");
        post.setEntity(s);
        HttpResponse response = client.execute(post);
        InputStream is = response.getEntity().getContent();
        String jsonResult = IOUtil.readAsString(is);
        return convert(jsonResult, c);
    }

    public static <T> RequestResultVO sendPosRequest(String url, Map<String, String> params,
                                                     Class<T> c) throws IOException {
        HttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);

        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            BasicNameValuePair vp = new BasicNameValuePair(entry.getKey(), entry.getValue());
            parameters.add(vp);
        }
        post.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
        HttpResponse response = client.execute(post);
        InputStream is = response.getEntity().getContent();
        String jsonResult = IOUtil.readAsString(is);
        return convert(jsonResult, c);
    }

    private static <T> RequestResultVO convert(String jsonStr, Class<T> c) {
        JSONObject jsonObj = JSON.parseObject(jsonStr);
        RequestResultVO result = new RequestResultVO();
        if (jsonObj.containsKey("data"))
            result.setData(jsonObj.getObject("data", c));
        if (jsonObj.containsKey("message"))
            result.setMessage(jsonObj.getString("message"));
        if (jsonObj.containsKey("code"))
            result.setCode(Integer.parseInt(jsonObj.getString("code")));
        if (jsonObj.containsKey("newSessionId")) {
            sessionStock.set(jsonObj.getString("newSessionId"));
        }
        return result;
    }

    private static void setHeaders(HttpPost post, Map<String, String> headers) {
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                post.setHeader(entry.getKey(), entry.getValue());
            }
        }
        if (sessionStock.get() != null) {
            post.setHeader("sessionId", sessionStock.get());
        }
    }

    private static void setPostForm(HttpPost post, Map<String, String> params) throws IOException {
        if (params != null) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                nvps.add(new BasicNameValuePair(key, params.get(key)));
            }
            post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        }
    }

    private static HttpClient getHttpClient() {
        HttpClient client = new DefaultHttpClient();
        //        HttpHost proxy = new HttpHost("127.0.0.1", 8888);
        //        client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        return client;
    }
}

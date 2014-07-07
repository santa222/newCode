package com.mika.newcode.network.request;

import android.content.Context;
import android.util.Log;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;

public class NetworkHelper {

    private static final String CONTENT_TYPE = "application/json";

    private static final String TAG = "DMTC: NetworkHelper";

    public static StringEntity getEntity(JSONObject json)
            throws UnsupportedEncodingException {
        StringEntity entity = new StringEntity(json.toString(), HTTP.UTF_8);
        entity.setContentType(CONTENT_TYPE + HTTP.CHARSET_PARAM + HTTP.UTF_8);
        return entity;
    }

    public static Result post(Context context, String url, JSONObject postData) {
        Log.d(TAG, "post: url = " + url + ", postData = " + postData);
        try {
            StringEntity entity = getEntity(postData);
            return post(context, url, entity, false);
        } catch (UnsupportedEncodingException e) {
            return new Result(e);
        }
    }

    public static Result post(Context context, String url, HttpEntity entity, boolean multipart) {
        final HttpPost httppost = new HttpPost(url);
        return execute(context, httppost, entity, multipart);
    }

    public static Result put(Context context, String url, HttpEntity entity, boolean multipart) {
        final HttpPut put = new HttpPut(url);
        return execute(context, put, entity, multipart);
    }

    public static Result get(Context context, String url) {
        Log.d(TAG, "get: url = " + url);
        final HttpGet httpget = new HttpGet(url);
        return execute(context, httpget, null, false);
    }

    private static Result execute(Context context, HttpRequestBase request,
                                  HttpEntity entity, boolean multipart)/* throws AuthRequiredException */{

        HttpClient httpClient = null;
        try {

            String method;
            if (entity != null) {
                if (request instanceof HttpPut) {
                    method = HttpPut.METHOD_NAME;
                    ((HttpPut) request).setEntity(entity);
                } else if (request instanceof HttpPost) {
                    method = HttpPost.METHOD_NAME;
                    ((HttpPost) request).setEntity(entity);
                } else if (request instanceof HttpDelete) {
                    method = HttpDelete.METHOD_NAME;
                } else {
                    throw new RuntimeException(
                            "Got HTTPEntity but request is not POST, PUT or DELETE!");
                }
            } else {
                method = HttpGet.METHOD_NAME;
            }

            if (!multipart) {
                request.setHeader("Content-Type", CONTENT_TYPE);
                request.setHeader("Accept", CONTENT_TYPE);
            }
/*
            String token = SettingsUtils.getRegistrationToken(context);
            if (StringUtils.isNotEmpty(token)) {
                request.setHeader("Authorization", "Token token=\"" + SettingsUtils.getRegistrationToken(context) + "\"");
            }*/

            httpClient = HttpClientFactory.getThreadSafeClient();
            final HttpResponse httpResponse = httpClient.execute(request);
            final int statusCode = httpResponse.getStatusLine().getStatusCode();
            Log.d(TAG, "execute: statusCode = " + statusCode);
          /*  if (statusCode != HttpStatus.SC_OK
                    && statusCode != HttpStatus.SC_CREATED) {

                final String detailMessage = "Got HTTP " + statusCode + " ("
                        + httpResponse.getStatusLine().getReasonPhrase() + ')';

                LogRequest logRequest = new LogRequest();
                logRequest.setDevice_information(new LogDeviceInformation(context));
                logRequest.setEntry(new LogEntry());
                logRequest.getEntry().setTag("Request");
                logRequest.getEntry().setMessage("Error in request: " + detailMessage);
                logRequest.setRequest_information(new LogRequestInformation());
                logRequest.getRequest_information().setMethod(request.getMethod());
                logRequest.getRequest_information().setUrl(request.getURI().toString());
                logRequest.getRequest_information().setStatus_code(String.valueOf(statusCode));
                if (entity != null) {
                    logRequest.getRequest_information().setParams(EntityUtils.toString(entity));
                }
                new LogTask(context, logRequest).execute();


                IOException reason = new IOException(detailMessage);
                throw reason;
            }*/
            String response = convertStreamToString(httpResponse.getEntity().getContent());
            Log.d(TAG, "response: " + response);
            return new Result(response);
        } catch (ConnectTimeoutException e) {
            return new Result(e, true);
        } catch (SocketTimeoutException e) {
            return new Result(e, true);
        } catch (Exception e) {
            return new Result(e);
        } /*finally {
            safelyCloseHttpClient(httpClient);
        }*/
    }

    private static String convertStreamToString(InputStream stream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        catch (IOException e) {
            Log.e(TAG, "Cant convert the stream to string", e);
        }
        finally
        {
            try  {
                stream.close();
            }
            catch (IOException e) {
                Log.e(TAG, "Cant convert the stream to string", e);
            }
        }
        return sb.toString();
    }

    public static Result delete(Context appContext, String requestUrl,
                                HttpEntity entity) {
        Log.d(TAG, "delete: url = " + requestUrl);
        final HttpDelete delete = new HttpDelete(requestUrl);
        return execute(appContext, delete, entity, false);
    }

    public static void safelyCloseHttpClient(HttpClient httpClient) {
        if (httpClient != null) {
            httpClient.getConnectionManager().shutdown();
            httpClient = null;
        }
    }

    public static class Result {

        private boolean success;
        private boolean timeout;
        private String response;
        private Exception error;

        public Result(String response) {
            this.response = response;
            this.success = true;
        }

        public Result(Exception error) {
            this.error = error;
            this.success = false;
        }

        public Result(Exception error, boolean timeout) {
            this.error = error;
            this.success = false;
            this.timeout = timeout;
        }

        public String getResponse() {
            return response;
        }

        public Exception getError() {
            return error;
        }

        public boolean isSuccess() {
            return success;
        }

        public boolean isTimeout() {
            return timeout;
        }

        public String toString() {
            return new StringBuilder().append("{ success: ").append(success)
                    .append(", response: ").append(response)
                    .append(", error: ").append(error).append(" }").toString();
        }
    }
}

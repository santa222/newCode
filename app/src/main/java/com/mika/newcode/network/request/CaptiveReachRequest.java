package com.mika.newcode.network.request;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.mika.newcode.utils.ConnectionUtils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * AsyncTask used to perform the connection to the server and parse the response
 * You can subclass it if you want to perform your own task in your application
 *
 * @param <ResponseType>
 */
public abstract class CaptiveReachRequest<ResponseType> extends AsyncTask<Void, Void, NetworkHelper.Result> {
    private static final String TAG = CaptiveReachRequest.class.getSimpleName();

    protected Context context;
    /** The HTTP Method to be used in the request */
    private String httpMethod;
    /** The Message to display in the Progress Dialog */
    private String progressMessage;
    /** The progress dialog */
    private ProgressDialog progress;
    /** The listener, can be null */
    private TaskListener<ResponseType> listener;

    protected boolean multipart;

    protected boolean activeNetwork;

    /** The request URL, implemented by each task */
    protected abstract String getRequestUrl() throws Exception;


    /** Each task must implement its own actions when there is no network*/
  //  protected abstract NetworkHelper.Result onNetworkDisabled();

    /** */
    protected abstract ResponseType parseResponse(String responseString) throws Exception;

    public CaptiveReachRequest(Context context, String httpMethod,
                               String progressMessage, TaskListener<ResponseType> listener) {
        this.context = context;
        this.httpMethod = httpMethod;
        this.progressMessage = progressMessage;
        this.listener = listener;
    }

    public CaptiveReachRequest(Context context, String httpMethod,
                               String progressMessage, TaskListener<ResponseType> listener, boolean multipart) {
        this.context = context;
        this.httpMethod = httpMethod;
        this.progressMessage = progressMessage;
        this.listener = listener;
        this.multipart = multipart;
    }

    @Override
    protected void onPreExecute() {
        log("onPreExecute: entered");
        if (progressMessage != null) {
            progress = new ProgressDialog(context);
            progress.setMessage(progressMessage);
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();
        }

        activeNetwork = ConnectionUtils.isNetworkActive(context);
       /* if (SettingsUtils.isCacheEnabled(context)) {
            CRDynamicContentDao dao = new CRDynamicContentDao(context);
            activeNetwork &= (!dao.isTableExists(getPermanentLink()) || SettingsUtils.isCacheRunning(context));
        }*/
    }

    @Override
    protected NetworkHelper.Result doInBackground(Void... args) {
       /* if(!activeNetwork){
            return onNetworkDisabled();
        }*/

        String requestUrl;

        try {
            requestUrl = getRequestUrl();
        } catch (Exception e) {
            logError(e.getMessage(), e);
            cancel(true);
            return null;
        }

        Uri.Builder paramBuilder = new Uri.Builder();
        Map<String, String> params = getParams();

        if (HttpGet.METHOD_NAME.equals(httpMethod)) {
            if (params == null) {
                params = new HashMap<String, String>(1);
            }

            for (String paramName : params.keySet()) {
                paramBuilder.appendQueryParameter(paramName,
                        params.get(paramName));
            }
        }

        requestUrl += paramBuilder.toString();

        log(httpMethod + ":" + requestUrl);

        if (context == null) {
            return null;
        }

        final Context appContext = context.getApplicationContext();

        if (appContext == null) {
            return null;
        }

        if (HttpGet.METHOD_NAME.equals(httpMethod)) {
            return NetworkHelper.get(appContext, requestUrl);
        } else {
            HttpEntity entity;
            try {
                entity = getHttpEntity();
            } catch (IOException e) {
                return new NetworkHelper.Result(e);
            }

            if (HttpPost.METHOD_NAME.equals(httpMethod)) {
                return NetworkHelper.post(appContext, requestUrl, entity, multipart);
            } else if (HttpPut.METHOD_NAME.equals(httpMethod)) {
                return NetworkHelper.put(appContext, requestUrl, entity, multipart);
            } else if (HttpDelete.METHOD_NAME.equals(httpMethod)) {
                return NetworkHelper.delete(appContext, requestUrl, entity);
            }
            throw new RuntimeException("HTTP Method not supported! "
                    + httpMethod);
        }
    }

    @Override
    protected void onPostExecute(NetworkHelper.Result result) {
        log("onPostExecute: entered, result = " + result);

        if (progress != null) {
            progress.dismiss();
        }

        if (result == null) {
            reportError(new Exception("Could not find any results"));
            return;
        }

        if (result.isSuccess()) {
            final String responseStr = result.getResponse();
            log("Got response: " + responseStr);
            if (!TextUtils.isEmpty(responseStr)) {
                try {
                    ResponseType response = internalParseResponse(responseStr);
                    reportSuccess(response);
                } catch (Exception e) {
                    logError("onPostExecute: failed to parse response: ", e);
                    reportError(e);
                }
            } else {
                IOException e = new IOException("Got empty response!");
                logError("onPostExecute:", e);
                reportError(e);
            }
        } else if (result.isTimeout()) {
            reportTimeOut();
        } else {
            Exception e = result.getError();
            logError("onPostExecute: failed to execute http request", e);
            reportError(e);
        }
    }

    @Override
    protected void onCancelled(NetworkHelper.Result result) {
        super.onCancelled(result);
        reportCancelled();
    }

    protected Map<String, String> getParams() {
        return new HashMap<String, String>();
    }

    protected HttpEntity getHttpEntity() throws IOException {
        // Override this method in inherited classes
        return null;
    }

    protected void log(String info) {
        Log.d(getClass().getName(), info);
    }

    protected void logError(String description, Throwable error) {
        error.printStackTrace();
        Log.e(getClass().getName() + ':' + description, error.toString());
    }

    private void reportSuccess(ResponseType response) {
        if (listener != null) {
            listener.onTaskSuccess(response);
        }
    }

    private void reportError(Exception e) {
        if (listener != null) {
            listener.onTaskFailure(e);
        }
    }

    private void reportCancelled() {
        if (listener != null) {
            listener.onTaskCancelled();
        }
    }

    private void reportTimeOut() {
        if (listener != null) {
            listener.onTaskTimeOut();
        }
    }

    private ResponseType internalParseResponse(String jsonString) throws Exception {
        String response = jsonString;

        try {
            JSONObject json = new JSONObject(jsonString);
            JSONObject root = json.getJSONObject("json");

            response = root.toString();
        } catch (JSONException e) {

        }

       /* try {
            *//** To avoid showing data if any when an error occurs trying to update the database *//*
            if (!SettingsUtils.isCacheRunning(context) && activeNetwork) {
                String permanent_link = getPermanentLink();
                CRDynamicContentDao dao = new CRDynamicContentDao(context);
                dao.insertData(permanent_link, response);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error trying to update the database: " + e);
        }*/

        return parseResponse(response);
    }
}

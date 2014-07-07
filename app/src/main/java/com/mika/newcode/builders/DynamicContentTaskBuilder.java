package com.mika.newcode.builders;

import android.content.Context;

import com.google.gson.Gson;
import com.mika.newcode.network.request.GetDynamicContent;
import com.mika.newcode.network.request.TaskListener;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Helper class to request any content from the platform.
 *
 * By using the builder pattern, the user can customize any request
 * adding features like url parameters, request filters, http method.
 *
 * Created by hugo on 3/24/14.
 */
public class DynamicContentTaskBuilder<T> {
    private Context context;
    private String permanentLink;
    private String progressMessage;
    private TaskListener<T> taskListener;
   // private CRFilterRequest filterRequest;
    private String requestMethod;
    private Type type;
    private HashMap<String, String> params;
    private HttpEntity httpEntity;

    /**
     *
     * Build the actual task to be executed
     *
     * @return Returns the task object to be executed in order to retrieve content from the platform.
     */
    public GetDynamicContent<T> build(){
        return new GetDynamicContent<T>(this);
    }


    /**
     * Sets the context for the builder.
     *
     * @param context The application context
     */
    public DynamicContentTaskBuilder<T> withContext(Context context) {
        this.context = context;
        return this;
    }


    /**
     * Sets the content path for the builder.
     *
     * @param permanentLink The url path to retrieve the desired content (e.g. v2/mobile_menus)
     */
    public DynamicContentTaskBuilder<T> withPath(String permanentLink) {
        this.permanentLink = permanentLink;
        return this;
    }

    /**
     * Sets the progress message for the builder.
     *
     * @param progressMessage An optional message prompt for when the task its executing.
     * If the progressMessage its null, no progress dialog will be shown.
     */
    public DynamicContentTaskBuilder<T> withProgressMessage(String progressMessage) {
        this.progressMessage = progressMessage;
        return this;
    }

    /**
     * Sets the task listener for the builder.
     *
     * @param taskListener The application callback for the executed task {@link com.mika.newcode.network.request.TaskListener TaskListener}.
     */
    public DynamicContentTaskBuilder<T> withTaskListener(TaskListener<T> taskListener) {
        this.taskListener = taskListener;
        return this;
    }

    /**
     * Sets a single filter for the builder.
     *
     * @param filter A filter to be applied to the requested content.
     * See <a href="http://sixappeal.captivereach.com/api-doc/1.0/dynamic_contents/filter.html">filters</a>
     */
   /* public DynamicContentTaskBuilder<T> withFilter(Object filter) {
        if (filterRequest == null) {
            filterRequest = new CRFilterRequest();
        }

        filterRequest.getFilters().add(filter);
        return this;
    }*/

    /**
     * Sets a list of filters for the builder.
     *
     * @param filters A list of filters to be applied to the requested content.
     * See <a href="http://sixappeal.captivereach.com/api-doc/1.0/dynamic_contents/filter.html">filters</a>
     */
   /* public DynamicContentTaskBuilder<T> withFilters(List<Object> filters) {
        if (filterRequest == null) {
            filterRequest = new CRFilterRequest();
        }

        filterRequest.setFilters(filters);
        return this;
    }*/

    /**
     * Sets a single sort object for the builder.
     *
     * @param sort A single object to apply a sort to the content using different sorting options.
     * See <a href="http://sixappeal.captivereach.com/api-doc/1.0/dynamic_contents/filter.html">filters</a>
     */
/*    public DynamicContentTaskBuilder<T> withSort(CRSort sort) {
        if (filterRequest == null) {
            filterRequest = new CRFilterRequest();
        }

        filterRequest.getOrder_by().add(sort);
        return this;
    }*/

    /**
     * Sets a single sort object for the builder.
     *
     * @param sort A list of objects to apply a sort to the content using different sorting options.
     * See <a href="http://sixappeal.captivereach.com/api-doc/1.0/dynamic_contents/filter.html">filters</a>
     */
 /*   public DynamicContentTaskBuilder<T> withSort(List<CRSort> sort) {
        if (filterRequest == null) {
            filterRequest = new CRFilterRequest();
        }

        filterRequest.setOrder_by(sort);
        return this;
    }*/

    /**
     * Sets the type for the builder.
     *
     * @param type Defines the type of response, which is the model class for the desired content.
     */
    public DynamicContentTaskBuilder<T> withType(Type type){
        this.type = type;
        return this;
    }

    /**
     * Sets the method for the builder.
     *
     * @param requestMethod Http Method for the content request
     * <ul>
     * <li> {@link com.mika.newcode.enums.HttpMethod#GET HttpGet}
     * <li> {@link com.mika.newcode.enums.HttpMethod#POST HttpPost}
     * <li> {@link com.mika.newcode.enums.HttpMethod#PUT HttpPut}
     * <li> {@link com.mika.newcode.enums.HttpMethod#DELETE HttpDelete}
     * </ul>
     */
    public DynamicContentTaskBuilder<T> withMethod(String requestMethod){
        this.requestMethod = requestMethod;
        return this;
    }

    /**
     * Sets the request params for the builder.
     * These parameters are appended in the url and are set using a Map with the <key,value> pattern.
     *
     * @param params Request parameters appended on the url.
     */
    public DynamicContentTaskBuilder<T> withParams(HashMap<String,String> params){
        this.params = params;
        return this;
    }

    /**
     * Sets a single request param for the builder.
     * These parameters are appended in the url and are set using a Map with the <key,value> pattern.
     *
     * @param key Key for the param object
     * @param value Value of the param object
     */
    public DynamicContentTaskBuilder<T> withParam(String key, String value) {
        if (params == null) {
            params = new HashMap<String, String>();
        }

        params.put(key,value);
        return this;
    }

    /**
     * Sets the data for the builder.
     *
     * @param data A json string containing the request body.
     */
    public DynamicContentTaskBuilder<T> withData(String data) {
        try {
            httpEntity = new StringEntity(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the data for the builder.
     * Using the object containing the data and the type, it converts it into
     * a json string to be used as the request body.
     *
     * @param data An object with the desired fields to be on the content.
     * @param type Defines the type of response, which is the model class for the desired content.
     */
    public DynamicContentTaskBuilder<T> withData(Object data, Type type) {
        try {
            httpEntity = new StringEntity(new Gson().toJson(data, type), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    /** Getters and setters*/

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getPermanentLink() {
        return permanentLink;
    }

    public void setPermanentLink(String permanentLink) {
        this.permanentLink = permanentLink;
    }

    public String getProgressMessage() {
        return progressMessage;
    }

    public void setProgressMessage(String progressMessage) {
        this.progressMessage = progressMessage;
    }

    public TaskListener<T> getTaskListener() {
        return taskListener;
    }

    public void setTaskListener(TaskListener<T> taskListener) {
        this.taskListener = taskListener;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    /*public CRFilterRequest getFilterRequest() {
        return filterRequest;
    }

    public void setFilters(CRFilterRequest filterRequest) {
        this.filterRequest = filterRequest;
    }
*/
    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public HttpEntity getHttpEntity() {
        return httpEntity;
    }

    public void setHttpEntity(HttpEntity httpEntity) {
        this.httpEntity = httpEntity;
    }
}

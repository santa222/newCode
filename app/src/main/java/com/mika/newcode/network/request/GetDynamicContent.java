package com.mika.newcode.network.request;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.mika.newcode.builders.DynamicContentTaskBuilder;
import com.mika.newcode.utils.SettingsUtils;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Carlos Bedoya on 3/19/14.
 */
public class GetDynamicContent<T> extends CaptiveReachRequest<T> {
    private String permanent_link;
    private Type type;
    private Context context;
   // private CRFilterRequest filterRequest;
    private HttpEntity httpEntity;
    private HashMap<String, String> params;

    public GetDynamicContent(DynamicContentTaskBuilder builder) {
        super(builder.getContext(), builder.getRequestMethod(), builder.getProgressMessage(), builder.getTaskListener());
        this.context        = builder.getContext();
        this.permanent_link = builder.getPermanentLink();
        this.type           = builder.getType();
      //  this.filterRequest  = builder.getFilterRequest();
        this.params         = builder.getParams();
/*
        if (filterRequest != null) {
            String json = new Gson().toJson(filterRequest, CRFilterRequest.class);
            Log.d(GetDynamicContent.class.getSimpleName(), "Body request: " + json);
            try {
                httpEntity = new StringEntity(json, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {*/
            httpEntity = builder.getHttpEntity();
       // }
    }

    @Override
    protected String getRequestUrl() throws Exception {
        String dynamicContentUrl  = SettingsUtils.getBaseRestUrl(context) + permanent_link;
        Log.v("222",dynamicContentUrl);
       /* if (filterRequest != null) {
            dynamicContentUrl += "/filter";
        }

        if (params != null && params.size() > 0){
            dynamicContentUrl += FilterUtils.getRequestParamsString(params);
        }*/

        return dynamicContentUrl;
    }


    @Override
    protected HttpEntity getHttpEntity() throws IOException {
        return httpEntity;
    }

    /* @Override
    protected NetworkHelper.Result onNetworkDisabled() {
       try {
            String table = getPermanentLink();

            if (filterRequest != null && filterRequest.getFilters().size() > 0) {
                table += "/filter";
            }

            if (permanent_link.contains("/favorites")) {
                table = table.replace("/favorites", "/filter");

                filterRequest = new CRFilterRequest();
                CRFilter filter = new CRFilter();
                filter.setAttributeName("favorited");
                List<String> filterValues = new ArrayList<String>();
                filterValues.add("1");
                filter.setFilterValues(filterValues);
                filterRequest.getFilters().add(filter);
            }

            CRDynamicContentDao dao = new CRDynamicContentDao(context);
            dao.setFilters(filterRequest.getFilters());
            String json = dao.get(table);
            return new NetworkHelper.Result(json);
        } catch (Exception e) {
            return new NetworkHelper.Result(e);
        }
    }*/

    @Override
    protected T parseResponse(String responseString) throws Exception {
        T response = new Gson().fromJson(responseString, type);

        return response;
    }

    public void execute() {
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}

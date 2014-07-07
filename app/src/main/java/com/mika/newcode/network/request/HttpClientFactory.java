package com.mika.newcode.network.request;


import com.mika.newcode.utils.Constants;

import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * Created by Carlos Bedoya on 5/30/14.
 */
public class HttpClientFactory {

    private static DefaultHttpClient client;

    public synchronized static DefaultHttpClient getThreadSafeClient() {

        if (client != null)
            return client;
        client = new DefaultHttpClient();

        ClientConnectionManager mgr = client.getConnectionManager();

        HttpParams params = client.getParams();
        params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        params.setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
        HttpConnectionParams.setConnectionTimeout(params, Constants.CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, Constants.SO_TIMEOUT);
        HttpConnectionParams.setTcpNoDelay(params, true);
        client = new DefaultHttpClient(
                new ThreadSafeClientConnManager(params,
                        mgr.getSchemeRegistry()), params);

        return client;
    }
}
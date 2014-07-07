package com.mika.newcode.enums;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

/**
 * Created by carlosbedoya on 6/10/14.
 */
public enum HttpMethod {
    GET(HttpGet.METHOD_NAME),
    POST(HttpPost.METHOD_NAME),
    PUT(HttpPut.METHOD_NAME),
    DELETE(HttpDelete.METHOD_NAME);

    String methodName;

    HttpMethod(String methodName) {
        this.methodName = methodName;
    }

    public String getValue() {
        return methodName;
    }
}

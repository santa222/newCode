package com.mika.newcode.network.request;

/**
 * Interface definition for a callback to be invoked when a request to the server has been executed.
 */
public interface TaskListener<T> {
    public void onTaskSuccess(T result);
    public void onTaskFailure(Exception e);
    public void onTaskCancelled();
    public void onTaskTimeOut();
}
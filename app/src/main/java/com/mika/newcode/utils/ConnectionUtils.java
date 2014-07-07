package com.mika.newcode.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by hugo on 3/24/14.
 */
public class ConnectionUtils {
    public enum NetworkState {
        ONLINE,
        OFFLINE
    }

    public static boolean isWifiNetworkActive(Context context){
        return isNetworkActive(context, ConnectivityManager.TYPE_WIFI);
    }

    public static boolean isMobileNetworkActive(Context context){
        return isNetworkActive(context, ConnectivityManager.TYPE_MOBILE);
    }

    public static boolean isNetworkActive(Context context) {
        return isWifiNetworkActive(context) || isMobileNetworkActive(context);
    }

    private static boolean isNetworkActive(Context context, int connectionType){
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(connectionType);
        if(info != null){
            return info.isConnected();
        }
        return false;
    }

    public static NetworkState getNewNetworkState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

        if ( activeNetInfo == null ) {
            return NetworkState.OFFLINE;
        } else {
            return NetworkState.ONLINE;
        }
    }

    public static String getNetworkLog(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

        String networkLog;

        if (activeNetInfo == null) {
            networkLog = "offline";
        } else {
            networkLog = "name= " + activeNetInfo.getState().name() + ", type: " + activeNetInfo.getTypeName() + ", subtype: " + activeNetInfo.getSubtypeName() + ", extrainfo: " + activeNetInfo.getExtraInfo() + ", detailed state: " + activeNetInfo.getDetailedState().toString();
        }

        return networkLog;
    }
}

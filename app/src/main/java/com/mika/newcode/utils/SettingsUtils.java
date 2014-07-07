package com.mika.newcode.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Daniel on 5/21/13.
 */
public class SettingsUtils {

    private static final String BASE_URL = "base_url";
    private static final String BASE_REST_URL = "base_rest_url";
    private static final String GCM_SENDER_ID = "gcm_sender_id";
    public static final String REST_DYNAMIC_MODULE = "dynamic/";
    public static final String EMPTY_STRING = "";
    public static final String CACHE_RUNNING = "cache_service";
    public static final String CACHE_ENABLE = "cache_enable";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String DEVICE_ID = "device_id";
    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String USERNAME  = "username";
    public static final String AGE = "age";
    public static final String GENDER = "gender";
    public static final String FACEBOOK = "facebook";
    public static final String LOCATION_SERVICES = "location_services_preference";
    public static final String PROFILE_SET_UP = "profile_set_up";
    public static final String DEVICE_REGISTER = "device_register";
    public static final String REGISTRATION_TOKEN = "registration_token";
    public static final String PUSH_ID = "push_id";
    public static final String TWITTER = "twitter";
    public static final String TWITTER_TOKEN_SET = "twitter_token_set";
    public static final String TWITTER_TOKEN = "twitter_token";
    public static final String TWITTER_SECRET_KEY = "twitter_secret";
    public static final String EULA_ACCEPTED = "eula_accepted";
    public static final String LINKED_IN_TOKEN_SET = "linked_in_token_set";
    public static final String LINKED_IN_TOKEN = "linked_in_token";
    public static final String LINKED_IN_SECRET_KEY = "linked_in_secret_key";
    public static final String PRIVACY_ACCEPTED = "privacy_accepted";
    //Location Service Settings
    public static final String LOCATION_SERVICE = "location_service";
    private static final String USE_GOOGLE_PLAY_LOCATION_SERVICE = "use_google_play_location_service_preference";

    public static final String MEMBER_FIRST_NAME = "memberFirstName";
    public static final String MEMBER_LAST_NAME = "memberLastName";
    public static final String MEMBER_EMAIL = "memberEmail";
    public static final String MEMBER_AGE = "memberAge";
    public static final String MEMBER_CITY = "memberCity";
    public static final String MEMBER_STATE = "memberState";

    public static SharedPreferences getSettings(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferences.Editor getSettingsEditor(Context context) {
        return getSettings(context).edit();
    }

    public static String getSafeSetting(Context ctx, String settingName,
                                        String defaultValue) {
        return getSettings(ctx).getString(settingName, defaultValue);
    }

    private static boolean getSafeSetting(Context ctx, String locationServices, boolean b) {
        return getSettings(ctx).getBoolean(locationServices, b);
    }

    public static String getBaseUrl(Context ctx) {
        return getSettings(ctx).getString(BASE_URL, "");
    }

    public static void setBaseUrl(Context ctx, String url) {
        getSettingsEditor(ctx).putString(BASE_URL, url).commit();
    }

    public static String getBaseRestUrl(Context ctx) {
        return getSettings(ctx).getString(BASE_REST_URL, "");
    }

    public static void setBaseRestUrl(Context ctx, String url) {
        getSettingsEditor(ctx).putString(BASE_REST_URL, url).commit();
    }

    public static String getGcmSenderId(Context ctx) {
        return getSettings(ctx).getString(GCM_SENDER_ID, "");
    }

    public static void setGcmSenderId(Context ctx, String url) {
        getSettingsEditor(ctx).putString(GCM_SENDER_ID, url).commit();
    }
/*

    public static String getDeviceInfo(Context ctx) {
        return DeviceUtils.getDeviceInfo();
    }

    public static String getDeviceId(Context ctx) {
        String id = getSafeSetting(ctx, DEVICE_ID, null);
        if (id == null) {
            id = DeviceUtils.getDeviceId(ctx);
            getSettingsEditor(ctx).putString(DEVICE_ID, id).commit();
        }
        return id;
    }
*/

    public static String getLat(Context ctx) {
        return getSafeSetting(ctx, LAT, EMPTY_STRING);
    }

    public static void setLat(Context ctx, double lat) {
        getSettingsEditor(ctx).putString(LAT, String.valueOf(lat)).commit();
    }

    public static void setLng(Context ctx, double lng) {
        getSettingsEditor(ctx).putString(LNG, String.valueOf(lng)).commit();
    }

    public static String getLng(Context ctx) {
        return getSafeSetting(ctx, LNG, EMPTY_STRING);
    }

    public static String getEmail(Context ctx) {
        return getSafeSetting(ctx, EMAIL, EMPTY_STRING);
    }

    public static void setEmail(Context ctx, String email) {
        getSettingsEditor(ctx).putString(EMAIL, email).commit();
    }

    public static String getFirstname(Context ctx) {
        return getSafeSetting(ctx, FIRST_NAME, EMPTY_STRING);
    }

    public static void setFirstName(Context ctx, String firstName) {
        getSettingsEditor(ctx).putString(FIRST_NAME, firstName).commit();
    }

    public static String getLastname(Context ctx) {
        return getSafeSetting(ctx, LAST_NAME, EMPTY_STRING);
    }

    public static void setLastName(Context ctx, String lastName) {
        getSettingsEditor(ctx).putString(LAST_NAME, lastName).commit();
    }


    public static String getUsername(Context ctx) {
        return getSafeSetting(ctx,USERNAME, EMPTY_STRING);
    }

    public static void setUsername(Context ctx, String username){
        getSettingsEditor(ctx).putString(USERNAME, username).commit();
    }

    public static String getAge(Context ctx) {
        return getSafeSetting(ctx, AGE, EMPTY_STRING);
    }

    public static void setAge(Context ctx, String age) {
        getSettingsEditor(ctx).putString(AGE, age).commit();
    }

    public static String getGender(Context ctx) {
        return getSafeSetting(ctx, GENDER, EMPTY_STRING);
    }

    public static void setGender(Context ctx, String gender) {
        getSettingsEditor(ctx).putString(GENDER, gender).commit();
    }

    public static String getFacebookPreference(Context ctx) {
        return getSafeSetting(ctx, FACEBOOK, "OFF");
    }

    public static void setFacebookPreference(Context ctx, String facebookPreference) {
        getSettingsEditor(ctx).putString(FACEBOOK, facebookPreference).commit();
    }

    public static String getTwitterPreference(Context ctx) {
        return getSafeSetting(ctx, TWITTER, "OFF");
    }

    public static void setTwitterPreference(Context ctx, String twitterPreference) {
        getSettingsEditor(ctx).putString(TWITTER, twitterPreference).commit();
    }

    public static boolean getLocationServicesPreference(Context ctx) {
        return getSafeSetting(ctx, LOCATION_SERVICES, true);
    }

    public static void setlocationServicesPreference(Context ctx, boolean locationServices) {
        getSettingsEditor(ctx).putBoolean(LOCATION_SERVICES, locationServices).commit();
    }

    public static void setlocationServicesPreference(Context ctx, String locationServices) {
        getSettingsEditor(ctx).putString(LOCATION_SERVICES, locationServices).commit();
    }

    public static Boolean getProfileSetUp(Context ctx) {
        return getSettings(ctx).getBoolean(PROFILE_SET_UP, false);
    }

    public static void setProfileSetUp(Context ctx, Boolean isDone) {
        getSettingsEditor(ctx).putBoolean(PROFILE_SET_UP, isDone).commit();
    }

    public static void setDeviceRegister(Context ctx, Boolean registration) {
        getSettingsEditor(ctx).putBoolean(DEVICE_REGISTER, registration).commit();
    }

    public static Boolean getDeviceRegister(Context ctx) {
        return getSettings(ctx).getBoolean(DEVICE_REGISTER, false);
    }

    public static void setRegistrationToken(Context ctx, String registrationToken) {
        getSettingsEditor(ctx).putString(REGISTRATION_TOKEN, registrationToken).commit();
    }

    public static String getRegistrationToken(Context ctx) {
        return getSafeSetting(ctx, REGISTRATION_TOKEN, EMPTY_STRING);
    }

    public static String getPushId(Context ctx) {
        return getSafeSetting(ctx, PUSH_ID, EMPTY_STRING);
    }

    public static void setPushId(Context ctx, String pushId) {
        getSettingsEditor(ctx).putString(PUSH_ID, pushId).commit();
    }

    public static boolean getIsPreferedGooglePlayLocationServicesPreference(Context ctx) {
        return getSettings(ctx).getBoolean(USE_GOOGLE_PLAY_LOCATION_SERVICE, true);
    }

    public static void setPreferedGooglePlayLocationServicesPreference(Context ctx, String locationServices) {
        getSettingsEditor(ctx).putString(USE_GOOGLE_PLAY_LOCATION_SERVICE, locationServices).commit();
    }

    public static Boolean isCacheRunning(Context ctx) {
        return getSettings(ctx).getBoolean(CACHE_RUNNING, false);
    }

    public static void setCacheRunning(Context ctx, Boolean isRunning) {
        getSettingsEditor(ctx).putBoolean(CACHE_RUNNING, isRunning).commit();
    }

    public static Boolean isCacheEnabled(Context ctx) {
        return getSettings(ctx).getBoolean(CACHE_ENABLE, false);
    }

    public static void setCacheEnable(Context ctx, Boolean enable) {
        getSettingsEditor(ctx).putBoolean(CACHE_ENABLE, enable).commit();
    }

}

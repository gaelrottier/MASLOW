package fr.unice.mbds.maslow.util;

import android.app.Activity;
import android.app.ProgressDialog;

import com.androidquery.AQuery;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nicolas on 09/12/2015.
 */
public class ApiCallService {

    private static ApiCallService instance = null;
    private AQuery aq;

    public static ApiCallService getInstance() {
        if (instance == null) {
            synchronized (ApiCallService.class) {
                if (instance == null) {
                    instance = new ApiCallService();
                }
            }
        }

        return instance;
    }

    public void doGet(Activity caller, ProgressDialog progress, String url, String callbackMethodName) {
        execute(caller, progress, url, AQuery.METHOD_GET, null, callbackMethodName);
    }

    public void doDelete(Activity caller, ProgressDialog progress, String url, String callbackMethodName) {
        execute(caller, progress, url, AQuery.METHOD_DELETE, null, callbackMethodName);
    }

    public void doPut(Activity caller, ProgressDialog progress, String url, JSONArray params, String callbackMethodName) {
        execute(caller, progress, url, AQuery.METHOD_PUT, params, callbackMethodName);
    }

    public void doPost(Activity caller, ProgressDialog progress, String url, JSONArray params, String callbackMethodName) {
        execute(caller, progress, url, AQuery.METHOD_POST, params, callbackMethodName);
    }

    private void execute(Activity caller, ProgressDialog progress, String url, int method, JSONArray json, String callbackMethodName) {
        aq = new AQuery(caller);

        aq.progress(progress);

        if (method == AQuery.METHOD_POST || method == AQuery.METHOD_PUT) {
            Map<String, Object> params = new HashMap<>();

            params.put(AQuery.POST_ENTITY, json);

            aq.ajax(url, params, JSONArray.class, caller, callbackMethodName);
        } else {
            aq.ajax(url, JSONArray.class, caller, callbackMethodName);
        }
    }
}
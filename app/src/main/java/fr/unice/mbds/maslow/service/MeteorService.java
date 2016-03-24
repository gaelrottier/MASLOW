package fr.unice.mbds.maslow.service;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import fr.unice.mbds.maslow.entities.Appareil;
import fr.unice.mbds.maslow.entities.Evenement;
import fr.unice.mbds.maslow.interfaces.ICallback;
import fr.unice.mbds.maslow.util.ApiUrlService;
import im.delight.android.ddp.Meteor;
import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.ResultListener;

/**
 * Created by Gael on 14/03/2016.
 */
public class MeteorService implements MeteorCallback {

    private static MeteorService instance = null;
    private ICallback callbackClass;
    private Meteor meteor;

    private MeteorService() {
    }

    public static MeteorService getInstance() {
        if (instance == null) {
            synchronized (MeteorService.class) {
                if (instance == null) {
                    instance = new MeteorService();
                }
            }
        }

        return instance;
    }

    public void sendCommand(String commandId, JSONObject parameters) {
        meteor.call("sendCommandById", new Object[]{commandId, parameters}, new ResultListener() {
            @Override
            public void onSuccess(String s) {
                Log.w("websocket", "Succès");
            }

            @Override
            public void onError(String s, String s1, String s2) {
                Log.e("WebSocket", s + ", " + s1 + ", " + s2);
            }
        });
    }

    public void sendCommand(String commandId) {
        sendCommand(commandId, null);
    }

    public void setCallbackClass(ICallback callbackClass) {
        meteor = new Meteor(callbackClass.getContext(), ApiUrlService.WEBSOCKET_BASE_URL);
        meteor.addCallback(this);
        meteor.connect();
        this.callbackClass = callbackClass;
    }

    @Override
    public void onConnect(boolean b) {
        Log.w("INFO", "Connexion au websocket.");

        meteor.loginWithEmail(ApiUrlService.WEBSOCKET_USERNAME, ApiUrlService.WEBSOCKET_PASSWORD, new ResultListener() {
            @Override
            public void onSuccess(String s) {
                meteor.subscribe("events");
                meteor.subscribe("commands");
                Log.w("Websocket-Connexion", "OK");
            }

            @Override
            public void onError(String s, String s1, String s2) {
                Log.e("Websocket-Connexion", s1);
                Toast.makeText(callbackClass.getContext(), "Connexion au websocket échouée", Toast.LENGTH_LONG).show();
            }
        });

        if (!meteor.isConnected()) {
            Toast.makeText(callbackClass.getContext(), "Connexion à Orchestra impossible", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDisconnect() {
        Toast.makeText(callbackClass.getContext(), "Déconnecté du websocket", Toast.LENGTH_LONG).show();
        Log.w("INFO", "Déconnecté du websocket.");
        meteor.connect();
    }

    @Override
    public void onException(Exception e) {
        Log.e("ERREUR", "Une exception est survenue", e);
    }

    @Override
    public void onDataAdded(String collectionName, String documentID, String newValueJson) {
        Log.w("onDataAdded ", "collectionName = [" + collectionName + "], documentID = [" + documentID + "], newValueJson = [" + newValueJson + "]");
        if ("events".equals(collectionName)) {
            if (callbackClass != null) {
                Appareil appareilConcerne = checkId(documentID);
                if (appareilConcerne != null) {
                    callbackClass.onDataAdded(collectionName, documentID, getParameters(newValueJson), appareilConcerne);
                }
            }
        }
    }

    @Override
    public void onDataChanged(String collectionName, String documentID, String updateValuesJson, String removedValuesJson) {
        Log.w("onDataChanged", "collectionName = [" + collectionName + "], documentID = [" + documentID + "], updateValuesJson = [" + updateValuesJson + "], removedValuesJson = [" + removedValuesJson + "]");
        if (callbackClass != null) {
            Appareil appareilConcerne = checkId(documentID);
            if (appareilConcerne != null)
                callbackClass.onDataChanged(collectionName, documentID, getParameters(updateValuesJson), removedValuesJson, appareilConcerne);
        }
    }

    @Override
    public void onDataRemoved(String collectionName, String documentID) {
        Log.w("onDataRemoved", "collectionName = [" + collectionName + "], documentID = [" + documentID + "]");
        if (callbackClass != null) {
            Appareil appareilConcerne = checkId(documentID);
            if (appareilConcerne != null)
                callbackClass.onDataRemoved(collectionName, documentID, appareilConcerne);
        }
    }

    private Appareil checkId(String documentID) {
        Appareil response = null;

        List<Appareil> appareils = callbackClass.getWatchlist().getAppareils();

        appareilsLoop:
        for (Appareil a : appareils) {
            for (Evenement e : a.getEvenements()) {
                if (e.getIdOrchestra().equals(documentID)) {
                    response = a;
                    break appareilsLoop;
                }
            }
        }

        return response;
    }

    public JSONObject getParameters(String value) {
        JSONObject parameters = null;

        try {

            JSONObject json = new JSONObject(value);

            parameters = json.getJSONObject("parameters");
        } catch (JSONException e) {
            Log.e("json parsing", e.getMessage());
        }

        return parameters;
    }
}

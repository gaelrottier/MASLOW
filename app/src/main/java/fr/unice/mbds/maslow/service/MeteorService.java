package fr.unice.mbds.maslow.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

    public void init(final Context context) {
        meteor = new Meteor(context, ApiUrlService.WEBSOCKET_BASE_URL);
        meteor.addCallback(this);
        meteor.connect();
    }

    public void setCallbackClass(ICallback callbackClass) {
        this.callbackClass = callbackClass;
    }

    @Override
    public void onConnect(boolean b) {
        Log.w("INFO", "Connecté au websocket.");

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
                Toast.makeText((Context) callbackClass, "Connexion au websocket échouée", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDisconnect() {
        Log.w("INFO", "Déconnecté du websocket.");
    }

    @Override
    public void onException(Exception e) {
        Log.e("ERREUR", "Une exception est survenue", e);
    }

    @Override
    public void onDataAdded(String collectionName, String documentID, String newValueJson) {
        if (callbackClass != null) {
//            if (checkId(documentID))
                callbackClass.onDataAdded(collectionName, documentID, newValueJson);
        }
    }

    @Override
    public void onDataChanged(String collectionName, String documentID, String updateValuesJson, String removedValuesJson) {
        if (callbackClass != null) {
//            if (checkId(documentID))
                callbackClass.onDataChanged(collectionName, documentID, updateValuesJson, removedValuesJson);
        }
    }

    @Override
    public void onDataRemoved(String collectionName, String documentID) {
        if (callbackClass != null) {
//            if (checkId(documentID))
                callbackClass.onDataRemoved(collectionName, documentID);
        }
    }

    private boolean checkId(String documentID) {
        boolean response = false;

        List<Appareil> appareils = callbackClass.getWatchlist().getAppareils();

        appareilsLoop:
        for (Appareil a : appareils) {
            for (Evenement e : a.getEvenements()) {
                if (e.getIdOrchestra() == documentID) {
                    response = true;
                    break appareilsLoop;
                }
            }
        }

        return response;
    }
}

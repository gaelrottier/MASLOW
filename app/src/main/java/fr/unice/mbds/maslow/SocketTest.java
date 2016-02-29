package fr.unice.mbds.maslow;



import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import im.delight.android.ddp.Meteor;
import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.ResultListener;

public class SocketTest extends AppCompatActivity implements MeteorCallback {

    public final static String TAG = SocketTest.class.getSimpleName();

    private static final String SOCKET_URL = "ws://134.59.152.114:3000/websocket";

    private static final String CONSO_EVENT_ID = "6DXJvDjpbxjYeDwJT";
    private static final String ON_COMMAND_ID = "XHLQQ5yvhNNmv7Rad";
    private static final String OFF_COMMAND_ID = "kmvdPAgvFZ7dhRk6N";

    private MeteorSingleton mMeteor;



    public MeteorSingleton meteorCallback(Context context)
    {

        mMeteor = MeteorSingleton.createInstance(context, "ws://134.59.152.114:3000/websocket");
        mMeteor.getInstance().setCallback(this);
        return mMeteor;
    }

    public void switchOn(MeteorSingleton mMeteor)
    {
        if (mMeteor.isLoggedIn()) {
            mMeteor.call("sendCommandById", new Object[]{ON_COMMAND_ID}, new ResultListener() {
                @Override
                public void onSuccess(String result) {
                    System.out.println("success");
                    Log.d(TAG, "Result : " + result);

                }

                @Override
                public void onError(String error, String reason, String details) {
                    System.out.println("echec");
                    Log.e(TAG, error + " : " + reason);
                    Toast.makeText(SocketTest.this, "Failed to send command", Toast.LENGTH_LONG).show();

                }
            });
        }
    }

                public void switchOff(MeteorSingleton mMeteor)
                {
                    if (mMeteor.isLoggedIn()) {
                        mMeteor.call("sendCommandById", new Object[]{OFF_COMMAND_ID}, new ResultListener() {
                            @Override
                            public void onSuccess(String result) {
                                System.out.println("success");
                                Log.d(TAG, "Result : " + result);

                            }

                            @Override
                            public void onError(String error, String reason, String details) {
                                System.out.println("echec");
                                Log.e(TAG, error + " : " + reason);
                                Toast.makeText(SocketTest.this, "Failed to send command", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }


    //Exécuté lorsque le socket s'est connecté
    @Override
    public void onConnect(boolean b) {
        mMeteor.loginWithEmail("mbds-maslow@harmolabs.com", "maslowmaslow", new ResultListener() {
            @Override
            public void onSuccess(String s) { //On s'inscrit une fois que l'on a bien été authentifié
                mMeteor.subscribe("events");
                mMeteor.subscribe("commands");
            }

            @Override
            public void onError(String s, String s1, String s2) {
                //Les erreurs de connexion sont écrites dans le Logcat
                Log.e(TAG, s1);
                Toast.makeText(SocketTest.this, "Failed to log in to Socket", Toast.LENGTH_LONG).show();
                System.out.print("erruer");
            }
        });
    }

    @Override
    public void onDisconnect() {

    }

    //Appelée à la première connexion du socket et lorsque un nouveau jeu de données est inséré dans la BDD Mongo
    @Override
    public void onDataAdded(String collectionName, String documentID, String newValueJson) {
        try {
            JSONObject reader = new JSONObject(newValueJson);
            //L'identifiant de l'évènement donné par l'interface d'Orchestra doit correspondre au documentID attribué par Mongo
            if(documentID.compareTo(CONSO_EVENT_ID) == 0){
                //La consomation est donnée dans l'objet "parameters"
                JSONObject parameters = reader.getJSONObject("parameters");
                //La consomation nous est donnée sous forme d'entier à la clé "conso"
                //On met à jour le texte de l'application
                System.out.println("test inDataAdded");
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    //Idem que onDataAdded mais est appelé quand la consommation a changé (ou que la prise a changé d'état)
    @Override
    public void onDataChanged(String collectionName, String documentID, String updateValuesJson, String removedValuesJson) {
        if(documentID.compareTo(CONSO_EVENT_ID) == 0) {
            try {
                JSONObject reader = new JSONObject(updateValuesJson);
                if (reader.has("parameters")) {
                    JSONObject parameters = reader.getJSONObject("parameters");
                    System.out.println("test inDataChanged");

                }
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    @Override
    public void onDataRemoved(String collectionName, String documentID) {

    }

    @Override
    public void onException(Exception e) {
        Log.d(TAG, "Exception : " + e.getMessage());
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}

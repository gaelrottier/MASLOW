package fr.unice.mbds.maslow.interfaces;

import android.content.Context;

import org.json.JSONObject;

import fr.unice.mbds.maslow.entities.Appareil;
import fr.unice.mbds.maslow.entities.Watchlist;

/**
 * Created by Gael on 14/03/2016.
 */
public interface ICallback {

    void onDataAdded(String collectionName, String documentID, JSONObject newValueJson, Appareil appareil);

    void onDataChanged(String collectionName, String documentID, JSONObject updateValuesJson, String removedValuesJson, Appareil appareil);

    void onDataRemoved(String collectionName, String documentID, Appareil appareil);

    Watchlist getWatchlist();

    void setWatchlist(Watchlist watchlist);

    void reconnect();

    Context getContext();
}

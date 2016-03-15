package fr.unice.mbds.maslow.interfaces;

import fr.unice.mbds.maslow.entities.Watchlist;

/**
 * Created by Gael on 14/03/2016.
 */
public interface ICallback {

    void onDataAdded(String collectionName, String documentID, String newValueJson);

    void onDataChanged(String collectionName, String documentID, String updateValuesJson, String removedValuesJson);

    void onDataRemoved(String collectionName, String documentID);

    Watchlist getWatchlist();
}

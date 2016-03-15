package fr.unice.mbds.maslow.entities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Gael on 14/03/2016.
 */
public class Watchlist {
    int id;

    List<Appareil> appareils;

    public Watchlist() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Appareil> getAppareils() {
        return appareils;
    }

    public void setAppareils(List<Appareil> appareils) {
        this.appareils = appareils;
    }

    public JSONObject toJson() {
        JSONObject watchlistJson = new JSONObject();

        try {

            if (id > 0) {
                watchlistJson.put("id", id);
            }

            JSONArray appareilsJson = new JSONArray();

            for (int i = 0; i <= appareilsJson.length(); i++) {
                appareilsJson.put(((Appareil) appareilsJson.get(i)).toJson());
            }

            watchlistJson.put("appareils", appareilsJson);

        } catch (JSONException e) {
            Log.e("Watchlist-Conversion", "Erreur de sérialisation", e);
        }

        return watchlistJson;
    }
}

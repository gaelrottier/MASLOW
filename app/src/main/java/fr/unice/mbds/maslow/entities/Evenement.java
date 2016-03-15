package fr.unice.mbds.maslow.entities;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gael on 14/03/2016.
 */
public class Evenement {
    int id;

    String idOrchestra;

    String alias;

    public Evenement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdOrchestra() {
        return idOrchestra;
    }

    public void setIdOrchestra(String idOrchestra) {
        this.idOrchestra = idOrchestra;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public JSONObject toJson() {
        JSONObject watchlistJson = new JSONObject();

        try {

            if (id > 0) {
                watchlistJson.put("id", id);
            }

            watchlistJson.put("idOrchestra", idOrchestra);
            watchlistJson.put("alias", alias);

        } catch (JSONException e) {
            Log.e("Watchlist-Conversion", "Erreur de sérialisation", e);
        }

        return watchlistJson;
    }
}

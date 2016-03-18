package fr.unice.mbds.maslow.entities;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.unice.mbds.maslow.interfaces.IEntity;

/**
 * Created by Gael on 14/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.)
public class Watchlist implements IEntity{


    private String id;

    private List<Appareil> appareils;

    public Watchlist() {
        appareils = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Appareil> getAppareils() {
        return appareils;
    }

    public void setAppareils(List<Appareil> appareils) {
        this.appareils = appareils;
    }

    public JSONObject toJson() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return new JSONObject(mapper.writeValueAsString(this));
        } catch (JSONException | JsonProcessingException e) {
            Log.e("Serialisation", e.getMessage());
            return null;
        }

//
//        JSONObject watchlistJson = new JSONObject();
//
//        try {
//
//            if (id > 0) {
//                watchlistJson.put("id", id);
//            }
//
//            JSONArray appareilsJson = new JSONArray();
//
//            if (!appareils.isEmpty()) {
//                for (Appareil a : appareils) {
//                    appareilsJson.put(a.toJson());
//                }
//            }
//
//            watchlistJson.put("appareils", appareilsJson);
//
//        } catch (JSONException e) {
//            Log.e("Watchlist-Conversion", "Erreur de sérialisation", e);
//        }
//
//        return watchlistJson;
    }
}

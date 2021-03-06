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
 * Created by Gael on 17/02/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Watchlist implements IEntity {

    private WatchListPK watchListPK;
    private List<Appareil> appareils = new ArrayList<>();

    public Watchlist() {
    }

    public Watchlist(WatchListPK watchListPK) {
        this.watchListPK = watchListPK;
    }

    public WatchListPK getWatchListPK() {
        return watchListPK;
    }

    public void setWatchListPK(WatchListPK watchListPK) {
        this.watchListPK = watchListPK;
    }

    public List<Appareil> getAppareils() {
        return appareils;
    }

    public void setAppareils(List<Appareil> appareils) {
        this.appareils = appareils;
    }

    @Override
    public JSONObject toJson() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return new JSONObject(mapper.writeValueAsString(this));
        } catch (JSONException | JsonProcessingException e) {
            Log.e("Serialisation", e.getMessage());
            return null;
        }
    }
}

package fr.unice.mbds.maslow.entities;

import android.util.Log;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.json.JSONException;
import org.json.JSONObject;

import fr.unice.mbds.maslow.interfaces.IEntity;

/**
 * Created by Gael on 14/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Evenement implements IEntity{

    private String id;

    private String idOrchestra;

//    {"nom":"on","nomOrchestra":"switchOn"}
    private String alias;

    public Evenement() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

            if (id != null) {
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

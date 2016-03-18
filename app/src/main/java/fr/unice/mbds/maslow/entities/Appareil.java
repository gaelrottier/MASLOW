package fr.unice.mbds.maslow.entities;

import android.util.Log;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.unice.mbds.maslow.interfaces.IEntity;

/**
 * Created by Gael on 14/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Appareil implements IEntity{

    private String id;

    private String nom;

    private List<Evenement> evenements;

    public Appareil() {
        evenements = new ArrayList<>();
    }

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public JSONObject toJson() {
        JSONObject watchlistJson = new JSONObject();

        try {

            if (id != null) {
                watchlistJson.put("id", id);
            }

            watchlistJson.put("nom", nom);

            JSONArray evenementsJson = new JSONArray();

            if (!evenements.isEmpty()) {
                for (Evenement e : evenements) {
                    evenementsJson.put(e.toJson());
                }
            }

            watchlistJson.put("evenements", evenementsJson);

        } catch (JSONException e) {
            Log.e("Watchlist-Conversion", "Erreur de sérialisation", e);
        }

        return watchlistJson;
    }
}

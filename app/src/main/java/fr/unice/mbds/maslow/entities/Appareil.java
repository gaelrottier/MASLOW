package fr.unice.mbds.maslow.entities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Gael on 14/03/2016.
 */
public class Appareil {
    int id;

    String nom;

    private List<Evenement> evenements;

    public Appareil() {
    }

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

            if (id > 0) {
                watchlistJson.put("id", id);
            }

            watchlistJson.put("nom", nom);

            JSONArray evenementsJson = new JSONArray();

            for (int i = 0; i <= evenementsJson.length(); i++) {
                evenementsJson.put(((Evenement) evenementsJson.get(i)).toJson());
            }

            watchlistJson.put("evenements", evenementsJson);

        } catch (JSONException e) {
            Log.e("Watchlist-Conversion", "Erreur de sérialisation", e);
        }

        return watchlistJson;
    }
}

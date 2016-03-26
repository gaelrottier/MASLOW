package fr.unice.mbds.maslow.entities;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.unice.mbds.maslow.interfaces.IEntity;

/**
 * Created by Gael on 17/02/2016.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Appareil implements IEntity {

    private int id;

    //Nom affiché par l'appli
    private String nom;

    private List<Evenement> evenements = new ArrayList<>();


    private List<Watchlist> watchlists = new ArrayList<>();

    @JsonIgnore
    public List<Watchlist> getWatchlists() {
        return watchlists;
    }

    public void setWatchlists(List<Watchlist> watchlists) {
        this.watchlists = watchlists;
    }

    public Appareil() {
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

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
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

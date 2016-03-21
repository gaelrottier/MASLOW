package fr.unice.mbds.maslow.entities;

import android.content.Context;
import android.content.SharedPreferences;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.json.JSONObject;

import fr.unice.mbds.maslow.interfaces.IEntity;

/**
 * Created by Gael on 14/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Utilisateur implements IEntity {

    private int id;
    private String nom;
    private String prenom;
    private String identifiant;
    private String token;

    @JsonIgnore
    private String password;

    public Utilisateur() {
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("utilisateur", Context.MODE_PRIVATE);

        return sharedPreferences.getString("token", "");
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}

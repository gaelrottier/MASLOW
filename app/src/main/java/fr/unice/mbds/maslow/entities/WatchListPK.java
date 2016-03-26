package fr.unice.mbds.maslow.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by Gael on 25/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WatchListPK implements Serializable {

    private int idWatchList;

    private int idUtilisateur;

    public WatchListPK() {
    }

    public WatchListPK(int idWatchList, int idUtilisateur) {
        this.idWatchList = idWatchList;
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdWatchList() {
        return idWatchList;
    }

    public void setIdWatchList(int idWatchList) {
        this.idWatchList = idWatchList;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}

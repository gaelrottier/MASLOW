package model;

import java.io.Serializable;

/**
 * Created by Zac on 20/12/2015.
 */

public class Musique implements Serializable {
    private String titre;
    private String artiste;

    public Musique(String titre, String artiste) {
        this.titre = titre;
        this.artiste = artiste;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }
}

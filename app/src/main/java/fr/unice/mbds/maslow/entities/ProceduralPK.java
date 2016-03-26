package fr.unice.mbds.maslow.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by Gael on 22/02/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProceduralPK implements Serializable {
    private int idProcedural;
    private int idUtilisateur;

    public ProceduralPK() {
    }

    public ProceduralPK(int idUtilisateur, int idProcedural) {
        this.idProcedural = idProcedural;
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdProcedural() {
        return idProcedural;
    }

    public void setIdProcedural(int idProcedure) {
        this.idProcedural = idProcedure;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public int hashCode() {
        return idProcedural + idUtilisateur;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ProceduralPK) {
            ProceduralPK ppk = (ProceduralPK) obj;
            return ppk.idUtilisateur == this.idUtilisateur && ppk.idProcedural == idProcedural;
        }

        return false;
    }
}

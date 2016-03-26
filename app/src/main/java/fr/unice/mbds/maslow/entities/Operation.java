
package fr.unice.mbds.maslow.entities;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import fr.unice.mbds.maslow.interfaces.IEntity;

/**
 * Created by Gael on 17/02/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Operation implements IEntity {

    int id;

    String idOrchestra;

    Procedural procedural;

    private Map<String, String> params;

    public Operation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdOrchestra() {
        return idOrchestra;
    }

    public void setIdOrchestra(String idOrchestra) {
        this.idOrchestra = idOrchestra;
    }

    public Procedural getProcedural() {
        return procedural;
    }

    public void setProcedural(Procedural procedural) {
        this.procedural = procedural;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> alias) {
        this.params = alias;
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

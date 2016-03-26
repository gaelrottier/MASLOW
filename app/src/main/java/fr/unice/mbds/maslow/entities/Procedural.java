package fr.unice.mbds.maslow.entities;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import fr.unice.mbds.maslow.interfaces.IEntity;

/**
 * Created by Gael on 17/02/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Procedural implements IEntity {
    private ProceduralPK proceduralPK;

    private List<Operation> operations;

    private Map<String, String> params;

    public Procedural() {
    }

    public Procedural(ProceduralPK proceduralPK) {
        this.proceduralPK = proceduralPK;

    }

    public ProceduralPK getProceduralPK() {
        return proceduralPK;
    }

    public void setProceduralPK(ProceduralPK proceduralPK) {
        this.proceduralPK = proceduralPK;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
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

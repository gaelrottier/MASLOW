package fr.unice.mbds.maslow.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Watchlist;
import fr.unice.mbds.maslow.interfaces.ICallback;
import fr.unice.mbds.maslow.service.MeteorService;
import fr.unice.mbds.maslow.view.adapter.ConsoEnergieItemAdapter;

/**
 * Created by Gael on 21/12/2015.
 */
public class ListeConsoEnergieActivity extends AppCompatActivity implements ICallback {

    private ListView listeAppareils;
    private ConsoEnergieItemAdapter adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_conso_energie);
        MeteorService.getInstance().setCallbackClass(this);
        listeAppareils = (ListView) findViewById(R.id.activity_liste_conso_energie_list_view);

        Map<String, String> appareils = new HashMap<>();

        appareils.put("Télévision", "30W");
        appareils.put("Lave linge", "70W");
        appareils.put("Chauffage", "50W");

        adaptor = new ConsoEnergieItemAdapter(getApplicationContext(), appareils);
        listeAppareils.setAdapter(adaptor);
    }


    @Override
    public void onDataAdded(String collectionName, String documentID, String newValueJson) {
        Log.w("data added", "collectionName :" + collectionName + "; documentID : " + documentID + "; newValueJson : " + newValueJson);
    }

    @Override
    public void onDataChanged(String collectionName, String documentID, String updateValuesJson, String removedValuesJson) {
        Log.w("data changed", "collectionName :" + collectionName + "; documentID : " + documentID + "; updateValuesJson : " + updateValuesJson + "; removedValuesJson : " + removedValuesJson);
    }

    @Override
    public void onDataRemoved(String collectionName, String documentID) {
        Log.w("data removed", "collectionName :" + collectionName + "; documentID : " + documentID);
    }

    @Override
    public Watchlist getWatchlist() {
        return null;
    }
}

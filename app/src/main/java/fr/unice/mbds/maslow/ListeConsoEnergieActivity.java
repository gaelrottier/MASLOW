package fr.unice.mbds.maslow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import adapters.ConsoEnergieAdaptor;

/**
 * Created by Gael on 21/12/2015.
 */
public class ListeConsoEnergieActivity extends AppCompatActivity {

    private ListView listeAppareils;
    private ConsoEnergieAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_conso_energie);

        listeAppareils = (ListView) findViewById(R.id.activity_liste_conso_energie_list_view);

        Map<String, String> appareils = new HashMap<>();

        appareils.put("Télévision", "30W");
        appareils.put("Lave linge", "70W");
        appareils.put("Chauffage", "50W");

        adaptor = new ConsoEnergieAdaptor(getApplicationContext(), appareils);
    }
}
package fr.unice.mbds.maslow.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Watchlist;
import fr.unice.mbds.maslow.service.MeteorService;
import fr.unice.mbds.maslow.util.ApiCallService;
import fr.unice.mbds.maslow.util.ApiUrlService;
import fr.unice.mbds.maslow.view.adapter.ConsoEnergieItemAdapter;

/**
 * Created by Gael on 21/12/2015.
 */
public class ListeConsoEnergieActivity extends AppCompatActivity{

    private ListView listeAppareils;
    private ConsoEnergieItemAdapter adaptor;
    private Watchlist watchlist;
    private static final int watchlistId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_conso_energie);

        listeAppareils = (ListView) findViewById(R.id.activity_liste_conso_energie_list_view);

        Map<String, String> appareils = new HashMap<>();

        appareils.put("Télévision", "30W");
        appareils.put("Lave linge", "70W");
        appareils.put("Chauffage", "50W");

        ResponseEntity result = null;
        try {
            result = ApiCallService.getInstance().execute(ApiUrlService.getWatchlistUrl(watchlistId), HttpMethod.GET, null, Watchlist.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        watchlist = (Watchlist) result.getBody();

        adaptor = new ConsoEnergieItemAdapter(getApplicationContext(), watchlist);
        listeAppareils.setAdapter(adaptor);
        MeteorService.getInstance().setCallbackClass(adaptor);

    }
}

package fr.unice.mbds.maslow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import adapters.LumieresItemAdapter;

public class ListeDiffuseurOdeursActivity extends AppCompatActivity {

    ListView listeDiffuseurs;
    LumieresItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_diffuseur_odeurs);

        listeDiffuseurs = (ListView) findViewById(R.id.activity_liste_diffuseurs_odeur_list_view);

        Map<String, String> diffuseurs = new HashMap<>();

        diffuseurs.put("Chambre", "true");
        diffuseurs.put("Cuisine", "false");
        diffuseurs.put("Salon", "true");
        diffuseurs.put("Salle de bain", "false");

        adapter = new LumieresItemAdapter(getApplicationContext(), diffuseurs);
        listeDiffuseurs.setAdapter(adapter);
    }
}

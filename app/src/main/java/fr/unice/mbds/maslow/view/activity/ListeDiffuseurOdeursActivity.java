package fr.unice.mbds.maslow.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.view.adapter.DiffuseurOdeursItemAdapter;

public class ListeDiffuseurOdeursActivity extends AppCompatActivity {

    ListView listeDiffuseurs;
    DiffuseurOdeursItemAdapter adapter;

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

        adapter = new DiffuseurOdeursItemAdapter(getApplicationContext(), diffuseurs);
        listeDiffuseurs.setAdapter(adapter);
    }
}

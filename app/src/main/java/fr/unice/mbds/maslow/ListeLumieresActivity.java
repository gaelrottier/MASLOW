package fr.unice.mbds.maslow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

import adapters.LumieresItemAdapter;

public class ListeLumieresActivity extends AppCompatActivity {

    ListView listeLumieres;
    LumieresItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_lumieres);

        listeLumieres = (ListView) findViewById(R.id.activity_liste_lumieres_list_view);

        Map<String, String> lumieres = new HashMap<>();

        lumieres.put("Chambre", "true");
        lumieres.put("Cuisine", "false");
        lumieres.put("Salon", "true");
        lumieres.put("Salle de bain", "false");

        adapter = new LumieresItemAdapter(getApplicationContext(), lumieres);
        listeLumieres.setAdapter(adapter);
    }
}

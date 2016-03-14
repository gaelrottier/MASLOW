package fr.unice.mbds.maslow.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.view.adapter.PlayListItemAdapter;

public class PlayListsActivity extends AppCompatActivity {
    protected int nombrePlayList;
    protected PlayListItemAdapter adapter;
    protected ListView listView;
    protected List<Class<? extends AppCompatActivity>> boutons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_play_list);
        listView = (ListView) findViewById(R.id.listViewItemPlayList);

        Random r = new Random();
        List<Class<? extends AppCompatActivity>> boutons = new ArrayList<>();
        nombrePlayList = (r.nextInt(5)) + 5;
        for (int i=0;i<nombrePlayList;i++) {
            boutons.add(ListeMusiquesActivity.class);
        }

        adapter = new PlayListItemAdapter(getApplicationContext(),boutons);
        listView.setAdapter(adapter);

    }

}

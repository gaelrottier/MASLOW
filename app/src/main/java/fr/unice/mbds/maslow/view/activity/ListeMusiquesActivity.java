package fr.unice.mbds.maslow.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.view.adapter.MusicItemAdapter;
import object.Musique;

public class ListeMusiquesActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_musiques);
        listView = (ListView) findViewById(R.id.listViewMusiques);
        Bundle extra = getIntent().getBundleExtra("extra");
        List<Musique> musiqueList = (ArrayList<Musique>) extra.getSerializable("playList");
        final MusicItemAdapter adaptor = new MusicItemAdapter(ListeMusiquesActivity.this, musiqueList);
        listView.setAdapter(adaptor);

    }
}
